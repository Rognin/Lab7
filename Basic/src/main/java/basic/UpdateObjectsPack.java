package basic;

import java.io.Serializable;

public class UpdateObjectsPack implements Serializable {
    private static final long serialVersionUID = 1;
    LabWork lw;
    long id;

    public LabWork getLw() {
        return lw;
    }

    public void setLw(LabWork lw) {
        this.lw = lw;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
