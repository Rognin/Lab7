package serverCommands;

import basic.LabWork;
import server.CommandProvider;
import server.Server;
import basic.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * update the value of the element with the given id
 */
public class ServerUpdate extends ServerCommand {
    Server server;
    CommandProvider commandProvider;

    public ServerUpdate(Server server, CommandProvider commandProvider) {
        super(server, commandProvider);
        this.server = server;
        this.commandProvider = commandProvider;
    }

    /**
     * the given id
     */
    long id;
    UpdateObjectsPack uop;

    @Override
    public void onCall(Object additionalInput) throws IOException {

        uop = (UpdateObjectsPack) additionalInput;
        LabWork lw = uop.getLw();
        id = uop.getId();
        lw.setId(id);
        lw.setCreationDate(LocalDate.now());

        boolean flag = server.getSet().stream().anyMatch(l -> l.getId() == id);

        if (flag) {
            if (updateLabworkSQL(lw, (int) id) != -1) {
                LabWork labWork = server.getSet().stream().filter(l -> l.getId() == id).findAny().get();
                server.getSet().remove(labWork);
                server.getSet().add(lw);
                server.answer = "success";
            } else {
                server.answer = "couldn't update the element in the data base";
            }
        } else {
            server.answer = ("There is no element with this id");
        }
    }

    private int updateLabworkSQL(LabWork labwork, int id) {
        int returnId = -1;
        if (updateAuthorSQL(labwork.getAuthor(), id) != -1)
            try {
                PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                        "UPDATE labworks " +
                                "SET name = ?, x=?, y=?, creationdate=?, minimalpoint=?, description=?, difficulty_id= (select id from difficulties where difficultyname = ?) " +
                                "WHERE id = ? returning id"
                );
                statement.setString(1, labwork.getName());
                statement.setFloat(2, labwork.getCoordinates().getX());
                statement.setDouble(3, labwork.getCoordinates().getY());
                statement.setTimestamp(4, Timestamp.valueOf(labwork.getCreationDate().atStartOfDay()));
                statement.setDouble(5, labwork.getMinimalPoint());
                statement.setString(6, labwork.getDescription());
                statement.setString(7, labwork.getDifficulty().toString());
                statement.setInt(8, id);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    returnId = resultSet.getInt("id");
//                    System.out.println("Updated LabWork id: " + id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return returnId;
    }

    private int updateAuthorSQL(Person author, int id) {
        int idAuthor = -1;
        try {
            PreparedStatement statement = commandProvider.getDataBaseHandler().getConnection().prepareStatement(
                    "UPDATE authors SET " +
                            "authorname = ? , height = ?, weight = ?, passportid = ? , authoreyecolor_id = (select id from colors where name = ?) where id = ? returning id"
            );

            statement.setString(1, author.getName());
            statement.setInt(2, author.getHeight());
            statement.setLong(3, author.getWeight());
            statement.setString(4, author.getPassportID());
            statement.setString(5, author.getEyeColor().toString());
            statement.setInt(6, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) idAuthor = resultSet.getInt("id");
//            System.out.println(id);
        } catch (SQLException e) {
            e.printStackTrace();
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
