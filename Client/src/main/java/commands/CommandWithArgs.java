package commands;

import client.Client;

import java.io.IOException;

/**command child created to give all commands with a parameter a common processing method*/


public class CommandWithArgs extends Command {
    Client client;
    private static final long serialVersionUID = 1;

    /**the value is the argument*/
    String args = "";

    public CommandWithArgs(Client client) {
        super(client);
        this.client = client;
    }

    /**
     * used to process the argument and account for the case when the argument is missing
     * @param args the argument
     * */
    @Override
    public void getArgs(String args) throws IOException {
        super.getArgs(args);
        this.args=args;
        if(this.args.isEmpty()){
            System.out.println("please enter the argument(s)");
            String tmpArgs = client.input.readLine();
            getArgs(tmpArgs);
        }
    }
}
