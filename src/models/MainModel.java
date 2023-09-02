package src.models;

import src.models.data.DataHandler;
import src.models.data.DataStorage;
import src.models.file.FileHandler;
import src.models.logic.LogicOperator;

public class MainModel {
    

    public FileHandler fileHandler;
    public DataStorage dataStorage;
    public DataHandler dataHandler;

    public LogicOperator logicOperator;


    public MainModel() {

        fileHandler = new FileHandler();

        dataHandler = new DataHandler();

        logicOperator = new LogicOperator(dataHandler);

    }
}
