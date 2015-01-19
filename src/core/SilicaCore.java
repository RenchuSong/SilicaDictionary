/**
 * Silica智能词典后台程序包
 * @author Silica Group
 */
package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Silica后台核心类
 * 获取单词资源，并构造词典
 */
public class SilicaCore {
	//单词表
	static WordList srcData;
	//词典类
	static Dictionary dictionary;
	
	public SilicaCore(){
		try {
			srcData=new WordList();
		}catch(IOException ioException) {
			ioException.printStackTrace();
			System.out.println("载入失败");
		}
		try{
			dictionary=new Dictionary(srcData.getData());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 单词查询通过传递参数设置查询结果
	 */
	public ArrayList<Word> spellFindWords(String wordSpell,boolean prefix,boolean fuzzy,boolean suffix){
		return dictionary.spellFindWords(wordSpell, prefix, fuzzy, suffix);
	}
	
	/**
	 * 读音查询通过传递参数设置查询结果
	 */
	public ArrayList<Word> phonogramFindWords(String wordPhonogram,boolean prefix,boolean fuzzy){
		return dictionary.phonogramFindWords(wordPhonogram, prefix, fuzzy);
	}
	
	/**
	 * 通过拼写精确查询
	 */
	public Word preciousFind(String wordSpell) {
		Iterator<Word> it=srcData.getData().iterator();
		while (it.hasNext()) {
			Word w=it.next();
			if (w.getWord().equals(wordSpell)) return w;
		}
		return null;
	}
	/*
	public static void main(String[] args){
		//System.out.println("中文æ");
		new SilicaCore();
		if (dictionary==null) System.out.println("df");
		//ArrayList<Word> result=dictionary.spellFindWords("aggregate", true, false, false);
		ArrayList<Word> result=dictionary.phonogramFindWords("'ægrigeit", false, true);
		Iterator<Word> it=result.iterator();
		while (it.hasNext()){
			Word w=(Word)it.next();
			System.out.println(w.getWord()+"\n"+w.getPhonogram()+"\n"+w.getTranslation()+"\n\n");
		}
	}*/
}
