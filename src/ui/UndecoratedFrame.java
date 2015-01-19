package ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

//import org.omg.CORBA.PRIVATE_MEMBER;

//import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.awt.event.*;
//import java.awt.image.ImageObserver;
//import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Vector;

import core.*;

public class UndecoratedFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SilicaCore slcCore;
	
	static Point origin = new Point(); //节点
	private ImagePanel titlePanelLeft = new ImagePanel(new ImageIcon(
			"element/titleBar_left.png"));
	private ImagePanel titlePanelMiddle = new ImagePanel(new ImageIcon(
			"element/titleBar_middle.png"));
	private ImagePanel titlePanelRight = new ImagePanel(new ImageIcon(
			"element/titleBar_right.png"));
	private JButton closeButton = new JButton();
	private JButton minimizeButton = new JButton();
	private JButton fgButton = new JButton();
	private JLabel titleLabel = new JLabel();
	private ImagePanel toolBarPanel = new ImagePanel(new ImageIcon(
			"element/toolBar_bg.png"));
	private ImagePanel textLeftPanel = new ImagePanel(new ImageIcon(
			"element/textField_left_normal.png"));
	private JTextField textField = new JTextField("search");
	private ImagePanel textRightPanel = new ImagePanel(new ImageIcon(
			"element/textField_right_normal.png"));
	private ImagePanel textMiddlePanel = new ImagePanel(new ImageIcon(
			"element/textField_middle_normal.png"));
	private JButton searchButton = new JButton();
	private JPanel contentPanel = new JPanel();
	private JPanel listPanel = new JPanel();
	private ImagePanel listTop = new ImagePanel(new ImageIcon(
			"element/list_top.png"));
	private ImagePanel listMiddle = new ImagePanel(new ImageIcon(
			"element/list_middle.png"));
	private ImagePanel listBottom = new ImagePanel(new ImageIcon(
			"element/list_bottom.png"));
	private ImagePanel contentTop = new ImagePanel(new ImageIcon(
			"element/contentField_top.png"));
	private ImagePanel contentMiddle = new ImagePanel(new ImageIcon(
			"element/contentField_middle.png"));
	private ImagePanel contentBottom = new ImagePanel(new ImageIcon(
			"element/contentField_bottom.png"));
	private ImagePanel textAreaLeftTop = new ImagePanel(new ImageIcon(
			"element/textArea_leftTop.png"));
	private ImagePanel textAreaMiddleTop = new ImagePanel(new ImageIcon(
			"element/textArea_middleTop.png"));
	private ImagePanel textAreaRightTop = new ImagePanel(new ImageIcon(
			"element/textArea_rightTop.png"));
	private ImagePanel textAreaMiddle = new ImagePanel(new ImageIcon(
			"element/textArea_middle.png"));
	private ImagePanel textAreaLeftBottom = new ImagePanel(new ImageIcon(
			"element/textArea_leftBottom.png"));
	private ImagePanel textAreaMiddleBottom = new ImagePanel(new ImageIcon(
			"element/textArea_middleBottom.png"));
	private ImagePanel textAreaRightBottom = new ImagePanel(new ImageIcon(
			"element/textArea_rightBottom.png"));
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JLabel explanationLabel = new JLabel("Explanation");
	private JLabel word = new JLabel();
	private JLabel phonogram = new JLabel();
	private JTextArea explanationArea = new JTextArea();
	private JList<String> wordList = new JList<String>();
	private DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
    
	private JScrollPane jScrollPane = new JScrollPane(wordList);
	
	public UndecoratedFrame() {
		slcCore=new SilicaCore();
		setUndecorated(true);
		setVisible(true);
		setLayout(null);
		// titlePanel.setPreferredSize(new Dimension(700, 41));
		// titlePanel.setLayout(new BorderLayout());
		// titlePanelLeft.setPreferredSize(new Dimension(80, 41));
		// titlePanelRight.setPreferredSize(new Dimension(41, 41));

		titlePanelLeft.setBounds(0, 0, 80, 41);
		titlePanelMiddle.setBounds(41, 0, 618, 41);
		titlePanelRight.setBounds(659, 0, 41, 41);

		titlePanelLeft.setLayout(null);

		closeButton.setOpaque(false);
		closeButton.setBorder(null);
		closeButton.setContentAreaFilled(true);
		closeButton.setBounds(8, 10, 18, 20);
		closeButton.setIcon(new ImageIcon("element/close_normal.png"));
		closeButton.setRolloverIcon(new ImageIcon("element/close_hover.png"));
		closeButton.setPressedIcon(new ImageIcon("element/close_active.png"));
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		minimizeButton.setOpaque(false);
		minimizeButton.setBorder(null);
		minimizeButton.setContentAreaFilled(false);
		minimizeButton.setBounds(28, 10, 14, 20);
		minimizeButton.setIcon(new ImageIcon("element/bigger_normal.png"));
		minimizeButton
				.setRolloverIcon(new ImageIcon("element/bigger_hover.png"));
		minimizeButton
				.setPressedIcon(new ImageIcon("element/bigger_active.png"));
		minimizeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		fgButton.setOpaque(false);
		fgButton.setBorder(null);
		fgButton.setContentAreaFilled(false);
		fgButton.setBounds(45, 10, 18, 20);
		fgButton.setIcon(new ImageIcon("element/minimize_normal.png"));
		fgButton.setRolloverIcon(new ImageIcon("element/minimize_hover.png"));
		fgButton.setPressedIcon(new ImageIcon("element/minimize_active.png"));
		fgButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		titlePanelLeft.add(closeButton);
		titlePanelLeft.add(minimizeButton);
		titlePanelLeft.add(fgButton);

		titleLabel.setOpaque(false);
		titleLabel.setIcon(new ImageIcon("element/Silica.png"));
		titleLabel.setBounds(280, 15, 32, 10);

		titlePanelMiddle.setLayout(null);
		titlePanelMiddle.add(titleLabel);

		toolBarPanel.setBounds(-1, 40, 701, 44);
		toolBarPanel.setLayout(null);

		textLeftPanel.setBounds(10, 9, 27, 27);
		textLeftPanel.setLayout(null);
		textMiddlePanel.setBounds(37, 9, 450, 27);
		textMiddlePanel.setLayout(new BorderLayout());
		textField.setOpaque(false);
		textField.setBorder(null);
		textField.setForeground(new Color(171, 171, 171));
		/*输入单词*/
		
		
		textMiddlePanel.add(textField);
		textRightPanel.setBounds(487, 9, 27, 27);

		comboBox.setBounds(520, 11, 171, 22);
		/*选项*/
		comboBox.addItem("前缀查询");
		comboBox.addItem("后缀查询");
		comboBox.addItem("模糊查询");
		comboBox.addItem("前缀+模糊");
		comboBox.addItem("模糊+后缀");
		comboBox.addItem("音标查询");
		comboBox.addItem("音标模糊查询");
		
		comboBox.setEditable(true);
		comboBox.setRenderer(new ListCellRenderer() {
		    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        final JLabel renderer = new JLabel(value.toString());
		        if (isSelected) {
		            renderer.setOpaque(true);
		            renderer.setForeground(Color.white);
		            renderer.setBackground(Color.black);
		        } else {
		        	renderer.setOpaque(true);
		        	renderer.setForeground(Color.white);
		        	renderer.setBackground(new Color(52, 52, 52));
		        }
		        return renderer;
		    }
		});
		comboBox.setEditor(new MyComboBoxEditor());
		
