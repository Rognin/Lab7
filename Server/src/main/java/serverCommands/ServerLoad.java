package serverCommands;

import basic.LabWork;
import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;
import java.sql.*;
import java.util.Collections;
import java.util.Set;

import basic.*;

public class ServerLoad extends ServerCommand {

    ClientHandler clientHandler;
    CommandProvider commandProvider;

    public ServerLoad(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        Set<LabWork> labworks = Collections.synchronizedSet(commandProvider.getSet());
        try {
            PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                    "select authors.id," +
                            "       labworks.name," +
                            "       labworks.x," +
                            "       labworks.y," +
                            "       labworks.creationdate," +
                            "       labworks.minimalpoint," +
                            "       labworks.description," +
                            "       difficulties.difficultyname," +
                            "       authors.authorname," +
                            "       authors.height," +
                            "       authors.weight," +
                            "       authors.passportid," +
                            "       e.name authoreyecolor," +
                            "       u.username " +
                            "from labworks " +
                            " join authors on authors.id = labworks.id " +
                            " join colors e on authors.authoreyecolor_id = e.id " +
                            " join difficulties on labworks.difficulty_id = difficulties.id " +
                            " join users u on labworks.user_id = u.id"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Person author = new Person(resultSet.getString("authorname"),
                        resultSet.getInt("height"),
                        resultSet.getInt("weight"),
                        resultSet.getString("passportid"),
                        Color.valueOf(resultSet.getString("authoreyecolor")));
                LabWork labwork = new LabWork(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new Coordinates(resultSet.getLong("x"), resultSet.getInt("y")),
                        resultSet.getTimestamp("creationdate").toLocalDateTime().toLocalDate(),
                        resultSet.getDouble("minimalpoint"),
                        resultSet.getString("description"),
                        Difficulty.valueOf(resultSet.getString("difficultyname")),
                        author);
                labwork.setUsername(resultSet.getString("username"));
                labworks.add(labwork);
            }
            System.out.println("collection loaded successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
