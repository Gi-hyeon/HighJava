package kr.or.ddit.basic.mvc.controller;

import java.util.Scanner;

import kr.or.ddit.basic.mvc.service.IMemberService;
import kr.or.ddit.basic.mvc.service.MemberServiceImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;

public class MemberController {
	private Scanner scanner;
	private IMemberService service; // Service 객체 변수 선언
	
	public MemberController() {
		scanner = new Scanner(System.in);
		service = new MemberServiceImpl();
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
				//deleteMember();
				break;
			case 3:
				//updateMember();
				break;
			case 4:
				//displayAllMember();
				break;
			case 5:
				//updateMember2();
				break;
			case 6:
				//updateMember3();
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
