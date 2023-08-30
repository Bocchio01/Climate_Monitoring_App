package src.functions;

import java.io.BufferedReader;
import java.io.FileReader;

import src.utils.AppConstants;

public class CityFunctions {

    public static Integer getCityIDByCoords(String latitude, String longitude) {

        Integer cityID = null;

        try {
            double[] coordsQuery = new double[] {
                    Double.parseDouble(latitude.replace(',', '.')),
                    Double.parseDouble(longitude.replace(',', '.'))
            };

            double currentMaxDistance = Integer.MAX_VALUE;

            FileReader fin = new FileReader(AppConstants.Path.Files.CITY_COORDS);
            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                String[] lineArray = line.split(AppConstants.CSV_SEPARATOR);

                try {
                    double[] coordsFile = new double[] {
                            Double.parseDouble(lineArray[5].replace(',', '.')),
                            Double.parseDouble(lineArray[6].replace(',', '.'))
                    };

                    double distance = distanceBetweenCoords(coordsQuery, coordsFile);

                    if (distance < 10 && distance < currentMaxDistance) {
                        currentMaxDistance = distance;
                        cityID = Integer.parseInt(lineArray[0]);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            rfbuffer.close();
            fin.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return cityID;
    }

    public static Integer getCityIDByName(String cityName) {

        Integer cityID = null;

        try {

            FileReader fin = new FileReader(AppConstants.Path.Files.CITY_COORDS);
            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                String[] lineArray = line.split(AppConstants.CSV_SEPARATOR);

                if (lineArray[1].equalsIgnoreCase(cityName)) {
                    cityID = Integer.parseInt(lineArray[0]);
                    break;
                }
            }

            fin.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return cityID;
    }

    private static double distanceBetweenCoords(double[] coords1, double[] coords2) {

        Integer LAT = 0, LON = 1;
        double distance = Math.sqrt(
                Math.pow(coords1[LAT] - coords2[LAT], 2) +
                        Math.pow(coords1[LON] - coords2[LON], 2));

        return distance;
    }

    private static double _distanceBetweenCoords(double[] coords1, double[] coords2) {

        Integer LAT = 0, LON = 1, R = 6371;
        double distLat = Math.toRadians(coords1[LAT] - coords2[LAT]);
        double distLon = Math.toRadians(coords1[LON] - coords2[LON]);

        double a = Math.sin(distLat / 2) * Math.sin(distLat / 2) + Math.cos(Math.toRadians(coords1[LAT]))
                * Math.cos(Math.toRadians(coords2[LAT])) * Math.sin(distLon / 2) * Math.sin(distLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
