package codes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import codes.datas.UserData;

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
//			1 : 추가 등록 기능, 2 : 목록 조회, 그 외 : 잘못된 입력.
			else if (inputMenu == 1) {
				
				addPhoneNum();
				
			}
			else if (inputMenu == 2) {
				
				showAllPhoneNum();
				
			}
			else {
				System.out.println("잘못된 입력입니다. 메뉴로 돌아갑니다..");
				
//				2초 정도 프로그램 정지. => 그 후에 메뉴로.
				try {
//					예외처리 진행
					Thread.sleep(2000);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
//		종료 안내 문구
		System.out.println("프로그램을 종료합니다..");
		
	}
	
	
//	폰번 추가하는 관련 코드 함수
	static void addPhoneNum() {
		
//		 이름, 폰번, 출생년도를 순서대로 입력받자.
		 Scanner myScanner = new Scanner(System.in);
		 
		 System.out.print("이름 입력 : ");
		 String name = myScanner.next();
		 
		 System.out.print("폰번 입력 : ");
		 String phoneNum = myScanner.next();
		 
		 System.out.print("출생년도 입력 : ");
		 int birthYear = myScanner.nextInt();
		 
		 
//		 정보들을 한줄로 모아주자 (가공해주자).  "이름,폰번,년도" 형태로.
		 String content = String.format("%s,%s,%d", name, phoneNum, birthYear);

//		 System.out.println(content);
		 
		 
		 savePhoneNumToFile(content);
		 
		
	}
	
	
//	가공된 한줄을 파일에 추가해주는 함수.
	static void savePhoneNumToFile( String content  ) {
		
//		 완성된 한줄을, myPhoneBook.csv 파일에 저장하자.
		
		File myFile = new File("myPhoneBook.csv");
		
		try {
			
//			지정된 파일에, 데이터 작성을 해주는 클래스. (예외처리 필요)
//			생성자의 두번째 파라미터 : 이어붙이기가 맞다. (기존내용 보존 O)
			FileWriter fw = new FileWriter(myFile, true);
			
//			FileWriter는 2byte씩 데이터 처리. => 한 글자씩 적는다.
//			한 문장씩 적게 하는게 편하겠다. 보조 도구 활용.
			BufferedWriter bw = new BufferedWriter(fw);
			
//			보조도구로, 저장할 내용을 한번에 한줄 저장.
			bw.append(content);
//			다음줄로 내려주기.
			bw.newLine();
			
			
//			다른 경우에도 파일에 접근할 수 있게, 사용이 끝나면 파일을 닫자.
			bw.close();
			fw.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
//	모든 목록 조회하는 함수
	static void showAllPhoneNum() {
		
//		ArrayList로, 사용자데이터 UserData 목록을 담아두자.
//		UserData클래스를 추가해주자.
		
		List<UserData> userList = new ArrayList<>();
		
//		파일을 읽으면서 -> 저장된 줄들을 -> UserData로 변신. -> userList에 추가.
		addUsersByFile(userList);
		
//		userList 목록에 들어있는 항목들을 syso.
		
		for (UserData  user  : userList) {
			System.out.println(user);
		}
		
		
	}
	
	static void addUsersByFile( List<UserData> list ) {
		
//		목록을 담아주고싶은 List하나를 받아서, 거기에 추가.
		
//		파일에 적힌 내용을 list에 추가.
		
		File myFile = new File("myPhoneBook.csv");
		
		try {
//			지정된 파일을 (2byte-한글자씩) 읽어주는 클래스  -> 예외처리 필요
			FileReader fr = new FileReader(myFile);
			
//			한글자씩 읽는건 처리 불편. => 한 문장씩 String으로 뭉쳐서 읽어오게 하는 보조 도구.
			BufferedReader br = new BufferedReader(fr);
			
			
//			br 이 한줄씩 계속 읽어오게. 몇줄이나 있을지 알 수 없다. -> 무한반복 + break;
			while(true) {
				
//				한줄 읽어오기. 변수에 담자. (예외처리 필요-이미 try문 안에서 작업중.)
				String line = br.readLine();
				
//				읽어온 line이 null이라면, 다 읽어와서 더 불러올 내용이 없었다.
				
				if (line == null) {
					
//					읽어올게 없으니 반복 종료.
					break;
				}
				
//				이 줄이 실행된다 : break 안만남. -> line == null 아님. 읽어올게 있었다.
//				"이름,폰번,출생년도" 양식의 String. ->  "," 기준으로 분리하자.
				
				String[] userInfos = line.split(","); 
				
//				UserData 객체를 만들어서 -> 이름, 폰번, 출생년도를 생성자의 파라미터로 대입. -> list에 추가.
				
				list.add( new UserData(userInfos[0], userInfos[1], Integer.parseInt(userInfos[2])) );
				
				
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			
			System.out.println("아직 저장된 번호가 하나도 없습니다.");
			System.out.println("전화번호부 파일이 만들어지지 않았습니다.");
			
		} catch (IOException e) {

			System.out.println("파일은 잘 불러왔지만, 내용을 읽어올때 손상된 부분이 있습니다.");
		}
		
		
	}
	
	
}









