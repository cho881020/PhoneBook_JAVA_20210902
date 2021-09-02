package codes;

import java.util.Scanner;

public class MainDrive {

	public static void main(String[] args) {
		
//		메뉴 띄우고 -> 입력받고 -> 종료까지를 별도 함수로 분리.
		
		printMenu();
		
	}
	
	static void printMenu() {
		
//		0번을 입력할때까지 메뉴를 계속 출력.
		
//		입력 : Scanner
		Scanner myScanner = new Scanner(System.in);
		
		while (true) {
			
//			입력 안내문을 여러줄로.
			System.out.println("======= 전화번호부 =======");
			System.out.println("1. 전화번호 추가 등록");
			System.out.println("2. 전화번호 목록 조회");
			System.out.println("0. 프로그램 종료");
			System.out.println("=======================");
			System.out.print("메뉴 입력 : ");
			
			int inputMenu = myScanner.nextInt();
			
//			입력한 숫자가 0 이다 : 반복 탈출.
			if (inputMenu == 0) {
				break;
			}
		}
		
//		종료 안내 문구
		System.out.println("프로그램을 종료합니다..");
		
	}
	
}






