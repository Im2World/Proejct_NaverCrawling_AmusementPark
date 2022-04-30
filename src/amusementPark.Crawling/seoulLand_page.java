package amusementPark.Crawling;
//view�� �����;��� ��� page�� ũ�Ѹ�

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
	
	//�غ�ܰ�
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";
	public static ArrayList<ArrayList<String>> titleDateSet = new ArrayList<ArrayList<String>>();	


	
	public static void main(String[] args) throws IOException {
		File fileSave = new File ("C:\\Users\\IM\\Desktop\\javaBasicPrj\\amusementPark\\seoulland.csv"); // ���� ���� ��� ���� �� ���� ����
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileSave));
		
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		
		// ����Ʈ ����
		driver.get("https://www.naver.com/");
		
	      try {
		         Thread.sleep(500); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
		
		// ���̹� �˻�â Ŭ��
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/form/fieldset/div/input")).click();

	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		// �˻�â�� �˻��� �Է�
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/form/fieldset/div/input")).sendKeys("���﷣��");	 	//�˻�â�� �Ե����� �Է�
		
		//�˻���ư Ŭ��
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/form/fieldset/button")).click();
	
	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		//���﷣�� view �� Ŭ��
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div[1]/div/ul/li[3]/a")).click();

	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		//�ɼ� Ŭ��
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div/div[1]/div/div[3]/a")).click();
		
	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		//�Ⱓ �ɼ��� 1������ Ŭ��
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div/div[2]/ul/li[2]/div/div[1]/a[8]")).click();
	
	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		//��α׸� ���	=> ���õ� ���� �⺻����
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div/div[2]/ul/li[1]/div/div/a[2]")).click();
		
	      try {
		         Thread.sleep(1000); //
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
	      
		
		///////////////////////////////////////////////
		//ũ�Ѹ� ����
		System.out.println("ũ�Ѹ� ����");

		int cnt = 1;
		int nowSize = 0;
		int afterScrollSize = 0;
		
		//��ӹݺ�
		while(true) {
		    //������ �����
			int basicUlSize = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[1]/section/html-persist/div/more-contents/div/ul/li")).size();

			nowSize = basicUlSize;
			System.out.println("������ ����: " + basicUlSize);	//ù ȭ�鿡�� 30���� ��µȴ�.
			
			String realDate = "";		
			String realTitle = "";	//���� ���� ���ڿ�
			   
			//�ݺ��ؼ� ũ�Ѹ� ����
			// 30���� ũ�Ѹ�
			for (int i = cnt; i <= basicUlSize; i++) {
				//�Խñ� ����ޱ�
				realTitle = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/section/html-persist/div/more-contents/div/ul/li[" + i + "]/div/div/a")).getText();
				if(realTitle.contains(",")) {
					realTitle = realTitle.replace(",","*");		//���� �޸��� ���� �� �ִ�. => �޸��� ó��������Ѵ�.
				}
				
				//�Խñ� ���ڹޱ�
				realDate = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/section/html-persist/div/more-contents/div/ul/li["+ i +"]/div/div/div[1]/div[2]/span/span/span[1]/span")).getText();
				
				//�ѹ��� ���پ� �Է�
				String titleDateSet = realTitle + "," + realDate;
				
				//���Ͼ���
				bw.write(titleDateSet);
				bw.newLine();	//�� �� ���� �ٹٲ�			
			}	//for
			
			//////////��ũ�� ������
			// to perform Scroll on application using Selenium
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // ��ũ���� �� �Ʒ����� ������. => +30�� => ����ϴ� null���̴�.

			//���ð��� ��� �ε��ȴ�.
		      try {
			         Thread.sleep(3000); //
			      } catch (InterruptedException e) {
			         e.printStackTrace();
			      }
		      
			int basicUlSize2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[1]/section/html-persist/div/more-contents/div/ul/li")).size();
			
			afterScrollSize = basicUlSize2;	//�ݺ��� ���߱� ���� ī��Ʈ
			
		      
				System.out.println("���������� ũ�Ѹ�");
				cnt += 30;	//��ũ�� 1ȸ�� 30���� ������ ���� => 1~30 , 31~60, 61~90 ...

				//�������� : ��ũ�� ���� �� �����͸����� ��ȣ�� ��ũ�� ������ �� �����͸����� ��ȣ ������ ���̻� ������ ���� => ����
				if (afterScrollSize == nowSize) {
					System.out.println("ũ�Ѹ� ����");
					bw.close();
					break;			
				}
		}	//while	
	}	//main
}	//class
