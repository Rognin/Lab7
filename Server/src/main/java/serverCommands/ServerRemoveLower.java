package serverCommands;

import basic.LabWork;
import server.CommandProvider;
import server.Server;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * remove all elements with a value less than that of the given one (compares by minimal point)
 */
public class ServerRemoveLower extends ServerCommand {
    Server server;
    CommandProvider commandProvider;
    public ServerRemoveLower(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        LabWork lw = (LabWork) additionalInput;

        Set<LabWork> set = server.getSet().stream().filter((l) -> l.compareTo(lw) < 0).collect(Collectors.toSet());
        set.forEach(l -> server.getSet().remove(l));
        server.answer = "success";

        try {
            PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                    "delete from labworks where user_id = ? and minimalpoint < ? returning labworks.id"
            );
            statement.setInt(1, 1);
            statement.setDouble(2, lw.getMinimalPoint());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Integer> ids = new ArrayList<>();
            while (resultSet.next())
                ids.add(resultSet.getInt("id"));
            Set<LabWork> labworks = server.getSet();
            labworks.removeAll((labworks.parallelStream().filter(labWork -> ids.indexOf(labWork.getId()) != -1)
                    .collect(Collectors.toCollection(HashSet::new))));
            server.answer="successfully removed all elements lower than the given one";
        } catch (SQLException e) {
             server.answer="something went wrong with the data base";
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
