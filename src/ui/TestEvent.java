package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

//import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
//import javax.swing.JLabel;

//import org.jvnet.substance.SubstanceLookAndFeel;
//import org.jvnet.substance.theme.SubstanceLightAquaTheme;

//import ch.randelshofer.quaqua.QuaquaLookAndFeel;

public class TestEvent extends JComponent implements ComponentListener,
		WindowFocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Boolean start = false;
	private Image background;
	private Point p;

	// ��õ�ǰ��Ļ����
	public void updateBackground() {
		try {
			Robot rbt = new Robot();
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension dim = tk.getScreenSize();
			background = rbt.createScreenCapture(new Rectangle(0, 0, (int) dim
					.getWidth(), (int) dim.getHeight()));
		} catch (Exception ex) {
			// p(ex.toString());
			// �˷���û������� ����Ϊ�޷���֪������ ����Ϊ��Ӱ��ִ��Ч�� ����ע�͵��� ex.printStackTrace();
		}
	}

	// �����ڵ������Ļ�Ի�ô���ı���ͼ��
	public void refresh() {
		if (start == true) {
			this.updateBackground();
			frame.setLocation(p);
			if (p.x < 0 || p.y < 0)
				frame.setLocation(0, 0);
			this.repaint();
		}
	}

	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("Hidden");
	}

	// �����ƶ�ʱ
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("moved");
		this.repaint();
	}

	// ���ڸı��Сʱ
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("resized");
		this.repaint();
	}

	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("shown");
	}

	// ���ڵõ������,��refresh()�������½���
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("gainedFocus");
		refresh();
		start = false;
	}

	// ����ʧȥ�����,�����Ƴ���Ļ
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println("lostFocus"); if (frame.isShowing() == true) {
		 * System.out.println("visible"); } else {
		 * System.out.println("invisible"); }
		 */
		start = true;
		p = frame.getLocation();
		frame.setLocation(-2000, -2000);
	}

	public TestEvent(JFrame frame) {
		super();
		this.frame = frame;
		updateBackground();
		this.setVisible(true);
		frame.addComponentListener(this);
		frame.addWindowFocusListener(this);
		setVisible(true);
		
	}

	// �������,ע��,���� pos,offset ��Ϊ�˽��ض����ֵ�ͼ��������
	public void paintComponent(Graphics g) {
		Point pos = this.getLocationOnScreen();
		Point offset = new Point(-pos.x, -pos.y);
		g.drawImage(background, offset.x, offset.y, null);
	}

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		JFrame frame = new UndecoratedFrame();
		TestEvent t = new TestEvent(frame);
		frame.setSize(700, 500);
		t.setLayout(new BorderLayout());
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		int sHeight = dem.height;
		int sWidth = dem.width;
		int fHeight = frame.getHeight();
		int fWidth = frame.getWidth();
		frame.setLocation((sWidth - fWidth) / 2, (sHeight - fHeight) / 2);
		frame.getContentPane().add("Center", t);
		//frame.pack();
		frame.show();
		// t.start=true;
	}

}