package commands;

import client.Client;
import basic.*;

import java.io.IOException;

/**
 * update the value of the element with the given id
 */
public class Update extends Command {
    Client client;

    /**
     * the given id
     */
    long id;

    public Update(Client client) {
        super(client);
        this.client = client;
    }

    @Override
    public void onCall(String args) throws IOException {
        getArgs(args);

        LabWorkUserInputReader ir = new LabWorkUserInputReader(client.input);
        LabWork lw = ir.getUserInput();

        UpdateObjectsPack uop = new UpdateObjectsPack();

        uop.setId(id);
        uop.setLw(lw);

        client.additionalOutputToServer = uop;

    }

    @Override
    public void getArgs(String args) throws IOException {
        if (args.equals("")) {
            System.out.println("please enter the id of the element you need to update");
            try {
                id = Integer.parseInt(client.input.readLine());
            } catch (NumberFormatException e) {
                System.out.println("it looks like what you entered is not an integer. please enter again");
                getArgs(client.input.readLine());
            }
        } else {
            try {
                id = Integer.parseInt(args);
            } catch (NumberFormatException e) {
                System.out.println("it looks like what you entered is not an integer. please enter again");
                getArgs(client.input.readLine());
            }
        }
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }
}
