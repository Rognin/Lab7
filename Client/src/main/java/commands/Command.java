package commands; /**the class that has all the command classes as descendants*/

import client.Client;

import java.io.IOException;
import java.io.Serializable;

public class Command implements Serializable {

    Client client;

    public Command(Client client) {
        this.client=client;
    }

    private static final long serialVersionUID = 1;

    /**
     * the main method that determines how a command functions
     * @param args the parameter for a command
     * */
    public void onCall(String args) throws IOException {
    }

    /**used to process the argument*/
    public void getArgs(String args) throws IOException {
    }

    /**
     * used to get command-specific help, currently not implemented
     * @return the helping information
     * */
    public String getHelp() {
        return "";
    }
}
