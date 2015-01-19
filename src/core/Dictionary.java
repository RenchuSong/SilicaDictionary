/**
 * Silica智能词典后台程序包
 * @author Silica Group
 */
package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *词典类，含有单词、读音前缀、后缀，以及单词列表成员
 */
public class Dictionary {
	//单词前缀Trie
	Trie wordspellPrefixTrie;
	//单词后缀Trie
	Trie wordspellSuffixTrie;
	//音标前缀Trie
	Trie wordPhonogramPrefixTrie;

	//单词表
	ArrayList<Word> wordList;
	
	//模糊程度
	private final double fuzzyRate=0.75;
	
	//构造函数
	Dictionary(ArrayList<Word> wordList) throws Exception{
		//单词表
		this.wordList=wordList;
		
		//构建单词前缀Trie
		ArrayList<String> stringList=new ArrayList<String>();
		Iterator<Word> it=wordList.iterator();
		while (it.hasNext()) {
			stringList.add(it.next().getWord());
		}
		wordspellPrefixTrie=new Trie();
		wordspellPrefixTrie.buildTrie(stringList, wordList);
		
		//构建音标前缀Trie
		stringList=new ArrayList<String>();
		it=wordList.iterator();
		while (it.hasNext()) {
			stringList.add(it.next().getPhonogram());
		}
		wordPhonogramPrefixTrie=new Trie();
		wordPhonogramPrefixTrie.buildTrie(stringList, wordList);
		
		//构建单词后缀Trie
		stringList=new ArrayList<String>();
		it=wordList.iterator();
		while (it.hasNext()) {
			StringBuffer strBuffer=new StringBuffer(it.next().getWord());
			stringList.add(strBuffer.reverse().toString());
		}
		wordspellSuffixTrie=new Trie();
		wordspellSuffixTrie.buildTrie(stringList, wordList);
	}
	
	/**
	 * 根据单词拼写查找单词
	 * @param wordSpell 查找的单词
	 * @param prefix 是否前缀查找
	 * @param fuzzy 是否模糊查找
	 * @param suffix 是否后缀查找
	 * @return 返回查找结果列表
	 */
	public ArrayList<Word> spellFindWords(String wordSpell,boolean prefix,boolean fuzzy,boolean suffix) {
		ArrayList<Word> findResult=new ArrayList<Word>();
		if (prefix) {
			findResult.addAll(wordspellPrefixTrie.prefixFind(wordSpell));
		}
		if (fuzzy) {
			findResult.addAll(wordspellPrefixTrie.fuzzyFind(wordSpell, fuzzyRate));
		}
		if (suffix) {
			StringBuffer strBuf=new StringBuffer(wordSpell);
			strBuf.reverse();
			findResult.addAll(wordspellSuffixTrie.prefixFind(new String(strBuf)));
		}
		Collections.sort(findResult);
		ArrayList<Word> result=new ArrayList<Word>();
		Iterator<Word> it=findResult.iterator();
		if (it.hasNext()){
			Word w1=it.next();
			Word w2=null;
			result.add(w1);
			while (it.hasNext()){
				w2=it.next();
				if (w1.compareTo(w2)!=0){
					result.add(w2);
				}
				w1=w2;
			}
		}
		return result;
	}
	
	/**
	 * 根据单词读音查找单词
	 * @param wordPhonogram 单词音标
	 * @param prefix 是否前缀查询
	 * @param fuzzy 是否模糊查询
	 * @return 返回查询结果列表
	 */
	public ArrayList<Word> phonogramFindWords(String wordPhonogram,boolean prefix,boolean fuzzy) {
		ArrayList<Word> findResult=new ArrayList<Word>();
		if (prefix) {
			findResult.addAll(wordPhonogramPrefixTrie.prefixFind(wordPhonogram));
		}
		if (fuzzy){
			findResult.addAll(wordPhonogramPrefixTrie.fuzzyFind(wordPhonogram, fuzzyRate));
		}
		Collections.sort(findResult);
		ArrayList<Word> result=new ArrayList<Word>();
		Iterator<Word> it=findResult.iterator();
		if (it.hasNext()){
			Word w1=it.next();
			Word w2=null;
			result.add(w1);
			while (it.hasNext()){
				w2=it.next();
				if (w1.compareTo(w2)!=0){
					result.add(w2);
				}
				w1=w2;
			}
		}
		return result;
	}
}
