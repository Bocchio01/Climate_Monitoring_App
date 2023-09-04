package src.models.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.models.record.WeatherRecord;
import src.models.record.WeatherRecord.WeatherData;

public class LogicCity {

    public static class WeatherTableData {

        public static final String[] keys = {
                "wind",
                "humidity",
                "pressure",
                "temperature",
                "precipitation",
                "glacierElevation",
                "glacierMass", };

        private Map<String, Float> categoryScore = new HashMap<>();
        private Map<String, Integer> categoryRecordCounts = new HashMap<>();
        private Map<String, List<String>> categoryComments = new HashMap<>();

        public WeatherTableData(WeatherRecord[] weatherRecords) {
            for (WeatherRecord record : weatherRecords) {
                processCategory(record.wind(), keys[0]);
                processCategory(record.humidity(), keys[1]);
                processCategory(record.pressure(), keys[2]);
                processCategory(record.temperature(), keys[3]);
                processCategory(record.precipitation(), keys[4]);
                processCategory(record.glacierElevation(), keys[5]);
                processCategory(record.glacierMass(), keys[6]);
            }
        }

        private void processCategory(WeatherData data, String category) {
            if (data.score() != null) {

                categoryScore.put(category, categoryScore.getOrDefault(category, 0f) + data.score());
                categoryRecordCounts.put(category, categoryRecordCounts.getOrDefault(category, 0) + 1);
                List<String> comments = categoryComments.getOrDefault(category, new ArrayList<>());
                comments.add(data.comment());
                categoryComments.put(category, comments);

            }
        }

        public Float getCategoryAvgScore(String category) {
            if (getCategoryRecordCount(category) == 0) {
                return null;
            }
            return categoryScore.getOrDefault(category, null) / getCategoryRecordCount(category);
        }

        public int getCategoryRecordCount(String category) {
            return categoryRecordCounts.getOrDefault(category, 0);
        }

        public List<String> getCategoryComments(String category) {
            return categoryComments.getOrDefault(category, new ArrayList<>());
        }
    }

}
