package codes.datas;

import java.util.Calendar;

public class UserData {
	
	private String name;
	private String phoneNum;
	private int birthYear;
	
	
	public int getKoreanAge() {
		
//		올해의 나이가 몇살인가?
//		올해 : 지금 일시를 불러내서 -> 그 중 연도값만 추출.
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		
		return  thisYear - this.birthYear + 1;
		
	}
	
//	toString메쏘드 오버라이딩.
	@Override
	public String toString() {

		return String.format("%s(%d세) - %s", this.name, this.getKoreanAge(), this.phoneNum);
		
	}
	
	
//	생성자 커스터마이징. 모든 데이터를 한번에 세팅.
	public UserData(String name, String phoneNum, int birthYear) {
		super();
		this.name = name;
		this.phoneNum = phoneNum;
		this.birthYear = birthYear;
	}
	
//	정보 은닉. getter / setter 메쏘드들 활용.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	

}
