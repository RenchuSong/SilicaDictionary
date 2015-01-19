/**
 * Silica智能词典前台界面包
 * @author Silica Group
 */
package ui;
import core.SilicaCore;
import core.Word;

import java.awt.*;
import java.awt.event.*;
//import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

//import javax.naming.directory.SearchResult;

public class Silica extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SilicaCore slcCore;
	TextArea searchResult;
	Choice selectMode;
	
	private Panel flowPanel;
	private Panel leftflowPanel;
	/**
	 * 构建字典界面
	 * @param title 标题
	 */
	public Silica(String title) {
		//设置标题
		super(title);
		//实例化核心类
		slcCore=new SilicaCore();
		//窗体大小和位置
		setSize(600,500);
		this.setBackground(new Color(210,220,244));
		setLocation(300,100);
		//窗体布局
		setLayout(new BorderLayout());
		//设置图标
		Toolkit tool=this.getToolkit();
		Image iconImage=tool.getImage("./element/SilicaLogo.png");
		this.setIconImage(iconImage);
		//添加操作栏
		setFlowLayoutPanel();
		add(flowPanel,BorderLayout.NORTH);
		setLeftFlowLayoutPanel();
		add(leftflowPanel,BorderLayout.WEST);
		
		//添加结果显示
		searchResult=new TextArea();
		searchResult.setBackground(new Color(223,225,227));
		//final TextField searchResult=new TextField();
		add(searchResult,BorderLayout.CENTER);
		//searchResult.setBounds(10, 10, 100, 20);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		);
	}
	
	/**
	 * 设置左侧的单词显示框
	 */
	public void setLeftFlowLayoutPanel(){
		leftflowPanel=new Panel();
		leftflowPanel.setLayout(new FlowLayout());
		Button btn=new Button("        Search Result         ");
		btn.setEnabled(false);
		btn.setBackground(new Color(223,225,227));
		leftflowPanel.add(btn);
	}
	
	/**
	 * 设置第一行的输入框等
	 */
	public void setFlowLayoutPanel(){
		flowPanel=new Panel();
		flowPanel.setLayout(new FlowLayout());
		final TextField wordInput=new TextField(56);
		wordInput.setBackground(new Color(223,225,227));
		selectMode=new Choice();
		selectMode.add("prefix");
		selectMode.add("suffix");
		selectMode.add("fuzzy");
		selectMode.add("prefix+fuzzy");
		selectMode.add("suffix+fuzzy");
		selectMode.add("phonogram");
		selectMode.add("fuzzy phonogram");
		selectMode.setBackground(new Color(223,225,227));
		Button searchBtn=new Button("Go");
		searchBtn.setBackground(new Color(48,87,192));
		searchBtn.setForeground(new Color(210,220,244));
		searchBtn.setFont(new Font("Comic Sans MS",3,12));
		//private static TextArea text2;
		flowPanel.add(wordInput);
		flowPanel.add(searchBtn);
		flowPanel.add(selectMode);
		searchBtn.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String searchText=wordInput.getText();
					String searchType=selectMode.getSelectedItem();
					StringBuffer showResult=new StringBuffer("");
					boolean spell=false;
					boolean prefix=false;
					boolean suffix=false;
					boolean fuzzy=false;
					if (searchType.equals("prefix")){
						spell=true;prefix=true;
					}else
					if (searchType.equals("suffix")){
						spell=true;suffix=true;
					}else
					if (searchType.equals("fuzzy")){
						spell=true;fuzzy=true;
					}else
					if (searchType.equals("prefix+fuzzy")){
						spell=true;prefix=true;fuzzy=true;
					}else
					if (searchType.equals("suffix+fuzzy")){
						spell=true;suffix=true;fuzzy=true;
					}else
					if (searchType.equals("phonogram")){
						spell=false;prefix=true;
					}else
					if (searchType.equals("fuzzy phonogram")){
						spell=false;fuzzy=true;
					}
					ArrayList<Word> result;
					if (spell){
						result=slcCore.spellFindWords(searchText, prefix, fuzzy, suffix);
					}else{						
						result=slcCore.phonogramFindWords(searchText, prefix, fuzzy);
					}
					Iterator<Word> it=result.iterator();
					while (it.hasNext()){
						Word w=(Word)it.next();
						showResult.append(w.getWord()+"\n"+w.getPhonogram()+"\n"+w.getTranslation()+"\n\n");
					}
					searchResult.setText(showResult.toString());
					//searchResult.setText(selectMode.getSelectedItem());
				}
			}
		);
	}
	/*
	public static void main(String[] args) {
		Silica slc=new Silica("Silica智能词典");
		slc.setVisible(true);
	}*/
}
