import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Client{
	
	private GUI gui; 
	private CalculatorModel calculatorModel; 
	private boolean clientClosed = false; 
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	public Client() { 
		gui = new GUI(); 
		calculatorModel = new CalculatorModel(); 
		giveButtonsandFrameCommands();
	}
	
	public void giveButtonsandFrameCommands() {
		
		ArrayList<JButton> buttons = gui.getAllButtons();
		
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).addActionListener(new CalcButtonListener(buttons.get(i)));
		}
	
		JFrame frame = gui.getFrame(); 
		
		frame.addWindowListener(new CalcFrameListener());
	}
	
	
	public void updateAnswer() {
		gui.getAnswerLabel().setText(calculatorModel.getOutput());
	}
	
	public void run() {
		
		String host = "127.0.0.1";
		int port = 8081;
		
		if (socket == null && out == null && in == null) {
			System.out.println("Connecting to host " + host + " on port " + port + ".");
			try {
				socket = new Socket(host, port);
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
		String input = calculatorModel.getInput(); 
		if (input == null) {
			//Fire here
			System.out.println("Error. Input is null. Please close and reopen");
		}
		else if (input.equals("break")) {
			System.out.print("Client stopping");
			out.print(input);
		}
		else if(input.equals("")) {
			//Skip over input, nothing's been typed on the calculator
			System.out.println("in else if");
			
		} else {
			//System.out.println(input);
			out.println(input + "\n");
			try {
				calculatorModel.setOutput(in.readLine());
				gui.answerLabel.setText(calculatorModel.getOutput());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			while (true) {
				try {
					if ((fromServer = in.readLine()) != null) {
						String [] output = fromServer.split("\n"); 
						calculatorModel.setOutput(output[0]);
						calculatorModel.setInput("");
						break; 
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Waiting");
				}
			}
			*/
		}
		/*
		while(true) {
			String input = calculatorModel.getInput(); 
			if (input == null) {
				//Fire here
				System.out.println("Input is" + input);
			}
			else if (input.equals("break")) {
				System.out.print("Client stopping");
				out.print(input);
				break; 
			}
			else if(input.equals("")) {
				//Skip over input, nothing's been typed on the calculator
				System.out.println("in else if");
				
			} else {
				System.out.println(input);
				out.print(input);
				String fromServer; 
				while (true) {
					try {
						if ((fromServer = in.readLine()) != null) {
							String [] output = fromServer.split("\n"); 
							calculatorModel.setOutput(output[0]);
							calculatorModel.setInput("");
							break; 
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Waiting");
					}
				}
			}
		}
		*/
		
	}
	
	public void closeConnections() {
		try {
			System.out.println("Closing");
			out.close();
			in.close();
			socket.close(); 
		}catch(IOException e) {
			System.out.println("Had problems closing");
		}
	}
	
	
	public boolean isClientClosed() {
		return clientClosed; 
	}
	
	
	private class CalcButtonListener implements ActionListener{
		
		JButton button; 
		
		public CalcButtonListener(JButton b) {
			button = b;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			calculatorModel.setInput(getLabel());
			run();
		}
		
		@SuppressWarnings("deprecation")
		public String getLabel() {
			return button.getLabel(); 
		}
	}
	
	private class CalcFrameListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			calculatorModel.setInput("break");
			clientClosed = true; 
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
