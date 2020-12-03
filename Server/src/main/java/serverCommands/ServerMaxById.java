package serverCommands;

import basic.LabworkByIdComparator;
import server.CommandProvider;
import server.Server;

import java.io.IOException;

/**
 * print the information about the element in the main collection with the greatest id value
 */
public class ServerMaxById extends ServerCommand {
    Server server;
    CommandProvider commandProvider;
    public ServerMaxById(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        server.answer = server.getSet().stream().max(new LabworkByIdComparator()).get().toString();
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
