package commands;

import client.Client;

import java.io.IOException;

/**list all the elements of the main collection*/
public class Show extends Command {
    Client client;

    public Show(Client client) {
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
