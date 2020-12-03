package serverCommands;

import basic.LabWork;
import server.CommandProvider;
import server.Server;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * remove an element with the given id from the main collection
 */
public class ServerRemoveById extends ServerCommand {
    Server server;
    CommandProvider commandProvider;

    public ServerRemoveById(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
        this.commandProvider = commandProvider;
    }

    /**
     * the given id
     */
    long id;

    @Override
    public void onCall(Object additionalInput) throws IOException {

//        Set<LabWork> set = server.getSet().stream().filter((l) -> l.getId() == id).collect(Collectors.toSet());
//        set.forEach(l -> server.getSet().remove(l));
//        server.answer = ("success");

        Set<LabWork> labworks = server.getSet();
        int startSize = labworks.size();
        if (startSize > 0) {
            long id = (long) additionalInput;
            try {
                PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                        "delete from labworks where user_id = ? and id = ? returning labworks.id"
                );
                statement.setInt(1, 1);
                statement.setLong(2, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next())
                    labworks.removeAll((labworks.parallelStream().filter(labWork -> labWork.getId() == id)
                            .collect(Collectors.toCollection(HashSet::new))));
            } catch (SQLException e) {
                server.answer = "database error whoops (on remove attempt)";
            }
            if (startSize == labworks.size()) {
                server.answer = "there is no element with this id or it's not yours";
            } else {
                server.answer = "successfully removed element";
            }
        } else {
            server.answer = "the collection is empty";
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
