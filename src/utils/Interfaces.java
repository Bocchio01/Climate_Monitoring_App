package utils;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.GUI;

public class Interfaces {

    public interface RecordCity {
        Integer getID();

        String getName();

        String getASCIIName();

        String getCountryCode();

        String getCountryName();

        double getLatitude();

        double getLongitude();

        String toString();
    }

    public interface RecordOperator {
        Integer getID();

        String getNameSurname();

        String getTaxCode();

        String getEmail();

        String getUsername();

        String getPassword();

        Integer getAreaID();

        String toString();
    }

    public interface RecordArea {
        Integer getID();
        String getAreaName();
        String getStreetName();
        String getStreetNumber();
        String getCAP();
        String getTownName();
        String getDistrictName();
        Integer[] getCityIDs();
    }
    

    public interface RecordWeather {
        Integer getID();

        Integer getAreaID();

        Integer getCityID();

        String getDate();

        WeatherData getWind();

        WeatherData getHumidity();

        WeatherData getPressure();

        WeatherData getTemperature();

        WeatherData getPrecipitation();

        WeatherData getGlacierElevation();

        WeatherData getMassOfGlaciers();

        String toString();
    }

    public interface WeatherData {
        Integer getScore();

        String getComment();

        String toString();
    }

    public interface UIWindows {
        JPanel getMainPanel();
    
        JScrollPane getScrollPane();
    
        JPanel getContentPanel();
    
        void setAppInfo(String text);
    }

    public interface UIPanel {
        JPanel createPanel(GUI gui);
    
        void onOpen(Object[] args);
    
        String getID();
    }
}
