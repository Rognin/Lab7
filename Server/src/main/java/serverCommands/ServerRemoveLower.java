package serverCommands;

import basic.LabWork;
import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * remove all elements with a value less than that of the given one (compares by minimal point)
 */
public class ServerRemoveLower extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;

    public ServerRemoveLower(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        LabWork lw = (LabWork) additionalInput;

        int userId = commandProvider.getDataBaseHandler().getUserId(clientHandler.currentUI.getUsername());

        try {
            PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                    "delete from labworks where user_id = ? and minimalpoint < ? returning labworks.id"
            );
            statement.setInt(1, userId);
            statement.setDouble(2, lw.getMinimalPoint());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Long> ids = new ArrayList<>();
            while (resultSet.next())
                ids.add(resultSet.getLong("id"));
            Set<LabWork> labworks = commandProvider.getSet();
            Iterator<LabWork> iter = labworks.iterator();
            while (iter.hasNext()) {
                if (ids.contains(iter.next().getId())) iter.remove();
            }
            clientHandler.answer = "successfully removed all your elements lower than the given one";
        } catch (SQLException e) {
            clientHandler.answer = "something went wrong with the data base";
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
