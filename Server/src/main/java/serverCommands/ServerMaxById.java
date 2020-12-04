package serverCommands;

import basic.LabworkByIdComparator;
import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;

/**
 * print the information about the element in the main collection with the greatest id value
 */
public class ServerMaxById extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;
    public ServerMaxById(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        clientHandler.answer = commandProvider.getSet().stream().max(new LabworkByIdComparator()).get().toString();
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
