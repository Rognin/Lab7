package serverCommands;

import basic.LabWork;
import server.ClientHandler;
import server.CommandProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * clear the collection
 */
public class ServerClear extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;

    public ServerClear(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) {
        int userId = commandProvider.getDataBaseHandler().getUserId(clientHandler.currentUI.getUsername());

        try {
            PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                    "delete from labworks where user_id = ? returning labworks.id"
            );
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Long> ids = new ArrayList<>();
            while (resultSet.next())
                ids.add(resultSet.getLong("id"));
            Set<LabWork> labworks = commandProvider.getSet();
            Iterator<LabWork> iter = labworks.iterator();
            while (iter.hasNext()) {
                if (ids.contains(iter.next().getId())) iter.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clientHandler.answer = "success";
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
