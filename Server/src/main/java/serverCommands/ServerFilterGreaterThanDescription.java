package serverCommands;

import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * print all the element with a description value greater than the given one
 */
public class ServerFilterGreaterThanDescription extends ServerCommandWithArgs {
    ClientHandler clientHandler;
    CommandProvider commandProvider;
    public ServerFilterGreaterThanDescription(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    /**
     * the given description
     */
    String description;

    @Override
    public void onCall(Object additionalInput) throws IOException {
        description = (String) additionalInput;
        commandProvider.getSet().stream().filter((l) -> l.getDescription().compareTo(description) > 0).collect(Collectors.toList()).forEach((p) -> clientHandler.answer += "\n" + p.toString());
    }

    @Override
    public void getArgs(String args) {
        super.getArgs(args);
        description = super.args;
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }
}
