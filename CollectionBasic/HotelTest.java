package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelTest {
	private Map<Integer, Room> map;
	private Scanner scanner;
	
	public HotelTest() {
		map = new HashMap<>();
		scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new HotelTest().start();
	}

	private void start() {
		System.out.println("*********************************************");
		System.out.println("       호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		while (true) {
			int selectNum = disMenu();
			switch (selectNum) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				room();
				break;
			case 4:
				System.out.println("*********************************************");
				System.out.println("       호텔문을 닫았습니다");
				System.out.println("*********************************************");
				return;
			default:
				System.out.println("올바른 번호를 입력하세요.");
				break;
			}
		}
	}

	private void checkIn() {
		// TODO Auto-generated method stub
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방 번호 입력 > ");
		
		int roomNum = Integer.parseInt(scanner.nextLine());
		String roomType = "";
		if(roomNum >= 201 && roomNum <= 209) {
			roomType = "싱글룸";
		} else if (roomNum >= 301 && roomNum <= 309) {
			roomType = "더블룸";
		} else if (roomNum >= 401 && roomNum <= 409) {
			roomType = "스위트룸";
		} else {
			System.out.println(roomNum +  "호 객실은 존재하지 않습니다.");
			return;
		}
		if (map.containsKey(roomNum)) {
			System.out.println(roomNum + "호 객실에는 체크인 한 사람이 있습니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 > ");
		String name = scanner.nextLine();
		map.put(roomNum, new Room(roomNum, roomType, name));
//		System.out.println(map.get(404));
		
	}
	
	private void checkOut() {
		// TODO Auto-generated method stub
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 > ");
		int roomNum = Integer.parseInt(scanner.nextLine());
		if (map.containsKey(roomNum)) {
			Room room = map.get(roomNum);
			System.out.printf("%s호 객실의 %s님 체크아웃을 완료하였습니다.\n", room.getRoomNum(), room.getName());
			map.remove(roomNum);
			
		} else if(roomNum >= 201 && roomNum <= 209 || roomNum >= 301 && roomNum <= 309 || roomNum >= 401 && roomNum <= 409){
			System.out.println(roomNum + "호 객실에는 체크인 한 사람이 없습니다.");
		} else {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
		}
	}
	
	private void room() {
		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	 방 종류	 투숙객 이름");
		System.out.println("----------------------------------------------");
		for(int i=1; i<=3; i++) {
			int roomNum = 0;
			String roomType = "";
			for(int j=1; j<=9; j++) {
				if(i==1) {
					roomNum = 200;
					roomType = "  싱글룸 ";
				} else if (i == 2) {
					roomNum = 300;
					roomType = "  더블룸 ";
				} else {
					roomNum = 400;
					roomType = "스위트룸";					
				}
				if(map.get(roomNum+j) == null) {
					System.out.printf("  %d   %5s  		-", roomNum+j, roomType);
					System.out.println();					
				} else {
					System.out.printf("  %d   %5s  %14s", roomNum+j, roomType, map.get(roomNum+j));
					System.out.println();					
				}
			}
		}
	}

	private int disMenu() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택 > ");
		return Integer.parseInt(scanner.nextLine());
	}
}

class Room{
	private int roomNum;
	private String roomType;
	private String name;
	
	public Room() {}
	
	public Room(int roomNum, String roomType) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
	}

	public Room(int roomNum, String roomType, String name) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.name = name;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("%s", name);
	}

	
}