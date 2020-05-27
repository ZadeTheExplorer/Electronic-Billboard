package Billboard;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXMLFile {
    public static final String xmlFilePath = "E:\\Users\\Desktop\\JavaProject\\xmlfile.xml";

    public static void main(String argv[]) {

        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("billboard");
            document.appendChild(root);
            root.setAttribute("background","#0000FF");

            // message element
            Element message = document.createElement("message");
            message.appendChild(document.createTextNode("message default"));
            root.appendChild(message);
            message.setAttribute("colour", "#FFFF00");

            // picture element
            Element picture = document.createElement("picture");
            root.appendChild(picture);
            Attr urlAttr = document.createAttribute("url");
            picture.setAttributeNode(urlAttr);
            Attr dataAttr = document.createAttribute("data");
            picture.setAttributeNode(dataAttr);

            // message element
            Element information = document.createElement("information");
            information.appendChild(document.createTextNode("information default"));
            root.appendChild(information);
            information.setAttribute("colour", "#00FFFF");

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}