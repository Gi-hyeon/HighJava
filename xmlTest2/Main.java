package xml1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		
		File file = new File("./src/xml1/userList.xml");
		
		SAXParserFactory factroy = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = factroy.newSAXParser();
			PeopleSaxHandler handler = new PeopleSaxHandler();
			
			parser.parse(file, handler);
			
			List<PersonDto> pList = handler.getPersonDtoList();
			
			for(PersonDto personDto : pList) {
				
				System.out.println(personDto.toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}