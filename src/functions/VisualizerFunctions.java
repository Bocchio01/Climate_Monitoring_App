package src.functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.utils.ENV;

public class VisualizerFunctions {

    // public static String[] computeAverageMessages(String[] dataFromFile) {

    //     String[] AverageMessage = new String[7];
    //     String cityName = dataFromFile[0].split(AppConstants.CSV_SEPARATOR)[0];

    //     for (int i = 0; i < 7; i++) {

    //         String[] AverageMessagePerCategory = new String[dataFromFile.length / 7];
    //         String categoryName = dataFromFile[i].split(AppConstants.CSV_SEPARATOR)[3];
    //         for (int j = i, k = 0; j < dataFromFile.length; j = j + 7, k++) {
    //             AverageMessagePerCategory[k] = dataFromFile[j];
    //         }

    //         float aveargeScore = computeMeanScore(AverageMessagePerCategory);
    //         String aveargeComment = computeConcatComment(AverageMessagePerCategory);

    //         AverageMessage[i] = String.join(",",
    //                 new String[] { cityName,
    //                         categoryName,
    //                         String.format("%.2f", aveargeScore),
    //                         aveargeComment });
    //     }

    //     return AverageMessage;

    // }

    public static String[] getDataFromFile(int cityID) {

        List<String> dataStrings = new ArrayList<>();

        try (FileReader fin = new FileReader(ENV.Path.Files.CITY_DATAS)) {

            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                int currentCityID = Integer.parseInt(line.split(ENV.CSV_SEPARATOR)[0]);
                if (cityID == currentCityID) {
                    dataStrings.add(line.replace("\n", ""));
                }
            }

        } catch (IOException e) {
            return new String[] {};
        }

        return dataStrings.toArray(new String[] {});
    }

    public static float computeMeanScore(String[] cityDatas, Integer categoryIndex) {

        Integer cumulativeScore = 0;

        for (int i = 0; i < cityDatas.length; i++) {
            String categoryString = cityDatas[i].split(ENV.CSV_SEPARATOR)[categoryIndex];
            String currentScoreString = categoryString.split(",")[0];

            if (!currentScoreString.equals(ENV.EMPTY_STRING)) {
                cumulativeScore += Integer.parseInt(currentScoreString);
            }
        }

        return (float) cumulativeScore / cityDatas.length;
    }

    public static String computeConcatComment(String[] cityDatas, Integer categoryIndex) {

        ArrayList<String> commentArray = new ArrayList<>();

        for (int i = 0; i < cityDatas.length; i++) {
            String categoryString = cityDatas[i].split(ENV.CSV_SEPARATOR)[categoryIndex];
            String currentCommentString = categoryString.split(",")[1];

            if (!currentCommentString.equals(ENV.EMPTY_STRING)) {
                commentArray.add(currentCommentString);
            }
        }

        return String.join(" | ", commentArray);
    }

}