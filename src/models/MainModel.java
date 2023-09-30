package models;

import models.data.DataHandler;
import models.data.DataStorage;
import models.file.FileHandler;
import models.logic.LogicArea;
import models.logic.LogicCity;
import models.logic.LogicOperator;

public class MainModel {
    

    public FileHandler file;
    public DataStorage dataStorage;
    public DataHandler data;

    public LogicOperator logicOperator;
    public LogicArea logicArea;
    public LogicCity logicCity;


    public MainModel() {

        file = new FileHandler();

        data = new DataHandler();

        logicOperator = new LogicOperator(data);

        logicArea = new LogicArea(data);

        logicCity = new LogicCity(data);

    }
}
