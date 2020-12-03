package serverCommands;

import server.CommandProvider;
import server.Server;

import java.io.IOException;

/**print information about the main collection*/
public class ServerInfo extends ServerCommand {
    Server server;
    CommandProvider commandProvider;
    public ServerInfo(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {

        server.answer=("class: " + server.getSet().getClass() + "\n" +
                "initialization date: "+ server.date.toString()+"\n"+
                "number of elements:"+ server.getSet().size());
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
