package commands;

import client.Client;

import java.io.IOException;

/**print information about the main collection*/
public class Info extends Command {
    Client client;

    public Info(Client client) {
        super(client);
        this.client = client;
    }

    @Override
    public void onCall(String args) {

    }

    @Override
    public void getArgs(String args) throws IOException {
        super.getArgs(args);
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }
}
