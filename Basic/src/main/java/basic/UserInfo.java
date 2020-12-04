package basic;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1;
    String username;
    String hashedPassword;
//    int id;

    public UserInfo(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
