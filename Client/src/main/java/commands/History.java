package commands;

import client.Client;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

/**print the last 6 commands entered*/
public class History extends Command {
    Client client;

    public History(Client client) {
        super(client);
        this.client = client;
    }

    @Override
    public void onCall(String args) throws IOException {
        Queue<String> history = new ArrayDeque<>(client.history);
        for (int i = 0; i < client.history.size(); i++) {
            System.out.println(history.poll());
        }
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