//		comboBox.setRenderer(new ListCellRenderer() {
//		    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//		        final JLabel renderer = new JLabel(value.toString());
//		        if (isSelected) {
//		            renderer.setOpaque(true);
//		            renderer.setForeground(Color.BLUE);
//		            renderer.setBackground(Color.LIGHT_GRAY);
//		        }
//		        return renderer;
//		    }
//		});
		
		searchButton.setOpaque(false);
		searchButton.setBorder(null);
		searchButton.setContentAreaFilled(false);
		searchButton.setBounds(5, 4, 17, 17);
		searchButton.setIcon(new ImageIcon("element/search_normal.png"));
		searchButton.setRolloverIcon(new ImageIcon("element/search_hover.png"));
		searchButton.setPressedIcon(new ImageIcon("element/search_active.png"));

		textLeftPanel.add(searchButton);
		toolBarPanel.add(textLeftPanel);
		toolBarPanel.add(textMiddlePanel);
		toolBarPanel.add(textRightPanel);
		toolBarPanel.add(comboBox);

		listPanel.setBounds(0, 84, 192, 415);
		listPanel.setLayout(new BorderLayout());
		listTop.setPreferredSize(new Dimension(192, 3));
		listBottom.setPreferredSize(new Dimension(192, 3));
		listPanel.add(listTop, BorderLayout.NORTH);
		listPanel.add(listMiddle, BorderLayout.CENTER);
		listPanel.add(listBottom, BorderLayout.SOUTH);

		listMiddle.setLayout(null);
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane.getViewport().setBorder(null);
		jScrollPane.getViewport().setBackground(null);
		jScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setBounds(5, 0, 185, 420);
		
		jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
		 
			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = new JButton("zero button");
			    Dimension zeroDim = new Dimension(0,0);
			    button.setPreferredSize(zeroDim);
			    button.setMinimumSize(zeroDim);
			    button.setMaximumSize(zeroDim);
			    return button;
			}
		 
			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = new JButton("zero button");
			    Dimension zeroDim = new Dimension(0,0);
			    button.setPreferredSize(zeroDim);
			    button.setMinimumSize(zeroDim);
			    button.setMaximumSize(zeroDim);
			    return button;
			}
		 
			@Override
		    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		        g.setColor(new Color(21, 21, 21));
		        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
		    }
		 
		    @Override
		    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		        g.setColor(new Color(52, 52, 52));
		        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
		    }
		});
		
		wordList.setOpaque(false);
		listMiddle.add(jScrollPane);
		//listPanel.add(wordList);
