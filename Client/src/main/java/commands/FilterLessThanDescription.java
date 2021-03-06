package commands;

import client.Client;

import java.io.IOException;

/**print all the element with a description value less than the given one*/
public class FilterLessThanDescription extends CommandWithArgs {
    Client client;

    /**the given description*/
    String description;

    public FilterLessThanDescription(Client client) {
        super(client);
        this.client = client;
    }

    @Override
    public void onCall(String args) throws IOException {
        getArgs(args);
        client.additionalOutputToServer=description;
    }

    @Override
    public void getArgs(String args) throws IOException {
        super.getArgs(args);
        description = super.args;
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }
}
