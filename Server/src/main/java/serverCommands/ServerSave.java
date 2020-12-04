package serverCommands;

import basic.LabWork;
import server.ClientHandler;
import server.CommandProvider;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**save the collection to a file*/
public class ServerSave extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;

    public ServerSave(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {

        File file = commandProvider.getInputFile();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("there is no file to save to and we can't create one");
                return;
            }
        }

        PrintWriter pw = new PrintWriter(file);
        for (LabWork lw: commandProvider.getSet()) {
            pw.print(lw.getId()+","+lw.getName()+","+lw.getCoordinates().getX()+","+lw.getCoordinates().getY()+","+lw.getCreationDate()+","+lw.getMinimalPoint()+","+lw.getDescription()+","+lw.getDifficulty()+","+lw.getAuthor().getName()+","+lw.getAuthor().getHeight()+","+lw.getAuthor().getWeight()+","+lw.getAuthor().getPassportID()+","+lw.getAuthor().getEyeColor());
            pw.println();
        }
        System.out.println("saved successfully");
        pw.close();
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
