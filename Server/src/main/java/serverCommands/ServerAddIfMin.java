package serverCommands;

import server.ClientHandler;
import server.CommandProvider;
import basic.*;

import java.io.IOException;
import java.time.LocalDate;

/**
 * add an element to the collection if it's the smallest one
 */
public class ServerAddIfMin extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;

    public ServerAddIfMin(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {

        if (additionalInput != null) {

            LabWork lw = (LabWork) additionalInput;
            lw.setId((long) (commandProvider.getSet().size() + 1));
            lw.setCreationDate(LocalDate.now());
            boolean flag = true;
            for (LabWork labwork : commandProvider.getSet()) {
                if (labwork.compareTo(lw) < 0) flag = false;
            }

            if (flag) {
                ServerAdd add = new ServerAdd(clientHandler, commandProvider);
                add.onCall(lw);
                clientHandler.answer = "success";
            } else {
                clientHandler.answer = "the element wasn't minimal";
            }
        }
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
