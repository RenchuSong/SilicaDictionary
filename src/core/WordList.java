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
public class WordList {
	private ArrayList<Word> wordList;
	
	private String changeToStdPhonogram(String originPhonogram){
		return originPhonogram.replace('0','ð')
							  .replace('1','æ')
							  .replace('2','ɑ')
							  .replace('3','ɒ')
							  .replace('4','ə')
							  .replace('5','ʃ')
							  .replace('6','ŋ')
							  .replace('7','θ')
							  .replace('8','ʌ')
							  .replace('9','ʒ')
							  .replace('`','‚')
							  .replace('A','e');
	}
	
	WordList() throws IOException {
		wordList=new ArrayList<Word>();
		File dataSource=new File("data/wordList.txt");
		BufferedReader bufSource = new BufferedReader(new java.io.FileReader(dataSource));
		String word = null;
		String wordSpell=null,wordPhonogram=null;
		String wordTranslation=null;
		while ((word = bufSource.readLine())!=null) {
			if (word.equals("")) break;
			int seperator=word.indexOf(' ');
			wordSpell=word.substring(0,seperator );
			wordPhonogram=word.substring(seperator+3, word.indexOf(']'));
			StringBuffer strBuf=new StringBuffer();
			wordTranslation=null;
			while (true) {
				String tmpString=bufSource.readLine();
				if (tmpString.equals("")) break;
				strBuf.append(tmpString+"\n");
			}
			wordTranslation=strBuf.toString();
			wordList.add(new Word(wordSpell,changeToStdPhonogram(wordPhonogram),wordTranslation,0));
		}
		bufSource.close();
	}
	
	//获取值
	public ArrayList<Word> getData(){
		return wordList;
	}
}
