package support_classes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapRecord {

    private final Map<String, String> stringMap;

    public MapRecord() {
        this.stringMap = new HashMap<>();
    }

    public boolean put(String key, String value) {
        this.stringMap.put(key, value);
        return true;
    }

    public String get(String key) {
        return this.stringMap.get(key);
    }

    public Collection<String> values() {
        return this.stringMap.values();
    }

    @Override
    public String toString() {
        return this.stringMap.toString();
    }
}
