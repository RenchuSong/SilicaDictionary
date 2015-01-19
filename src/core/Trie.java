/**
 * Silica智能词典后台程序包
 * @author Silica Group
 */
package core;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Trie类，实例化为单词前缀、单词后缀、音标三个对象
 */
public class Trie {
	private TrieNode trieRoot;
	
	//构造方法
	Trie() { trieRoot=null; }
	
	//建立Trie
	public void buildTrie(ArrayList<String> stringList,ArrayList<Word> wordList) throws Exception{
		trieRoot=new TrieNode();
		Iterator<String> it=stringList.iterator();
		Iterator<Word> itWord=wordList.iterator();
		while (it.hasNext()) {
			String stringItem=(String)it.next();
			Word wordItem=(Word)itWord.next();
			insertItem(stringItem,wordItem);
		}
	}
	
	//Trie中插入一个元素
	private void insertItem(String stringItem, Word wordItem) throws Exception{
		TrieNode pointerNode=trieRoot;
		int stringLength=stringItem.length();
		for (int i=0;i<stringLength;++i) {
			if (pointerNode.getFirstChild()==null) {
				TrieNode newNode=new TrieNode(stringItem.charAt(i));
				pointerNode.setFirstChild(newNode);
				pointerNode=pointerNode.getFirstChild();
			}else {
				pointerNode=pointerNode.getFirstChild();
				while (pointerNode.getValue()!=stringItem.charAt(i)) {
					if (pointerNode.getRightBrother()==null) break;
					else pointerNode=pointerNode.getRightBrother();
				}
				if (pointerNode.getValue()!=stringItem.charAt(i)){
					TrieNode newNode=new TrieNode(stringItem.charAt(i));
					pointerNode.setRightBrother(newNode);
					pointerNode=pointerNode.getRightBrother();
				}
			}
		}
		pointerNode.setRelateWord(wordItem);
	}
	
	//从某个点开始DFS这个Trie，生成单词列表
	private void dfsTrie(TrieNode pointerNode,int leftDepth,ArrayList<Word> result) {
		if (pointerNode.getRelateWord()!=null) result.add(pointerNode.getRelateWord());
		if (leftDepth==0) return;
		pointerNode=pointerNode.getFirstChild();
		while (pointerNode!=null) {
			dfsTrie(pointerNode,leftDepth-1,result);
			pointerNode=pointerNode.getRightBrother();
		}
	}
	
	//模糊遍历Trie，生成单词列表
	private void fuzzyDfsTrie(TrieNode pointerNode,String pattern,int matchPosition,int tolerateDiffer,ArrayList<Word> result) {
		if (matchPosition>pattern.length()) return;
		if (pointerNode==null) return;
		if (tolerateDiffer<0) return;
		if (matchPosition==pattern.length() && pointerNode.getRelateWord()!=null) {
			result.add(pointerNode.getRelateWord());
		}

		//递归当前位精确
		TrieNode tmpNode=pointerNode.getFirstChild();
		while (tmpNode!=null && tmpNode.getValue()!=pattern.charAt(matchPosition-1)) {
			tmpNode=tmpNode.getRightBrother();
		}
		if (tmpNode!=null) {
			fuzzyDfsTrie(tmpNode,pattern,matchPosition+1,tolerateDiffer,result);
		}
		
		//递归从pattern中跳过一位
		fuzzyDfsTrie(pointerNode,pattern,matchPosition+1,tolerateDiffer-1,result);
		
		//递归从Trie中跳过一位
		tmpNode=pointerNode.getFirstChild();
		while (tmpNode!=null) {
			fuzzyDfsTrie(tmpNode,pattern,matchPosition,tolerateDiffer-1,result);
			tmpNode=tmpNode.getRightBrother();
		}
		
		//当前位不匹配
		tmpNode=pointerNode.getFirstChild();
		while (tmpNode!=null) {
			if (tmpNode.getValue()!=pattern.charAt(matchPosition-1))
				fuzzyDfsTrie(tmpNode,pattern,matchPosition+1,tolerateDiffer-1,result);
			tmpNode=tmpNode.getRightBrother();
		}
	}
	
	//前缀查询
	public ArrayList<Word> prefixFind(String prefix) {
		ArrayList<Word> result=new ArrayList<Word>();
		TrieNode pointerNode=trieRoot;
		int prefixLength=prefix.length();
		for (int i=0;i<prefixLength;++i) {
			if (pointerNode.getFirstChild()==null) return result;
			pointerNode=pointerNode.getFirstChild();
			while (pointerNode.getValue()!=prefix.charAt(i)) {
				if (pointerNode.getRightBrother()==null) return result;
				pointerNode=pointerNode.getRightBrother();
			}
		}
		dfsTrie(pointerNode,prefixLength*5,result);
		return result;
	}
	
	//模糊查询
	public ArrayList<Word> fuzzyFind(String pattern, double fuzzyRate) {
		ArrayList<Word> result=new ArrayList<Word>();
		TrieNode pointerNode=trieRoot;
		//查找长度
		int patternLength=pattern.length();
		//允许误差
		int tolerateDiffer=(int)(patternLength*(1-fuzzyRate))+1;
		System.out.println(tolerateDiffer);
		fuzzyDfsTrie(pointerNode,pattern,1,tolerateDiffer,result);
		return result;
	}
}

/**
 * TrieNode类是Trie中每个节点的类
 */
class TrieNode {
	private char nodeValue;
	private TrieNode firstChild;
	private TrieNode rightBrother;
	private Word relateWord;
	
	TrieNode() {
		this.nodeValue=(char)0;
		this.firstChild=null;
		this.rightBrother=null;
		this.relateWord=null;
	}
	
	TrieNode(char nodeValue) {
		this.nodeValue=nodeValue;
		this.firstChild=null;
		this.rightBrother=null;
		this.relateWord=null;
	}
	
	public char getValue() { return nodeValue; }
	public TrieNode getFirstChild() { return firstChild; }
	public TrieNode getRightBrother() { return rightBrother; }
	public Word getRelateWord() { return relateWord; }
	protected void setFirstChild(TrieNode toAdd) { firstChild=toAdd; }
	protected void setRightBrother(TrieNode toAdd) { rightBrother=toAdd; }
	protected void setRelateWord(Word theWord){ relateWord=theWord; }
}