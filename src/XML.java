import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML {
	public static void main(String argv[]) throws Exception {
		Document root = XML.getRoot("./公司.xml");
		System.out.println(root.getDocumentElement().getNodeName()+"\n");
		NodeList nodeList = root.getElementsByTagName("员工");
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node nNode = nodeList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String id=eElement.getAttribute("员工号");
				String name=eElement.getElementsByTagName("名字").item(0).getTextContent();
				String salary=eElement.getElementsByTagName("工资").item(0).getTextContent();
				System.out.printf("%-3s: %-5s  %-2s  %-5s",nNode.getNodeName(),id,name,salary);
			}
			System.out.println();
		}
	}

	private static Document getRoot(String fileName) throws Exception {
		File xml = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);
		doc.getDocumentElement().normalize();
		return doc;
	}
}

