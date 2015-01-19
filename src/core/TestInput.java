/**
 * Silica智能词典后台程序包
 * @author Silica Group
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 单词表类的构造
 */
public class TestInput {
	private ArrayList<String> wordList;
	TestInput() throws IOException {
		wordList=new ArrayList<String>();
		File dataSource=new File("data/testList.txt");
		BufferedReader bufSource = new BufferedReader(new java.io.FileReader(dataSource));
		String word = null;
		while ((word = bufSource.readLine())!=null) {
			wordList.add(word);
		}
		bufSource.close();
	}
	
	//获取值
	public ArrayList<String> getData(){
		return wordList;
	}
}
