package serverCommands;

import server.CommandProvider;
import server.Server;

import java.io.IOException;

public class ServerCommand {
    Server server;
    CommandProvider commandProvider;

    public ServerCommand(Server server, CommandProvider commandProvider) {
        this.server = server;
        this.commandProvider = commandProvider;
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
