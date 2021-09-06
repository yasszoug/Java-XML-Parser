import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLParser {

    /*
    1- Get Document Builder ( Design Patterns "Builder" + "Factory")
    2- Get Document
    3- Normalize the xml structure
    4- Get all the elements by tag names
     */



    public static void main(String[] args){

        //    1- Get Document Builder ( Design Patterns "Builder" + "Factory")

        DocumentBuilderFactory Factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder Builder = Factory.newDocumentBuilder();

            // Get Document
            Document document = Builder.parse(new File("laptops.xml"));

            // Normalize xml structure

            document.getDocumentElement().normalize();

            // Get All the nodes by tag names

            NodeList laptopList = document.getElementsByTagName("laptop");

            for(int i=0; i< laptopList.getLength(); i++)
            {
                Node laptop = laptopList.item(i);

                if(laptop.getNodeType()== Node.ELEMENT_NODE){
                    Element laptopElement = (Element) laptop;
                    System.out.println("Laptop Name: " +laptopElement.getAttribute("name"));

                    NodeList laptopDetails = laptop.getChildNodes();

                    for(int j=0; j<laptopDetails.getLength(); j++){
                        Node detail = laptopDetails.item(j);
                        if(detail.getNodeType()== Node.ELEMENT_NODE){
                            Element detailElement = (Element) detail;
                            System.out.println("     " +detailElement.getTagName() + ": "+detailElement.getAttribute("value"));
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }
}
