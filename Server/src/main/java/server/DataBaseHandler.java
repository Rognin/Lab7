package server;

import basic.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;

public class DataBaseHandler {

    private Connection connection;

    public boolean connectToDataBase(String host, int port, String dataBaseName, String user, String password) {
        String databaseUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dataBaseName;
        try {
            connection = DriverManager.getConnection(databaseUrl, user, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean initTables() {
        try {
            Statement statement = connection.createStatement();
            //users
            statement.execute("create table if not exists users (" +
                    "id serial primary key not null, username text unique , password_hash text)"
            );

            //Color
            statement.execute("CREATE TABLE if not exists colors " +
                    "(Id serial primary key not null ,name varchar(20) NOT NULL UNIQUE )");
            Color[] colors = {Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE, Color.BROWN};

            try {
                for (Color color : colors)
                    statement.execute("insert into colors(name) values('" + color + "') ");
            } catch (SQLException e) {
                //ignore
            }

            //Difficulty
            statement.execute("CREATE TABLE if not exists difficulties " +
                    "(Id serial primary key not null ,difficultyname varchar(20) NOT NULL UNIQUE )");
            Difficulty[] difficulties =
                    {Difficulty.HARD, Difficulty.HOPELESS, Difficulty.IMPOSSIBLE, Difficulty.VERY_EASY, Difficulty.VERY_HARD};
            try {
                for (Difficulty difficulty : difficulties)
                    statement.execute("insert into difficulties(difficultyname) values('" + difficulty + "') ");
            } catch (SQLException e) {
                //ignore
            }
            //Labwork
            statement.execute("create table if not exists labworks " +
                    "(id serial primary key not null , name text, x double precision,y double precision," +
                    "creationDate timestamp,minimalPoint double precision, description text," +
                    "difficulty_id  int,user_id int," +
                    "foreign key (difficulty_id) references difficulties(id)," +
                    "foreign key (user_id) references users(id))"
            );
            //Person (authors)
            statement.execute("create table if not exists authors " +
                    "(id serial primary key not null, authorName text, height int, weight int, passportID text, " +
                    "authorEyeColor_id int," +
                    "foreign key (authorEyeColor_id) references colors(id)," +
                    "foreign key (id) references labworks(id) on delete cascade)"
            );

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getUserId(String username) {
        int userId = -1;
        try {
            PreparedStatement s = connection
                    .prepareStatement("select id from users where (username =?)");
            s.setString(1, username);
            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) userId = resultSet.getInt("id");
        } catch (SQLException e) {
            //ignore
        }
        return userId;
    }

    public Connection getConnection() {
        return connection;
    }

}