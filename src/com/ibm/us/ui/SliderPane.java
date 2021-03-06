package com.ibm.us.ui;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderPane extends JPanel{
	JSlider slider; 
	JLabel label; 
	String labeltxt; 
	boolean normal = false; 
	
	public SliderPane(String title, String labeltext, int min, int max, int val, boolean n){
		super(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(title)); 
		slider = new JSlider(SwingConstants.HORIZONTAL, min, max, val); 
		label = new JLabel(); 
		labeltxt = labeltext; 
		normal = n; 
		label.setText(labeltxt+getValuetxt()); 
		this.add(slider, BorderLayout.NORTH);
		this.add(label, BorderLayout.SOUTH);
		slider.addChangeListener(new ChangeListener(){
	    @Override
		public void stateChanged(ChangeEvent e) {
	    	label.setText(labeltxt+getValuetxt());
	    	}
	    });
	}
	
	public String getValuetxt(){
		if (normal) return getNormalValue(); 
		else return getValue(); 
	}
	
	protected String getValue(){
		return String.valueOf(slider.getValue()); 
	}
	
	public float getParameter(){
		if (normal) return (float)slider.getValue() / (float)slider.getMaximum();
		else return slider.getValue(); 
	}
	
	protected String getNormalValue(){
		float norm = (float)slider.getValue() / (float)slider.getMaximum(); 
		return String.valueOf(norm); 
	}
	
	public void display() {
		setVisible(true);
	}
	
	public void addListener(ChangeListener cl){
		slider.addChangeListener(cl); 
	}

}
