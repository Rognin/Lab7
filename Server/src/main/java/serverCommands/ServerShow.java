package serverCommands;

import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;

/**
 * list all the elements of the main collection
 */
public class ServerShow extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;

    public ServerShow(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        commandProvider.getSet().forEach(l -> clientHandler.answer += "\n" + l.toString());
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
