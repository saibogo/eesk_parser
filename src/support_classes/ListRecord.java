package support_classes;

import java.util.ArrayList;
import java.util.List;

public class ListRecord  {

    private final List<String> stringList;

    public ListRecord() {
        this.stringList = new ArrayList<>();
    }

    public List<String> getList() {
        return this.stringList;
    }

    public boolean add(String s) {
        this.stringList.add(s);
        return true;
    }

    public int size() {
        return this.stringList.size();
    }

    public String get(int index) {
        return this.stringList.get(index);
    }

}
