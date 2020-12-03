/**command child created to give all commands with a parameter a common processing method*/
package serverCommands;

import server.CommandProvider;
import server.Server;

public class ServerCommandWithArgs extends ServerCommand {
    Server server;
    CommandProvider commandProvider;
    public ServerCommandWithArgs(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
        this.commandProvider = commandProvider;
    }

    private static final long serialVersionUID = 1;

    /**the value is the argument*/
    String args = "";

    /**
     * used to process the argument and account for the case when the argument is missing
     * @param args the argument
     * */
    @Override
    public void getArgs(String args) {

    }
}
