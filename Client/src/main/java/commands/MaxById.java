package commands;

import client.Client;

import java.io.IOException;

/**print the information about the element in the main collection with the greatest id value*/
public class MaxById extends Command {
    Client client;

    public MaxById(Client client) {
        super(client);
        this.client = client;
    }

    @Override
    public void onCall(String args) throws IOException {

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
