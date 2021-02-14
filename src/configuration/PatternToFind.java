package configuration;

import java.util.ArrayList;
import java.util.List;

public class PatternToFind {
    private static List<String> patternsList = new ArrayList<>();

    static {
        patternsList.add("Луначарского");
        patternsList.add("Либкнехта");
        patternsList.add("Малышева");
        patternsList.add("Ленина");
        patternsList.add("62/2");
    }

    public static List<String> getPatternsList() {
        return patternsList;
    }
}
