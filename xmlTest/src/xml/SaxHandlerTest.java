package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class Person {
	private int age;
	private String name;
	private String gender;
	private String role;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return String.format("이름: %s, 나이: %d, 성별: %s, 역할: %s", name, age, gender, role);
	}
}

// SAX를 통해 파싱을 하기 위해서는 먼저 DefaultHandler를 상속받는 Handler가 필요
// DefaultHandler 클래스의 오버라이드 된 메소드를 사용,
// SaxHandler가 XML 데이터를 파싱하여 전달
class Saxhandler extends DefaultHandler{
	// 파싱한 사람객체를 넣을 리스트
	private List<Person> personList;
	
	// 파싱한 사람 객체
	private Person person;
	
	// character 메소드에서 저장할 문자열 변수
	private String str;
	
	// 생성자
	public Saxhandler() {
		super();
		personList = new ArrayList<>();
	}
	
	// 내부
	
	// 태그를 처음 만나면 발생하는 이벤트
	@Override
	public void startElement(String uri, String localName, String name, org.xml.sax.Attributes att)
			throws SAXException {
		// TODO Auto-generated method stub
		if (name.equals("person")) {
			person = new Person();
			personList.add(person);
		}
	}
	
	// 끝 태그를 만났을 때 발생하는 이벤트 
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		// TODO Auto-generated method stub
		if (name.equals("age")) {
			person.setAge(Integer.parseInt(str));
		} else if (name.equals("name")) {
			person.setName(str);
		} else if (name.equals("gender")) {
			person.setGender(str);
		} else if (name.equals("role")) {
			person.setRole(str);
		}
	}
	
	// 태그와 태그 사이의 내용을 처리 -> 태그와 태그 사이의 text(내용)을 처리하기 위한 이벤트
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		str = new String(ch, start, length);
	}
	
	public List<Person> getPersonList(){
		return personList;
	}
	
	public void setPersonList(List<Person> personList) {
		this.personList=personList;
	}
	
}

public class SaxHandlerTest {
	public static void main(String[] args) {
//		File file2 = new File(".");
//		System.out.println(file2.getAbsolutePath());
		
		File file = new File("./src/xml/person.xml");
		// SAXParserFactory instance 객체 생성
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			// SAXParser 객체 생성
			SAXParser parser = factory.newSAXParser();
			
			// handler 객체 생성
			Saxhandler handler = new Saxhandler();
			
			// parse 메소드에 person.xml을 보내면 handler를 통해 파싱된다.
			parser.parse(file, handler);
			
			// getPersonList는 saxhandler 클래스에서 추가된 'person' 객체들을 담고있는 리스트를 반환한다.
			List<Person> list = handler.getPersonList();
			
			// 파싱된 데이터를 통해 person의 정보를 가지고온다. 
			for (Person person : list) {
				System.out.println(person);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
