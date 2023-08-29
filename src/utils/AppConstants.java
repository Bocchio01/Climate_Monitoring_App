package src.utils;

import java.awt.Dimension;

import java.nio.file.Paths;

public class AppConstants {

    public static final String EMPTY_STRING = "NONE";

    public static final class Index {
        public static final int NAME = 0;
        public static final int TAXCODE = 1;
        public static final int EMAIL = 2;
        public static final int ID = 3;
        public static final int PWD = 4;
        public static final int AREA = 5;

        private Index() {
        }
    }

    public static final class Path {

        public static final class Files {

            public static final String CITY_DATAS = "City.data.csv";
            public static final String OPERATOR_DATA = "Operator.data.csv";
            public static final String CITY_COORDS = "CityCoord.list.csv";
            public static final String AREA_DATA = "Area.data.csv";

            private Files() {
            }
        }

        public static final class Assets {

            public static final String LOGO = generatePath("logoDefault.png");
            public static final String LOADING = generatePath("logoLoading.png");
            public static final String FAVICON = generatePath("favicon.png");
            public static final String HOME = generatePath("iconHome.png");
            public static final String THEME = generatePath("iconTheme.png");

            private Assets() {
            }

            private static String generatePath(String fileName) {
                return Paths.get("src", "assets", fileName).toString();
            }
        }

        private Path() {
        }
    }

    public static final class GUI {
        public static final Dimension WIDGET_DIMENSION = new Dimension(300, 40);
        public static final Dimension LABEL_DIMENSION = new Dimension(300, 10);

        private GUI() {
        }
    }

    public static final class DefaultData {
        public static final String NAME = "Tommaso Boccihetti";
        public static final String TAXCODE = "TaxCode_Bocchio";
        public static final String EMAIL = "tommaso.bocchietti@gmail.com";
        public static final String ID = "ID_Bocchio";
        public static final String PWD = "PWD_Bocchio";
        public static final String AREA = "Area di Bocchio";
        // public static final String AREA = String.join(",", new String[] { "Como",
        // "Milano", "Firenze" });

        private DefaultData() {
        }

        @Override
        public String toString() {
            return String.join(",", new String[] { NAME, TAXCODE, EMAIL, ID, PWD, AREA });
        }
    }

    private AppConstants() {
    }

}
