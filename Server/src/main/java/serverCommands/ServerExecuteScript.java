package serverCommands;

import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;

/**execute a script from a file*/
public class ServerExecuteScript extends ServerCommandWithArgs {
    ClientHandler clientHandler;
    CommandProvider commandProvider;
    public ServerExecuteScript(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    /**the parameter*/
    String args = "";

    /**the name of the file with a script*/
    String fileName = "";

    @Override
    public void onCall(Object additionalInput) throws IOException {
        }

    @Override
    public void getArgs(String args) {
        super.getArgs(args);
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }
}
