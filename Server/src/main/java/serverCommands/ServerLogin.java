package serverCommands;

import basic.UserInfo;
import server.ClientHandler;
import server.CommandProvider;
import server.DataBaseHandler;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerLogin extends ServerCommand{
    ClientHandler clientHandler;
    CommandProvider commandProvider;

    public ServerLogin(ClientHandler clientHandler, CommandProvider commandProvider) {
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
                    "select * from users where (username = ?)"
            );
            statement.setString(1, currentUsername);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                clientHandler.answer="wrong username, try again";
                return;
            }
            resultSet.next();
            if(!resultSet.getString("password_hash").equals(currentPasswordHash)){
                clientHandler.answer="wrong password, try again";
                return;
            }
            clientHandler.answer="successfully logged in";

        } catch (SQLException e) {
            clientHandler.answer="error on login";
            e.printStackTrace();
        }
    }
}
