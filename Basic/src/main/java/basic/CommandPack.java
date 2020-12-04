package basic;

import java.io.Serializable;

public class CommandPack implements Serializable {
    private static final long serialVersionUID = 1;
    String commandName;
    Object additionalInput;
    UserInfo ui;
    String username;
    String passwordHash;

    public CommandPack(String commandName, Object additionalInput, UserInfo ui) {
        this.commandName = commandName;
        this.additionalInput = additionalInput;
//        this.ui = ui;
        this.username = ui.getUsername();
        this.passwordHash = ui.getHashedPassword();
    }

//    public UserInfo getUi() {
//        return ui;
//    }
//
//    public void setUi(UserInfo ui) {
//        this.ui = ui;
//    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public Object getAdditionalInput() {
        return additionalInput;
    }

    public void setAdditionalInput(Object additionalInput) {
        this.additionalInput = additionalInput;
    }
}
