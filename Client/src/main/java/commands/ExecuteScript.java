package commands;

import client.Client;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**execute a script from a file*/
public class ExecuteScript extends CommandWithArgs {
    Client client;

    /**the parameter*/
    String args = "";

    /**the name of the file with a script*/
    String fileName = "";

    public ExecuteScript(Client client) {
        super(client);
        this.client = client;
    }

    @Override
    public void onCall(String args) throws IOException {
        try {
            getArgs(args);
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNext()) {
                String input = sc.nextLine();
                if (input == null || input.isEmpty()) break;
                client.doCommand(input,client.in, client.out);
            }
        }catch (IOException e){
            System.out.println("this file doesn't exist. if you included the extension please try again without it");
        }
    }

    @Override
    public void getArgs(String args) throws IOException {
        super.getArgs(args);
        try {
            fileName = super.args;
        }catch (InputMismatchException e){
            System.out.println("please enter the name of the file without the extension");
            try {
                getArgs(client.input.readLine());
            }catch (IOException i){
                System.out.println("smth wrong with executeScript");
//                i.printStackTrace();
            }
        }
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }
}
