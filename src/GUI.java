import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GUI {
	
	JFrame frame; 
	JLabel answerLabel; 
	ArrayList<JButton> buttons; 
	
	public GUI() {
		
		frame = new JFrame(); 
		JPanel panel = new JPanel(); 
		buttons = new ArrayList<>();
		//panel.setBorder(BorderFactory.createEmptyBorder(200, 200, 150, 150));
		panel.setLayout(new GridLayout(0, 1));
		panel.setBackground(Color.WHITE);
		SetUpCalculator(panel); 
		
		frame.add(panel, BorderLayout.CENTER); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("WF77 Calculator SE311Assn4");
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public void SetUpCalculator(JPanel p) {
		//Adds answer label
		JPanel answerP = new JPanel(); 
		//answerP.setBorder(BorderFactory.createEmptyBorder(200, 200, 180, 150));
		answerLabel = new JLabel("Here", JLabel.LEFT);
		answerP.add(answerLabel); 
		p.add(answerP); 
		
		//Adds calculator buttons
		for (int i = 0; i < 16; i+=4) {
			JPanel buttonrowJPanel = new JPanel();
			buttonrowJPanel.setLayout(new FlowLayout()); 
			for (int j = 0; j < 4; j++) {
				JButton calcButton = new JButton();
				calcButton.setText(chooseLabel(i+j));
				buttonrowJPanel.add(calcButton); 
				buttons.add(calcButton); 
			}
			p.add(buttonrowJPanel); 
		}
	}
	
	public String chooseLabel(int i) {
		switch(i) {
			case 0:
				return "1";
			case 1:
				return "2";
			case 2:
				return "3";
			case 3:
				return "+";
			case 4:
				return "4";
			case 5:
				return "5";
			case 6:
				return "6";
			case 7:
				return "-";
			case 8:
				return "7";
			case 9:
				return "8";
			case 10:
				return "9";
			case 11:
				return "*";
			case 12:
				return "0";
			case 13:
				return "=";
			case 14:
				return "C";
			case 15:
				return "/";
			default:
				return "";
		}	
	}
	
	public ArrayList<JButton> getAllButtons(){
		return buttons; 
	}
	
	public JButton getSpecificButton(int i) {
		if (i >= 0 && i < buttons.size()) {
			return buttons.get(i); 
		}
		else {
			return null; 
		}
	}
	
	public JLabel getAnswerLabel() {
		return answerLabel; 
	}
	
	public JFrame getFrame() {
		return frame; 
	}

}
