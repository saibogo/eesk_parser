package configuration;

import java.util.ArrayList;
import java.util.List;

public class PatternToFind {
    private static List<String> patternsList = new ArrayList<>();

    static {
        patternsList.add("Луначарского");
        patternsList.add("Либкнехта");
        patternsList.add("Малышева");
    }

    public static List<String> getPatternsList() {
        return patternsList;
    }
}
