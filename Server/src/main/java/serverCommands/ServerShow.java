package serverCommands;

import server.CommandProvider;
import server.Server;

import java.io.IOException;

/**
 * list all the elements of the main collection
 */
public class ServerShow extends ServerCommand {
    Server server;
    CommandProvider commandProvider;

    public ServerShow(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        server.getSet().forEach(l -> server.answer += "\n" + l.toString());
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
