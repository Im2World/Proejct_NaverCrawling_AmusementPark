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
	static ArrayList<String> word = new ArrayList<>();		//�ܾ� ���� �迭

	///////////////����/////////////////
	public static void main(String[] args) throws IOException {
		FileRead();
		FileWrite();
	}
	
	//�ܾ�󵵼� �м�
	//String strToExtrtKwrd �� readLine ��. => �ش� ������ �ܾ�� �ɰ� �м�
	//kkma default����
	public static void keTest(String strToExtrtKwrd) throws IOException {
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);

		for (int i = 0; i < kl.size(); i++) {
			Keyword kwrd = kl.get(i);
//			System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());	//����� tab���� �����ؼ� ���

			word.add(kwrd.getString());	//�ɰ� �ܾ�鸸 ����
		}
	}
	
	//���� �б�
	//���� �о keTest�� FileRead �޼��忡�� ������.
	//���� �о keTest�� �ܾ� �� �м� => [word, cnt] ���� => �� ArrayList�� ����
	public static void FileRead() throws IOException {
		File file = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\amusementPark\\" + name + ".csv");
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;	//���� ������ ����� ����
		
		while ((line = br.readLine()) != null) {
			line.trim();		//�ش� �ܾ� �� �� ���� �߶���
			keTest(line);
		}
		br.close();
	}


	//���ϵ��� ���� �̸��� �ٲ㼭 ������ ����.
	public static void FileWrite() throws IOException {
		String[] wordArr = new String [word.size()];
		ArrayList <String> wordNCnt = new ArrayList<>();
		
		//��̸���Ʈ ���� ��̿� ���.
		for (int i = 0; i < word.size(); i++) {
			wordArr[i] = word.get(i);
		}
		
		//**********name �κп� lotteworld, everland, seoulland �� ����. ************* 
		File file = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\amusementPark\\" + name + "_DataAnalysis.csv"); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		/////////�ܾ� �󵵼� ���/////////////
		//**********ArrayList���� ��� �Ϲ� array�� ��ƾ� HashMap ��밡��.
		//HasgMap�� �Ϲ� Arr������ �۵�!
		Map<String, Integer> wordMap = new HashMap<>();
		Map<String, Integer> result = new HashMap<>();
		
		
		for(int i = 0; i < wordArr.length; i++) {
			if(wordMap.containsKey(wordArr[i])) {
				int value = (int)wordMap.get(wordArr[i]);
				wordMap.put(wordArr[i], value + 1);	//������ �ִ� Ű�� ���� �� +1
			} else {
				wordMap.put(wordArr[i], 1);	//������ ���� Ű�� ���� 1�� ����
			}
		}
		
		System.out.println();
		System.out.println("�󵵼� ���� �ܾ� �������� ��� : ");
		//key���� �������� �������� �����ϱ�
		List<String> listKeySet = new ArrayList<>(wordMap.keySet());
        Collections.sort(listKeySet, (value1, value2) -> (wordMap.get(value2).compareTo(wordMap.get(value1))));
        for(String key : listKeySet) {
        	StringBuffer sb = new StringBuffer();
        	sb.append(key + "," + wordMap.get(key));
            System.out.println(key + " => " + wordMap.get(key));
            bw.write(sb.toString());
            bw.newLine();
        }
	
		System.out.println("��������Ϸ�");
		bw.flush();
		bw.close();
	}
} //class