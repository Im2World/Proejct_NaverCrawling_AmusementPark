package amusementPark.wordAnalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

public class wordAnalysis_Comment {

	static String name = "seoulland";
	static ArrayList<String> word = new ArrayList<>();		//단어 담을 배열

	///////////////메인/////////////////
	public static void main(String[] args) throws IOException {
		FileRead();
		FileWrite();
	}
	
	//단어빈도수 분석
	//String strToExtrtKwrd 에 readLine 들어감. => 해당 문장을 단어로 쪼개 분석
	//kkma default값임
	public static void keTest(String strToExtrtKwrd) throws IOException {
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);

		for (int i = 0; i < kl.size(); i++) {
			Keyword kwrd = kl.get(i);
//			System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());	//출력은 tab으로 구분해서 출력

			word.add(kwrd.getString());	//쪼갠 단어들만 저장
		}
	}
	
	//파일 읽기
	//파일 읽어서 keTest를 FileRead 메서드에서 실행함.
	//파일 읽어서 keTest로 단어 빈도 분석 => [word, cnt] 생성 => 각 ArrayList에 저장
	public static void FileRead() throws IOException {
		File file = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\amusementPark\\" + name + ".csv");
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;	//읽은 라인을 담아줄 변수
		
		while ((line = br.readLine()) != null) {
			line.trim();		//해당 단어 앞 뒤 공백 잘라줌
			keTest(line);
		}
		br.close();
	}


	//파일들을 파일 이름명만 바꿔서 여러개 쓴다.
	public static void FileWrite() throws IOException {
		String[] wordArr = new String [word.size()];
		ArrayList <String> wordNCnt = new ArrayList<>();
		
		//어레이리스트 값을 어레이에 담기.
		for (int i = 0; i < word.size(); i++) {
			wordArr[i] = word.get(i);
		}
		
		//**********name 부분에 lotteworld, everland, seoulland 가 들어간다. ************* 
		File file = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\amusementPark\\" + name + "_DataAnalysis.csv"); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		/////////단어 빈도수 계산/////////////
		//**********ArrayList값을 모두 일반 array에 담아야 HashMap 사용가능.
		//HasgMap은 일반 Arr에서만 작동!
		Map<String, Integer> wordMap = new HashMap<>();
		Map<String, Integer> result = new HashMap<>();
		
		
		for(int i = 0; i < wordArr.length; i++) {
			if(wordMap.containsKey(wordArr[i])) {
				int value = (int)wordMap.get(wordArr[i]);
				wordMap.put(wordArr[i], value + 1);	//기존에 있는 키는 기존 값 +1
			} else {
				wordMap.put(wordArr[i], 1);	//기존에 없는 키는 값을 1로 저장
			}
		}
		
		System.out.println();
		System.out.println("빈도수 높은 단어 내림차순 출력 : ");
		//key값을 기준으로 내림차순 정렬하기
		List<String> listKeySet = new ArrayList<>(wordMap.keySet());
        Collections.sort(listKeySet, (value1, value2) -> (wordMap.get(value2).compareTo(wordMap.get(value1))));
        for(String key : listKeySet) {
        	StringBuffer sb = new StringBuffer();
        	sb.append(key + "," + wordMap.get(key));
            System.out.println(key + " => " + wordMap.get(key));
            bw.write(sb.toString());
            bw.newLine();
        }
	
		System.out.println("파일저장완료");
		bw.flush();
		bw.close();
	}
} //class