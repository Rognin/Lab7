package commands;

import client.Client;
import basic.*;
import java.io.IOException;

/**add an element to the collection if it's the smallest one*/
public class AddIfMin extends Command {
    Client client;
    public AddIfMin(Client client) {
        super(client);
        this.client = client;
    }

    @Override
    public void onCall(String args) throws IOException {

        LabWorkUserInputReader ir = new LabWorkUserInputReader(client.input);
        LabWork lw = ir.getUserInput();

        client.additionalOutputToServer = lw;

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
