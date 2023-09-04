package src.models;

import src.models.data.DataHandler;
import src.models.data.DataStorage;
import src.models.file.FileHandler;
import src.models.logic.LogicArea;
import src.models.logic.LogicCity;
import src.models.logic.LogicOperator;

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
