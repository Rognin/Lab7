package serverCommands;

import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;

public class ServerCommand {

    public ServerCommand(ClientHandler clientHandler, CommandProvider commandProvider) {
    }

    private static final long serialVersionUID = 1;

    /**
     * the main method that determines how a command functions
     * @param additionalInput*/
    public void onCall(Object additionalInput) throws IOException {
    }

    /**used to process the argument*/
    public void getArgs(String args) {
    }

    /**
     * used to get command-specific help, currently not implemented
     * @return the helping information
     * */
    public String getHelp() {
        return "";
    }
}
