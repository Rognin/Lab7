package serverCommands;

import server.CommandProvider;
import server.Server;

import java.sql.*;
import java.util.ArrayList;

/**clear the collection*/
public class ServerClear extends ServerCommand {
    Server server;
    CommandProvider commandProvider;

    public ServerClear(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) {
        server.getSet().clear();

        try {
            PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                    "delete from labworks where user_id = ? returning labworks.id"
            );
            statement.setInt(1, 1);
            ResultSet resultSet = statement.executeQuery();
//            ArrayList<Integer> ids = new ArrayList<>();
//            while (resultSet.next())
//                ids.add(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        server.answer="success";
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
