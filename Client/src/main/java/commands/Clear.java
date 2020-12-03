package commands;

import client.Client;

import java.io.IOException;

/**clear the collection*/
public class Clear extends Command {
    Client client;

    public Clear(Client client) {
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
