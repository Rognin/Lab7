package serverCommands;

import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;

/**print the last 6 commands entered*/
public class ServerHistory extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;
    public ServerHistory(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

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
