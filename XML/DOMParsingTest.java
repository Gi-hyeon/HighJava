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

            // xml 파일의 person 태그를 읽어 Nodelist 객체에 저장해준다. 
            // 이 객체는 배열과 비슷한 형태를 가지고 있어 인덱스를 통해서 접근이 가능하다.
            // person을 가지고 있는 5개의 요소를 NodeList에 저장시킨다.
            NodeList list = document.getElementsByTagName("person");
            
            
            // 자식 노드를 순회하면서 출력
            // DOM 파서는 XML 문서를 트리 구조로 변환하고 각 요소는 노트로 표현된다.
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                
                // Element_node -> 요소노드로 비교하기 위해서는 저장했던 노드리스트의 값을 노드에 저장시켜줘야함
                // 요소 노드인 경우 출력  -> 요소 노드 : 요소 노드는 하위속성을 가질 수 없다.
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                	// 
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
    
    // 	person 태그를 가진 노드들이 age, name, gender, role 태그들을 가지고있다.
    // 이 태그들을 가지고와 각 엘리먼트의 요소들을 출력한다.
    private static String getValue(String tag, Element element) {
    	// getElementsByTagName() 메소드는 특정한 태그명을 가진 노드들을 NodeList 형태로 반환한다.
    	// 그 후 item(0) 메서드를 호출하여 첫 번째 자식 노드를 가져온 후
    	// getNodeValue() 메서드를 통해 해당 노드의 값을 얻어온다.
//        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
//        Node node = (Node) nodes.item(0);
//        return node.getNodeValue();
    	
    	
    	// 해당 Element에서 지정한 태그명을 가진 노드를 찾아내고
    	// item(0) -> 그 중 첫번째 노드를 선택한다  -> 첫번째 노드의 모든 텍스트 내용을 가져와서 반환한다.
    	return element.getElementsByTagName(tag).item(0).getTextContent();
    }
}

// DOM 파서는 문서를 통째로 읽어 메모리에 로드하기 때문에 각각의 객체로 저장한다 -> 각각 고유한 노드를 가짐

/*
 * 'NodeList'에서 'Node'를 가져온 후, 해당 'Node'가 'Element' 노드인지 확인해야 합니다. 
 * 'Element' 노드가 맞다면, 'Element'로 형변환을 해준 후 
 * 'getValue' 메서드를 호출하여 해당 'Element'에서 지정한 태그명을 가진 첫 번째 자식 노드를 찾아내서 반환
 */


