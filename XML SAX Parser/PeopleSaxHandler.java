package xml1;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PeopleSaxHandler extends DefaultHandler {

	// 파싱한 사람객체를 넣을 리스트
	private List<PersonDto> personDtoList;
	
	// 파싱한 사람객체
	private PersonDto personDto;
	
	// character 메소드에서 자장할 문자열 변수
	private String str;
	
	public PeopleSaxHandler() {
		personDtoList = new ArrayList<PersonDto>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		// 시작 태그를 만났을 때 발생하는 이벤트
		if(qName.equals("person")) {
			personDto = new PersonDto();
			personDtoList.add(personDto);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		// 끝 태그를 만났을 때
		if(qName.equals("age")) personDto.setAge(Integer.parseInt(str));
		else if(qName.equals("name")) personDto.setName(str);
		else if(qName.equals("gender")) personDto.setGender(str);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		// 태그와 태그 사이의 내용을 처리
		str = new String(ch, start, length);
	}

	public List<PersonDto> getPersonDtoList() {
		return personDtoList;
	}

	public void setPersonDtoList(List<PersonDto> personDtoList) {
		this.personDtoList = personDtoList;
	}
}