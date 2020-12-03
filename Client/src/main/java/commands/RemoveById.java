package commands;

import client.Client;

import java.io.IOException;

/**remove an element with the given id from the main collection*/
public class RemoveById extends Command {
    Client client;

    /**the given id*/
    long id;

    public RemoveById(Client client) {
        super(client);
        this.client = client;
    }

    @Override
    public void onCall(String args) throws IOException {

        getArgs(args);
        client.additionalOutputToServer = id;
    }

    @Override
    public void getArgs(String args) throws IOException {
        if (args.equals("")) {
            System.out.println("please enter the id of the element you need to remove");
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
