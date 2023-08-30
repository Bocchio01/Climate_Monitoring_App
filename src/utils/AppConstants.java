package src.utils;

import java.awt.Dimension;

import java.nio.file.Paths;

public class AppConstants {

    public static final String EMPTY_STRING = "NULL";
    public static final String CSV_SEPARATOR = ";";

    private AppConstants() {
    }

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

        public static final String SEPARATOR = "/";

        public static final class Files {
            public static final String CITY_DATAS = getPath("City.data.csv");
            public static final String OPERATOR_DATA = getPath("Operator.data.csv");
            public static final String CITY_COORDS = getPath("CityCoord.list.csv");
            public static final String AREA_DATA = getPath("Area.data.csv");

            private Files() {
            }

            private static String getPath(String fileName) {
                return Paths.get("data", fileName).toString();
            }
        }

        public static final class Assets {
            public static final String LOGO = getPath("logo.png");
            public static final String HOME = getPath("iconHome.png");
            public static final String THEME = getPath("iconTheme.png");

            private Assets() {
            }

            private static String getPath(String fileName) {
                return SEPARATOR + String.join(SEPARATOR, new String[] { "src", "assets", fileName });
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
        public static final String NAME = "Tommaso Bocchietti";
        public static final String TAXCODE = "TaxCode_Bocchio";
        public static final String EMAIL = "tommaso.bocchietti@gmail.com";
        public static final String ID = "ID_Bocchio";
        public static final String PWD = "PWD_Bocchio";
        public static final String AREA = "Area di Bocchio";

        private DefaultData() {
        }

        @Override
        public String toString() {
            return String.join(AppConstants.CSV_SEPARATOR, new String[] { NAME, TAXCODE, EMAIL, ID, PWD, AREA });
        }
    }

}
