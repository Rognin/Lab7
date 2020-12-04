/**command child created to give all commands with a parameter a common processing method*/
package serverCommands;

import server.ClientHandler;
import server.CommandProvider;

public class ServerCommandWithArgs extends ServerCommand {
    public ServerCommandWithArgs(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
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
