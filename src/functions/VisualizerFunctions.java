package src.functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.utils.AppConstants;

public class VisualizerFunctions {

    public static String[] getDataFromFile(String nameCity) {

        List<String> dataStrings = new ArrayList<>();

        try (FileReader fin = new FileReader(AppConstants.Path.REGISTERED_DATAS)) {

            BufferedReader freader = new BufferedReader(fin);
            String line;

            while ((line = freader.readLine()) != null) {
                if (nameCity.equalsIgnoreCase(line.split(",")[0])) {
                    dataStrings.add(line.replace("\n", line));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new String[] {};
        }

        return dataStrings.toArray(new String[0]);
    }

    public static String[] computeAverageMessages(String[] dataFromFile) {

        String[] AverageMessage = new String[7];
        String cityName = dataFromFile[0].split(",")[0];

        for (int i = 0; i < 7; i++) {

            String[] AverageMessagePerCategory = new String[dataFromFile.length / 7];
            String categoryName = dataFromFile[i].split(",")[3];
            for (int j = i, k = 0; j < dataFromFile.length; j = j + 7, k++) {
                AverageMessagePerCategory[k] = dataFromFile[j];
            }

            float aveargeScore = computeAverageScores(AverageMessagePerCategory);
            String aveargeComment = computeAverageComment(AverageMessagePerCategory);

            AverageMessage[i] = String.join(",",
                    new String[] { cityName,
                            categoryName,
                            String.format("%.2f", aveargeScore),
                            aveargeComment });
        }

        return AverageMessage;

    }

    public static float computeAverageScores(String[] AverageMessagePerCategory) {

        Integer cumulativeScore = 0;

        for (int i = 0; i < AverageMessagePerCategory.length; i++) {
            String currentScore = AverageMessagePerCategory[i].split(",")[4];

            if (!currentScore.equals("null")) {
                cumulativeScore += Integer.parseInt(currentScore);
            }
        }

        return (float) cumulativeScore / AverageMessagePerCategory.length;
    }

    public static String computeAverageComment(String[] AverageMessagePerCategory) {

        StringBuilder concactedCommentBuilder = new StringBuilder();

        for (int i = 0; i < AverageMessagePerCategory.length; i++) {
            concactedCommentBuilder.append(AverageMessagePerCategory[i].split(",")[5]);

            if (i < (AverageMessagePerCategory.length - 1)) {
                concactedCommentBuilder.append(" | ");
            }
        }

        return concactedCommentBuilder.toString();
    }

}