///////		/*单词表*/
	
		contentPanel.setBounds(192, 84, 508, 415);
		contentPanel.setLayout(new BorderLayout());
		contentTop.setPreferredSize(new Dimension(508, 3));
		contentBottom.setPreferredSize(new Dimension(508, 3));

		contentMiddle.setLayout(null);
		explanationLabel.setBounds(10, 8, 100, 15);
		explanationLabel.setForeground(new Color(171, 171, 171));
		textAreaLeftTop.setBounds(3, 30, 10, 10);
		textAreaMiddleTop.setBounds(13, 30, 479, 10);
		textAreaRightTop.setBounds(492, 30, 10, 10);
		textAreaLeftBottom.setBounds(3, 395, 10, 10);
		textAreaMiddleBottom.setBounds(13, 395, 479, 10);
		textAreaRightBottom.setBounds(492, 395, 10, 10);
		textAreaMiddle.setBounds(3, 40, 499, 355);

		textAreaMiddle.setLayout(null);
		word.setBounds(30, 1, 200, 40);
		word.setFont(new Font("Arial", Font.BOLD, 20));
		word.setText("Hello");
		word.setForeground(Color.white);
		/*单词*/
		
		phonogram.setBounds(30, 30, 200, 40);
		phonogram.setFont(new Font("Arial", Font.ITALIC, 18));
		phonogram.setText("[ hello ]");
		phonogram.setForeground(Color.white);
		/*音标*/
		
		explanationArea.setBounds(30, 80, 440, 260);
		explanationArea.setEditable(false);
		explanationArea.setOpaque(false);
		explanationArea.setText("nihao\n你好");
		explanationArea.setFont(new Font("宋体", Font.BOLD, 14));
		explanationArea.setForeground(new Color(171, 171, 171));
		// explanationArea.setForeground())
		/*解释*/

		textAreaMiddle.add(word);
		textAreaMiddle.add(phonogram);
		textAreaMiddle.add(explanationArea);

		contentMiddle.add(explanationLabel);
		contentMiddle.add(textAreaLeftTop);
		contentMiddle.add(textAreaMiddleTop);
		contentMiddle.add(textAreaRightTop);
		contentMiddle.add(textAreaLeftBottom);
		contentMiddle.add(textAreaMiddleBottom);
		contentMiddle.add(textAreaRightBottom);
		contentMiddle.add(textAreaMiddle);

		contentPanel.add(contentTop, BorderLayout.NORTH);
		contentPanel.add(contentMiddle, BorderLayout.CENTER);
		contentPanel.add(contentBottom, BorderLayout.SOUTH);

		add(titlePanelLeft);
		add(titlePanelMiddle);
		add(titlePanelRight);
		add(toolBarPanel);
		add(listPanel);
		add(contentPanel);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { //mousePressed
				//
				origin.x = e.getX(); // 
				origin.y = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) { 

				Point p = getLocation(); // 

				setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
						- origin.y);
			}
		});
		
		
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean spell=false;
				boolean prefix=false;
				boolean suffix=false;
				boolean fuzzy=false;
				
				String searchType=(String)comboBox.getSelectedItem();
				
				if (searchType.equals("前缀查询")){
					spell=true;prefix=true;
				}else
				if (searchType.equals("后缀查询")){
					spell=true;suffix=true;
				}else
				if (searchType.equals("模糊查询")){
					spell=true;fuzzy=true;
				}else
				if (searchType.equals("前缀+模糊")){
					spell=true;prefix=true;fuzzy=true;
				}else
				if (searchType.equals("模糊+后缀")){
					spell=true;suffix=true;fuzzy=true;
				}else
				if (searchType.equals("音标查询")){
					spell=false;prefix=true;
				}else
				if (searchType.equals("音标模糊查询")){
					spell=false;fuzzy=true;
				}
				ArrayList<Word> searchResult;
				if (spell){
					searchResult=slcCore.spellFindWords(textField.getText(), prefix, fuzzy, suffix);
				}else{						
					searchResult=slcCore.phonogramFindWords(textField.getText(), prefix, fuzzy);
				}
				Iterator<Word> it=searchResult.iterator();
				//Vector<String> vecResult = new Vector<String>();
				defaultListModel=new DefaultListModel<String>();
				int i=0;
				while (it.hasNext()){
					++i;
					Word w=(Word)it.next();
					//vecResult.addElement(w.getWord());
					defaultListModel.add(0, w.getWord());
				    
					//vecResult.add( w.getWord());
					//vecResult.addElement(w.getWord());
					//wordList.add(w.getWord(), new JButton(w.getWord()));
					//strResult.append(w.getWord()+"\n"+w.getPhonogram()+"\n"+w.getTranslation()+"\n\n");
				}
				wordList.setForeground(Color.white);
				wordList.setBackground(Color.black);
				wordList.setModel(defaultListModel);
				//wordList=new JList(vecResult);
				wordList.addListSelectionListener(new ListSelectionListener() {
				    public void valueChanged(ListSelectionEvent arg0) {
				    	Word w=slcCore.preciousFind(wordList.getSelectedValue().toString());
				    	if (w!=null) {
				    		word.setText(w.getWord());
				    		phonogram.setText("[ "+w.getPhonogram()+" ]");
				    		explanationArea.setText(w.getTranslation());
				    	}
				    	
				        //System.out.println("你选择朝代的是："
				        //+ wordList.getSelectedValue().toString());
				    }
				});
				//System.out.println(i);
				/*vecResult=new Vector<String>();
				vecResult.add("sss");
				vecResult.add("rrr");*/
				
				//wordList=new JList<String>(vecResult);
				
				//System.out.println(textField.getText());
			}
		});
		
		
	}
}
