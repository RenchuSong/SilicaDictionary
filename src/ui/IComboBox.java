package ui;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class IComboBox extends JComboBox<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IComboBox() {
		super();
		init();
	}

	@SuppressWarnings("unchecked")
	public IComboBox(ComboBoxModel<?> model) {
		super((ComboBoxModel<Object>) model);
		init();
	}

	public IComboBox(Object[] items) {
		super(items);
		init();
	}

	@SuppressWarnings("unchecked")
	public IComboBox(Vector<?> items) {
		super((ComboBoxModel<Object>) items);
		init();
	}

	private void init() {
		setOpaque(false);
		setUI(new IComboBoxUI());
		setRenderer(new IComboBoxRenderer());
		//setBackground(XUtil.defaultComboBoxColor);
	}

	public Dimension getPreferredSize() {
		return super.getPreferredSize();
	}
}