package xml1;

public class PersonDto {
	
	private int age;
	private String name;
	private String gender;
	
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
	
	@Override
	public String toString() {
		return "이름:"+name+" 나이:"+age+" 성별:"+gender+"\n";
	}
}