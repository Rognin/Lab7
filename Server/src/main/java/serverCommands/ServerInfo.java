package serverCommands;

import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;

/**print information about the main collection*/
public class ServerInfo extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;
    public ServerInfo(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {

        clientHandler.answer=("class: " + commandProvider.getSet().getClass() + "\n" +
                "initialization date: "+ commandProvider.getDate().toString()+"\n"+
                "number of elements:"+ commandProvider.getSet().size());
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
