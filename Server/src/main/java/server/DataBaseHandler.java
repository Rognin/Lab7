package server;

import basic.*;

import java.sql.*;
import java.util.Random;

public class DataBaseHandler {

    private Connection connection;

    /**
     * Проводит подключение к базе данных
     *
     * @param host         Адрес для подключения к базе
     * @param port         Порт подключения
     * @param dataBaseName Имя базы данных
     * @param user         Имя пользователя в базе
     * @param password     Пароль пользователя
     * @return Статус подключения
     */
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

    /**
     * Инициализирует таблицы в базе
     *
     * @return Статус инифиализации
     */
    public boolean initTables() {
        try {
            Statement statement = connection.createStatement();
            //users
            statement.execute("create table if not exists users (" +
                    "id serial primary key not null, username text unique , password_hash bytea)"
            );
            //user status
            statement.execute("CREATE TABLE if not exists statuses " +
                    "(Id serial primary key not null ,name varchar(20) NOT NULL UNIQUE )");
            String[] statusList = {"reg", "npass"};

            try {
                for (String status : statusList)
                    statement.execute("insert into statuses(name) values('" + status + "') ");
            } catch (SQLException e) {
                //ignore
            }
            statement.execute("create table if not exists userstatus (" +
                    "id serial primary key not null, statusid int, code text," +
                    "foreign key (id) references users(id) on delete cascade," +
                    "foreign key (statusid) references statuses(id) on delete cascade)"
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
                for (Difficulty difficulty: difficulties)
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

    /**
     * Ищет id пользователя по заданному имени
     *
     * @param loginName Логин или email Пользователя
     * @return id Пользователя или -1, если пользователь не найден
     */
    public int getUserId(String loginName) {
        int userId = -1;
        try {
            PreparedStatement s = connection
                    .prepareStatement("select id from users where (email = ? or username =?)");
            s.setString(1, loginName);
            s.setString(2, loginName);
            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) userId = resultSet.getInt("id");
        } catch (SQLException ignore) {
        }
        return userId;
    }

    public String userStatusReg(int userId) {
        return setStatus(userId, "reg");
    }

    public String userStatusNewPass(int userId) {
        return setStatus(userId, "npass");
    }

    /**
     * Устанавливает Новый статус пользователя
     *
     * @param userId Id пользователя
     * @param status Статус (пока доступен только reg и npass)
     * @return
     */
    private String setStatus(int userId, String status) {
        try {
            clearStatus(userId);
            PreparedStatement statement2 = connection.prepareStatement("insert into userstatus(id,statusid,code) " +
                    "values (?,(select id from statuses where name = ?),? )");
            statement2.setInt(1, userId);
            statement2.setString(2, status);
            statement2.setString(3, randomString());
            ResultSet resultSet = statement2.executeQuery();
            if (resultSet.next()) return resultSet.getString("name");
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public String getUserStatus(int id) {
        try {
            PreparedStatement s = connection
                    .prepareStatement("select name from statuses where id = " +
                            "( select statusid from userstatus where id = " + id + " )");
            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) return resultSet.getString("name");
        } catch (SQLException ignore) {
        }
        return null;
    }

    public String getUserCode(int id) {
        try {
            PreparedStatement s = connection
                    .prepareStatement("select code from userstatus where id =" + id);
            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) return resultSet.getString("code");
        } catch (SQLException ignore) {
        }
        return null;
    }

    /**
     * Генерирует случайную строку
     *
     * @return Случайная строка
     */
    private static String randomString() {
        char[] chs = "ZXCVBNMASDFGHJKLQWERTYUIOP1234567890zxcvbnmasdfghjklqwertyuiop".toCharArray();
        String number = new String();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            number = number + (chs[random.nextInt(chs.length)]);
        }
        return number;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isReg(int uderId) {
        return "reg".equals(getUserStatus(uderId));
    }

    /**
     * Очищает статус пользователя
     *
     * @param userId id вользавотеля
     * @return Статус операции
     */
    public boolean clearStatus(int userId) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("delete from userstatus where id = " + userId);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean isNewPass(int uderId) {
        return "npass".equals(getUserStatus(uderId));
    }

//    public boolean checkAccount(Command command) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "select * from users where (email = ? or username =?) and password_hash = ?"
//            );
//            statement.setString(1, command.getLogin());
//            statement.setString(2, command.getLogin());
//            statement.setBytes(3, command.getPassword().getBytes());
//            ResultSet resultSet = statement.executeQuery();
//            return resultSet.next();
//        } catch (SQLException e) {
//            logger.error(e);
//            return false;
//        }
//    }

    /**
     * Проверяет валидность email адреса
     *
     * @param email email для проверки
     * @return Статус проверки
     */
    public boolean checkEmail(String email) {
        try {
            String[] login = email.split("@");
            if (login.length != 2) return false;
            String[] address = login[1].split("\\.");
            if (address.length != 2) return false;
        } catch (Exception ignored) {
        }
        return true;
    }
    
    
}