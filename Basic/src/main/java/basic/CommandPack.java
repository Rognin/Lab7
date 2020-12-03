package basic;

import java.io.Serializable;

public class CommandPack implements Serializable {
    String commandName;
    Object additionalInput;

    public CommandPack(String commandName, Object additionalInput) {
        this.commandName = commandName;
        this.additionalInput = additionalInput;
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
