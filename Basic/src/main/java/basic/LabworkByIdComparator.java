package basic;

import java.util.Comparator;

public class LabworkByIdComparator implements Comparator<LabWork> {

    @Override
    public int compare(LabWork o1, LabWork o2) {
        return (int) (o1.getId()-o2.getId());
    }
}
