package xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMParsingTest {
    public static void main(String[] args) {
        try {
            // 파일 생성
            File personFile = new File("./src/xml/person.xml");

            // XML 문서를 파싱하기 위한 DocumentBuilderFactory 객체 생성
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            // XML 문서를 생성하기 위한 DocumentBuilder 객체 생성
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // 파싱할 person.xml 파일을 Document 객체로 변환 
            Document document = builder.parse(personFile);
            
            // XML 문서의 루트 요소를 가져온 후 -> 일관성을 유지화하기 위해 정규화하는 메서드
            document.getDocumentElement().normalize();

            // xml 파일의 person 이라는 태그 읽기
            NodeList list = document.getElementsByTagName("person");
           
            
            // 자식 노드를 순회하면서 출력
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
            
                
                
                // 요소 노드인 경우 출력  -> 요소 노드 : 요소 노드는 하위속성을 가질 수 없다.
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.print("이름 : " + getValue("name", element));
                    System.out.print(", 나이 : " + getValue("age", element));
                    System.out.print(", 성별 : " + getValue("gender", element));
                    System.out.print(", 역할 : " + getValue("role", element));
                    System.out.println();
                }
            }


        }catch (Exception e){
            System.out.println("오류가 났습니다. " +e.getMessage());
        }
    }
    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}