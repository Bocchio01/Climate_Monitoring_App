package src.utils;

import java.awt.Dimension;

import java.nio.file.Paths;

public class AppConstants {

    public static final String EMPTY_STRING = "NONE";
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

        public static final class Forms {
            private Forms() {
            }

            public static final class CityQuery {
                public static final int CITY_NAME = 0;
                public static final int CITY_LAT = 1;
                public static final int CITY_LON = 1;

                private CityQuery() {
                }
            }

            public static final class OperatorLogin {
                public static final int USER_ID = 0;
                public static final int USER_PWD = 1;

                private OperatorLogin() {
                }
            }

            public static final class OperatorRegistration {
                public static final int USER_NAME = 0;
                public static final int USER_TAXCODE = 1;
                public static final int USER_EMAIL = 2;
                public static final int USER_ID = 3;
                public static final int USER_PWD = 4;
                public static final int USER_AREA = 5;

                private OperatorRegistration() {
                }
            }

            public static final class _AreaCreateNew {
                public static final int AREA_NAME = 0;
                public static final int STREET_NAME = 1;
                public static final int STREET_NUMBER = 2;
                public static final int CAP = 3;
                public static final int TOWN_NAME = 4;
                public static final int DISTRICT_NAME = 5;
                public static final int CITY_NAMES = 6;

                private _AreaCreateNew() {
                }
            }
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
                return SEPARATOR + Paths.get("data", fileName).toString();
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
