package serverCommands;

import server.ClientHandler;
import server.CommandProvider;
import basic.*;

import java.sql.*;
import java.time.LocalDate;

/**
 * add an element to the collection
 */
public class ServerAdd extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;

    public ServerAdd(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) {

        LabWork lw = (LabWork) additionalInput;
        lw.setCreationDate(LocalDate.now());
        lw.setUsername(clientHandler.currentUI.getUsername());

        int userId = commandProvider.getDataBaseHandler().getUserId(clientHandler.currentUI.getUsername());

        if (lw != null) {
            int id = addObjSql(lw, userId);
            lw.setId((long) id);
            if (id == -1) {
                clientHandler.answer = "failed adding to database";
            } else {
                commandProvider.getSet().add(lw);
                clientHandler.answer = "success";
            }
        } else {
            clientHandler.answer = "an empty labwork received with add command";
        }
    }

    private int addObjSql(LabWork labWork, int userId) {
        return addAuthorSQL(labWork.getAuthor(), addLabWorkSQL(labWork, userId));
    }

    private int addLabWorkSQL(LabWork lw, int userId) {
        int id = -1;
        try {
            PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                    "insert into labworks" +
                            "(name, x, y, creationdate, minimalpoint, description, difficulty_id, user_id) " +
                            "values (?,?,?,?,?,?,(select id from difficulties where difficultyname = ?),?) returning id"
            );
            statement.setString(1, lw.getName());
            statement.setFloat(2, lw.getCoordinates().getX());
            statement.setDouble(3, lw.getCoordinates().getY());
            statement.setTimestamp(4, Timestamp.valueOf(lw.getCreationDate().atStartOfDay()));
            statement.setDouble(5, lw.getMinimalPoint());
            statement.setString(6, lw.getDescription());
            statement.setString(7, lw.getDifficulty().toString());
            statement.setInt(8, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private int addAuthorSQL(Person author, int id) {
        int idAuthor = -1;
        try {
            PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                    "insert into authors" +
                            "(id,authorname, height, weight, passportid, authoreyecolor_id) " +
                            "values (?,?, ?, ?, ?, (select id from colors where name = ?)) returning id"
            );
            statement.setInt(1, id);
            statement.setString(2, author.getName());
            statement.setInt(3, author.getHeight());
            statement.setInt(4, (int) author.getWeight());
            statement.setString(5, author.getPassportID());
            statement.setString(6, author.getEyeColor().toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) idAuthor = resultSet.getInt("id");
        } catch (SQLException lal) {
            lal.printStackTrace();
        }
        return idAuthor;
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
