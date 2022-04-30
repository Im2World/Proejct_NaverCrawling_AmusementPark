package amusementPark.Crawling;
//view는 데이터양이 적어서 page로 크롤링

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class seoulLand_page {
	
	//준비단계
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";
	public static ArrayList<ArrayList<String>> titleDateSet = new ArrayList<ArrayList<String>>();	


	
	public static void main(String[] args) throws IOException {
		File fileSave = new File ("C:\\Users\\IM\\Desktop\\javaBasicPrj\\amusementPark\\seoulland.csv"); // 파일 저장 경로 지정 및 파일 생성
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileSave));
		
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		
		// 사이트 진입
		driver.get("https://www.naver.com/");
		
	      try {
		         Thread.sleep(500); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
		
		// 네이버 검색창 클릭
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/form/fieldset/div/input")).click();

	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		// 검색창에 검색어 입력
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/form/fieldset/div/input")).sendKeys("서울랜드");	 	//검색창에 롯데월드 입력
		
		//검색버튼 클릭
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/form/fieldset/button")).click();
	
	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		//서울랜드 view 탭 클릭
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div[1]/div/ul/li[3]/a")).click();

	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		//옵션 클릭
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div/div[1]/div/div[3]/a")).click();
		
	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		//기간 옵션은 1년으로 클릭
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div/div[2]/ul/li[2]/div/div[1]/a[8]")).click();
	
	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		//블로그만 출력	=> 관련도 순이 기본정렬
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div/div[2]/ul/li[1]/div/div/a[2]")).click();
		
	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		
		///////////////////////////////////////////////
		//크롤링 시작
		System.out.println("크롤링 시작");

		int cnt = 1;
		int nowSize = 0;
		int afterScrollSize = 0;
		
		//계속반복
		while(true) {
		    //데이터 저장부
			int basicUlSize = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[1]/section/html-persist/div/more-contents/div/ul/li")).size();

			nowSize = basicUlSize;
			System.out.println("데이터 개수: " + basicUlSize);	//첫 화면에는 30개만 출력된다.
			
			String realDate = "";		
			String realTitle = "";	//제목 담을 문자열
			   
			//반복해서 크롤링 시작
			// 30개씩 크롤링
			for (int i = cnt; i <= basicUlSize; i++) {
				//게시글 제목받기
				realTitle = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/section/html-persist/div/more-contents/div/ul/li[" + i + "]/div/div/a")).getText();
				if(realTitle.contains(",")) {
					realTitle = realTitle.replace(",","*");		//제목에 콤마가 있을 수 있다. => 콤마를 처리해줘야한다.
				}
				
				//게시글 일자받기
				realDate = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/section/html-persist/div/more-contents/div/ul/li["+ i +"]/div/div/div[1]/div[2]/span/span/span[1]/span")).getText();
				
				//한번에 한줄씩 입력
				String titleDateSet = realTitle + "," + realDate;
				
				//파일쓰기
				bw.write(titleDateSet);
				bw.newLine();	//한 줄 쓰고 줄바꿈			
			}	//for
			
			//////////스크롤 내리기
			// to perform Scroll on application using Selenium
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 스크롤을 맨 아래까지 내린다. => +30개 => 출력하니 null값이다.

			//대기시간을 줘야 로딩된다.
		      try {
			         Thread.sleep(3000); //
			      } catch (InterruptedException e) {
			         e.printStackTrace();
			      }
		      
			int basicUlSize2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[1]/section/html-persist/div/more-contents/div/ul/li")).size();
			
			afterScrollSize = basicUlSize2;	//반복문 멈추기 위한 카운트
			
		      
				System.out.println("다음페이지 크롤링");
				cnt += 30;	//스크롤 1회당 30개씩 데이터 증가 => 1~30 , 31~60, 61~90 ...

				//종료조건 : 스크롤 내린 후 데이터마지막 번호와 스크롤 내리기 전 데이터마지막 번호 같으면 더이상 데이터 없다 => 종료
				if (afterScrollSize == nowSize) {
					System.out.println("크롤링 종료");
					bw.close();
					break;			
				}
		}	//while	
	}	//main
}	//class
