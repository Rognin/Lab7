package serverCommands;

import server.CommandProvider;
import server.Server;

import java.io.IOException;
import java.util.stream.Collectors;

/**print all the element with a description value less than the given one*/
public class ServerFilterLessThanDescription extends ServerCommandWithArgs {
    Server server;
    CommandProvider commandProvider;
    public ServerFilterLessThanDescription(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
        this.commandProvider = commandProvider;
    }

    /**the given description*/
    String description;

    @Override
    public void onCall(Object additionalInput) throws IOException {
        description = (String) additionalInput;
        server.getSet().stream().filter((l) -> l.getDescription().compareTo(description) < 0).collect(Collectors.toList()).forEach((p) -> server.answer += "\n" + p.toString());
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
