package configuration;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import support_classes.ExitStatus;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatternToFind {
    private static final List<String> patternsList = new ArrayList<>();

    static {

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse("patterns.xml");
            Node root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() != Node.TEXT_NODE) {
                    Node child = nodeList.item(i).getFirstChild();
                    patternsList.add(child.getNodeValue());
                }
            }
        } catch (ParserConfigurationException | SAXException  e) {
            System.out.println(ExitStatus.ERROR_FILE_FORMAT + " " + "patterns.xml");
            System.exit(ExitStatus.ERROR_FILE_FORMAT.ordinal());
        } catch (IOException e) {
            System.out.println(ExitStatus.ERROR_OPEN_FILE + " " + "patterns.xml");
            System.exit(ExitStatus.ERROR_OPEN_FILE.ordinal());
        }

    }

    public static List<String> getPatternsList() {
        return patternsList;
    }
}
