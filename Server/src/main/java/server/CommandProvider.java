package server;

import basic.LabWork;

import java.io.File;
import java.time.LocalDate;
import java.util.Set;

public class CommandProvider {
    DataBaseHandler dataBaseHandler;
    Set<LabWork> set;
    File inputFile;
    LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public Set<LabWork> getSet() {
        return set;
    }

    public void setSet(Set<LabWork> set) {
        this.set = set;
    }

    public DataBaseHandler getDataBaseHandler() {
        return dataBaseHandler;
    }

    public void setDataBaseHandler(DataBaseHandler dataBaseHandler) {
        this.dataBaseHandler = dataBaseHandler;
    }
}
