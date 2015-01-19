/**
 * Silica智能词典后台程序包
 * @author Silica Group
 */
package core;

/**
 * Word类是单词的类，也是Silica词典最基本的数据结构单位
 * 提供了单词的构造和获取属性值方式
 */
public class Word implements Comparable<Word>{
	//私有属性：单词拼写、单词音标、单词翻译、单词被查询的次数
	private String wordSpell;
	private String wordPhonogram;
	private String wordTranslation;
	private int wordSearchTimes;
	
	//构造方法，传入四个参数
	Word(String spell,String phonogram,String translation,int searchTimes) {
		wordSpell=spell;
		wordPhonogram=phonogram;
		wordTranslation=translation;
		wordSearchTimes=searchTimes;
	}
	
	//获取拼写
	public String getWord() {
		return wordSpell;
	}
	
	//获取音标
	public String getPhonogram() {
		return wordPhonogram;
	}
	
	//获取翻译
	public String getTranslation() {
		return wordTranslation;
	}
	
	//获取查询次数
	public int getSearchTimes() {
		return wordSearchTimes;
	}
	
	//设置查询次数
	protected void setSearchTimes(int newTimes) {
		wordSearchTimes=newTimes;
	}
	
	//比较器
	public int compareTo(Word toCompare) {
		if (this.wordSearchTimes>toCompare.wordSearchTimes) return 1;
		if (this.wordSearchTimes<toCompare.wordSearchTimes) return -1;
		if (this.wordSpell.compareTo(toCompare.wordSpell)>0) return 1;
		if (this.wordSpell.compareTo(toCompare.wordSpell)<0) return -1;
		return 0;
	}
}
