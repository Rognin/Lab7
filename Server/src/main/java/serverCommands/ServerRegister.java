package serverCommands;

import basic.UserInfo;
import server.ClientHandler;
import server.CommandProvider;
import server.DataBaseHandler;

import java.io.IOException;
import java.sql.*;

public class ServerRegister extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;

    public ServerRegister(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        UserInfo ui = (UserInfo) additionalInput;
        String currentUsername = ui.getUsername();
        String currentPasswordHash = ui.getHashedPassword();
        DataBaseHandler dbh = commandProvider.getDataBaseHandler();

        try {
            PreparedStatement statement = dbh.getConnection().prepareStatement(
                    "insert into users (username, password_hash) values (?, ?) "
            );
            statement.setString(1, currentUsername);
            statement.setString(2, currentPasswordHash);
            try {
                statement.execute();
            } catch (SQLException e) {
                clientHandler.answer = "username already taken, try another";
                e.printStackTrace();
            }
            if (clientHandler.answer.equals("username already taken, try another")) {

            } else {
                clientHandler.answer = "successfully registered user";
            }

        } catch (SQLException e) {
            clientHandler.answer="error on registration";
            e.printStackTrace();
        }

    }
}
