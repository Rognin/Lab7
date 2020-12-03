package serverCommands;

import server.CommandProvider;
import server.Server;

import java.io.IOException;

/**print the last 6 commands entered*/
public class ServerHistory extends ServerCommand {
    Server server;
    CommandProvider commandProvider;
    public ServerHistory(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
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
