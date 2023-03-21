package kr.or.ddit.basic.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.basic.mvc.service.IMemberService;
import kr.or.ddit.basic.mvc.service.MemberServiceImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;

public class MemberController {
	private Scanner scanner;
	private IMemberService service; // Service 객체 변수 선언
	
	public MemberController() {
		scanner = new Scanner(System.in);
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}
	
	// 작업을 시작하는 메서드
	private void startMember() {
		// TODO Auto-generated method stub
		while (true) {
			int selectNum = displayMenu();

			switch (selectNum) {
			case 1:
				insertMember();
				break;
			case 2:
				deleteMember();
				break;
			case 3:
				updateMember();
				break;
			case 4:
				displayAllMember();
				break;
			case 5:
				updateMember2();
				break;
			case 6:
				updateMember3();
				break;
			case 0:
				System.out.println("종료...");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요.");
				break;
			}
		}
	}
	
	// 회원 정보 수정하기 -> 입력한 항목만 수정하기
	private void updateMember3() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.println("회원ID > ");
		String id = scanner.nextLine();
		
		int count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		} 
		
		// key값 : 수정할 컬럼명, value값 : 수정할 데이터 값
		// 수정할 데이터 값이 있을 때만 Map에 추가된다.
		Map<String,String> dataMap = new HashMap<>();
		
		System.out.print("새로운 비밀번호 > ");
		String newPass = scanner.nextLine().trim(); //""
		if (!"".equals(newPass)) {
			dataMap.put("mem_pass", newPass);
		}
		
		System.out.print("새로운 회원이름 > ");
		String newName = scanner.nextLine().trim();
		if (!"".equals(newName)) {
			dataMap.put("mem_name", newName);
		}
		
		System.out.print("새로운 전화번호 > ");
		String newTel = scanner.nextLine().trim();
		if (!"".equals(newTel)) {
			dataMap.put("mem_tel", newTel);
		}
		
		System.out.print("새로운 회원주소 > ");
		String newAddr = scanner.nextLine().trim();
		if (!"".equals(newAddr)) {
			dataMap.put("mem_addr", newAddr);
		}
		
		// Map에 검색할 회원ID값을 "memId"키 값으로 넣어준다.
		dataMap.put("memId", id);
		
		int cnt = service.updateMember3(dataMap);
		
		if (cnt > 0) {
			System.out.println(id + " 회원 정보 수정 완료!!!");
		} else {
			System.out.println(id + " 회원 정보 수정 실패...");
		}
	}
	// 회원 정보를 수정하는 메서드 -> 원하는 정보를 선택해서 수정하기
	private void updateMember2() {
		String updateData = "";
		int update = 0;
		String updateFieldTitle = "";
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID > ");
		String id = scanner.nextLine();
		
		int count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		} 
		
		int select;
		String updateField = "";
		do {
			System.out.println();
			System.out.println("수정할 정보를 선택하세요...");
			System.out.print("1. 비밀번호\n2. 이름\n3. 전화번호\n4. 주소\n");
			System.out.print("입력 > ");
			select = Integer.parseInt(scanner.nextLine());
			
			switch (select) {
			case 1: updateField = "MEM_PASS"; updateFieldTitle = "비밀번호"; break;
			case 2: updateField = "MEM_NAME"; updateFieldTitle = "이름"; break;
			case 3: updateField = "MEM_TEL"; updateFieldTitle = "전화번호"; break;
			case 4: updateField = "MEM_ADDR"; updateFieldTitle = "주소"; break;
			default:
				break;
			}
			
		} while (select < 1 || select > 4);
		
		System.out.println();
		System.out.print("새로운 " + updateFieldTitle + " 입력 > ");
		updateData = scanner.nextLine();
		
		// 구성한 데이터를 Map에 추가한다.
		// Map의 정보 -> Key값 : 수정할 컬럼명(field), 수정할 데이터(data), 검색할 회원ID(memId)
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("field", updateField); // 수정할 컬럼명
		paramMap.put("data", updateData);   // 수정할 데이터
		paramMap.put("memId", id);          // 검색할 회원ID
		
		int cnt = service.updateMember2(paramMap);
		
		if (cnt > 0) {
			System.out.println(id + " 회원 정보 수정 완료!!!");
		} else {
			System.out.println(id + " 회원 정보 수정 실패...");
		}
		
	}

	// 전체 회원 정보를 출력하는 메서드
	private void displayAllMember() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("--------------------------------------------------------------");
		System.out.println("  ID     비밀번호         이름         전화번호           주소");
		System.out.println("--------------------------------------------------------------");
		
		List<MemberVO> memList = service.getAllMember();
		
		if (memList == null || memList.size() == 0) { // 데이터가 없을 때
			System.out.println();
			System.out.println("  등록된 정보가 하나도 없습니다...");
			System.out.println();
		} else {
			// 반복문을 이용하여 출력한다.
			for (MemberVO memVo : memList) {
				System.out.printf("%5s", memVo.getMem_id()); 
				System.out.printf("%10s", memVo.getMem_pass());
				System.out.printf("%13s", memVo.getMem_name());
				System.out.printf("%20s", memVo.getMem_tel());
				System.out.printf("%9s", memVo.getMem_addr());
				System.out.println();
			}
		}
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("출력 끝");
	}

	// 회원정보를 수정하는 메서드 - 전체 항목 수정
	private void updateMember() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.println("회원ID > ");
		String id = scanner.nextLine();
		
		int count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		} 
		
		System.out.print("비밀번호 > ");
		String pass = scanner.nextLine();
		
		System.out.print("회원이름 > ");
		String name = scanner.nextLine();
		
		System.out.print("전화번호 > ");
		String tel = scanner.nextLine();
		
		System.out.print("회원주소 > ");
		String addr = scanner.nextLine();
		
		// 입력한 자료를 VO 객체에 셋팅한다.
		MemberVO memVo = new MemberVO(); // VO 객체 생성
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);
		
		int cnt = service.updateMember(memVo);
		
		if (cnt > 0) {
			System.out.println(id + " 회원 정보 수정 완료!!!");
		} else {
			System.out.println(id + " 회원 정보 수정 실패...");
		}
	}

	// 회원정보를 삭제하는 메서드
	private void deleteMember() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.println("회원ID > ");
		String id = scanner.nextLine();
		
		int cnt = service.deleteMember(id);
		
		if (cnt > 0) {
			System.out.println("회원ID가 " + id + "인 회원 정보 삭제 성공!!!");
		} else {
			System.out.println(id + " 회원은 없는 회원이거나 삭제 작업에 실패했습니다.");
		}
	}

	// 자료를 추가하는 메서드
	private void insertMember() {
		// TODO Auto-generated method stub
		String id = null;
		int count = 0;

		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");

		// 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다).
		do {
			System.out.print("회원ID > ");
			id = scanner.nextLine();
			count = service.getMemberCount(id);

			if (count > 0) {
				System.out.println(id + "은(는) 이미 등록된 회원 ID입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
			}

		} while (count == 1);

		System.out.print("비밀번호 > ");
		String pass = scanner.nextLine();

		System.out.print("회원이름 > ");
		String name = scanner.nextLine();

		System.out.print("전화번호 > ");
		String tel = scanner.nextLine();

		System.out.print("회원주소 > ");
		String addr = scanner.nextLine();
		
		// 입력한 자료를 VO객체에 담는다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);
		
		// Service의 insertMember() 메서드를 호출해서 실행한다.
		int cnt = service.insertMember(memVo);
		
		if (cnt > 0) {
			System.out.println(id + " 회원 정보 추가 완료!!!");
		} else {
			System.out.println(id + " 회원 정보 추가 실패...");
		}
		
		
	}

	// 메뉴를 출력하고 선택한 작업번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println("------------------------------");
		System.out.println("1. 자 료 추 가");
		System.out.println("2. 자 료 삭 제");
		System.out.println("3. 자 료 수 정(전체항목수정)");
		System.out.println("4. 전 체 자 료 출 력");
		System.out.println("5. 자 료 수 정2(수정항목선택)");
		System.out.println("6. 자 료 수 정3(입력항목만수정)");
		System.out.println("0. 작 업 끝.");
		System.out.println("------------------------------");
		System.out.print("작업 선택 > ");
		return Integer.parseInt(scanner.nextLine());
	}	
}
