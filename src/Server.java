import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server extends Thread{
	
	public static final int PORT_NUMBER = 8081; 
	
	protected Socket socket; 

	private String equation; 
	
	private String extraCredEquation = ""; 
	
	private CalculatorStateContext context;
	
	private ArrayList<CalculatorState> states;
	
	private boolean hasSentLine = false;
	
	private int totalSuccessfulEquations = 0; 
	
	private ArrayList<String> equationsMade; 
	
	private int currAnswer = 0; 
	
	public Server() {
		context = new CalculatorStateContext(); 
		equation = ""; 
		states = new ArrayList<>(); 
		states.add(new StartCalculatorState());
		states.add(new FirstOperandCalculatorState());
		states.add(new FirstOperatorCalculatorState());
		states.add(new SecondOperandCalculatorState());
		states.add(new SecondOperatorCalculatorState());
		states.add(new CalculateCalculatorState());
		states.add(new ErrorCalculatorState());
		start(); 
		context.setState(states.get(0));
		equationsMade = new ArrayList<>(); 
	}
	
	public void run() {
		System.out.println("Server started here");
		try {
			
			
			ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
			socket = serverSocket.accept(); 
			
			
			InputStream iStream = socket.getInputStream();
			OutputStream oStream = socket.getOutputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(iStream)); 
			
			while (true) {
				String s; 
				if ((s = br.readLine()) != null) {
					if (s.equals("break")) {
						System.out.println("Here");
						iStream.close();
						oStream.close(); 
						serverSocket.close();
						break; 
					}
					else if(s.isEmpty() || s.trim().isEmpty()) {
						//Filters out input that is solely whitespace
						//System.out.println("Filter these out");
					}
					else {	
						switchState(oStream, s);
						if (getStateOfContext() == 5) {
							//System.out.println("Should have a concrete answer. ");
							//calculate(oStream);
						}
						else if (hasSentLine == true) {
							//System.out.println(equation);
						}
						else {
							String response = equation + "\n"; 
							oStream.write(response.getBytes());
						}
					}
				}
				else {
					System.out.println("Did not find object");
				}
				hasSentLine = false; 
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Server failed to connect to socket and run, please try again later."); 
			e.printStackTrace();
		} 
	}
	
	
	public void switchState(OutputStream out, String input) {
		
		int stateNumTranslator = -1; 

		String regex = "[0-9]";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(input); 
		if (m.find() && m.group().equals(input)) {
			stateNumTranslator = 0; 
		}
		else if(input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
			stateNumTranslator = 1; 
		}
		else if(input.equals("=")) {
			stateNumTranslator = 2; 
		}
		else if(input.equals("C")) {
			stateNumTranslator = 3; 
		}
		switch(stateNumTranslator) {
			case 0:
				if (context.getState().equals(states.get(0))) {
					equation += input; 
					extraCredEquation += input; 
					context.setState(states.get(1));
				}
				else if (context.getState().equals(states.get(1))) {
					equation += input;
					extraCredEquation += input;
					context.setState(states.get(1));
				}
				else if (context.getState().equals(states.get(2))) {
					equation += input;
					extraCredEquation += input;
					context.setState(states.get(3));
				}
				else if (context.getState().equals(states.get(3))) {
					equation += input;
					extraCredEquation += input;
					context.setState(states.get(3));
				}
				else if (context.getState().equals(states.get(4))) {
					equation += input;
					extraCredEquation += input;
					context.setState(states.get(3));
				}
				else if (context.getState().equals(states.get(5))) {
					equation = input;
					extraCredEquation = input;
					context.setState(states.get(1));
				}
				else if (context.getState().equals(states.get(6))) {
					equation = input;
					extraCredEquation = input;
					context.setState(states.get(1));
				}
				break; 
			case 1:
				if (context.getState().equals(states.get(0))) {
					context.setState(states.get(0));
				}
				else if (context.getState().equals(states.get(1))) {
					equation += input;
					extraCredEquation += input;
					context.setState(states.get(2));
				}
				else if (context.getState().equals(states.get(2))) {
					context.setState(states.get(6));
				}
				else if (context.getState().equals(states.get(3))) {
					calculate(out); 
					equation += input;
					extraCredEquation += input;
					context.setState(states.get(4));
				}
				else if (context.getState().equals(states.get(4))) {
					context.setState(states.get(6));
				}
				else if (context.getState().equals(states.get(5))) {
					equation += input;
					extraCredEquation += input;
					context.setState(states.get(3));
				}
				else if (context.getState().equals(states.get(6))) {
					context.setState(states.get(6));
				}
				break; 
			case 2:
				if (context.getState().equals(states.get(0))) {
					context.setState(states.get(0));
				}
				else if (context.getState().equals(states.get(1))) {
					context.setState(states.get(6));
				}
				else if (context.getState().equals(states.get(2))) {
					context.setState(states.get(6));
				}
				else if (context.getState().equals(states.get(3))) {
					context.setState(states.get(5));
				}
				else if (context.getState().equals(states.get(4))) {
					context.setState(states.get(6));
				}
				else if (context.getState().equals(states.get(5))) {
					context.setState(states.get(0));
				}
				else if (context.getState().equals(states.get(6))) {
					context.setState(states.get(6));
				}
				break; 
			case 3:
				context.setState(states.get(0));
				equation = ""; 
				extraCredEquation = "";
				break; 
			default:
				System.out.println("Shouldn't be here"); 
				break; 
		}
		
		if (getStateOfContext() == 5) {
			calculate(out); 
			if (stateNumTranslator == 2) {
				writeEquation();
			}
		}
	}
	
	public void writeEquation() {
		System.out.println(extraCredEquation);
		EquationTreeVisitor etv = new EquationTreeVisitor(); 
		Equation eq = new Expression(); 
		String [] checkforequals = extraCredEquation.split("=");
		extraCredEquation = checkforequals[checkforequals.length-1]; 
		String allOperators = "+-*/"; 
		String [] ece = extraCredEquation.split(""); 
		for (int i = 0; i < ece.length; i++) {
			if (allOperators.contains(ece[i])) {
				Operator op = new Operator(ece[i]); 
				eq.Add(op);
			}
			else if (i != extraCredEquation.length() - 1){
				Operand opd = new Operand(Integer.parseInt(ece[i]));
				eq.Add(opd);
			}
			else {
				Operand opd = new EndOperand(Integer.parseInt(ece[i]));
				eq.Add(opd);
			}
		}
		eq.accept(etv);
		OutputFileWriter ofw = new OutputFileWriter(); 
		ofw.printOutput(extraCredEquation + "\n" + etv.finalString); 
		totalSuccessfulEquations += 1; 
		extraCredEquation += "=" + currAnswer; 
		equationsMade.add(extraCredEquation); 
		System.out.println("Total number of equations made: " + totalSuccessfulEquations);
		for (int i = 0; i < equationsMade.size(); i++) {
			System.out.println(equationsMade.get(i));
		}
	}
	
	
	public void calculate(OutputStream out) {
		System.out.println(equation);
		CalculatorVisitor cv = new CalculatorVisitor(); 
		String regex = "\\+|-|\\*|/"; 
		String operator; 
		if(equation.contains("+")) {
			operator = "+"; 
		}
		else if(equation.contains("-") && equation.charAt(0) != '-' || equation.substring(1).contains("-")) {
			operator = "-"; 
		}
		else if(equation.contains("*")) {
			operator = "*"; 
		}
		else if(equation.contains("/")) {
			operator = "/";
		}
		else {
			operator = ""; 
		}
		String [] parts = equation.split(regex); 
		
		int [] integers = new int [parts.length]; 
		
		char lastchar = equation.charAt(equation.length()-1); 
		
		for (int i = 0; i < parts.length; i++) {
			try {
				integers[i] = Integer.parseInt(parts[i]);
			}catch(NumberFormatException e) {
				integers[i] = -1; 
			}
		}
		
		if (equation.charAt(0) == '-') {
			integers[0] = -1 * integers[1]; 
			integers[1] = integers[2];
		}
		
		if (integers.length >= 2) {
			Equation eq = new Expression(); 
			Operand firstOp = new Operand(integers[0]);
			Operator op = new Operator(operator);
			Operand lastOp = new EndOperand(integers[1]);
		
			eq.Add(firstOp);
			eq.Add(op);
			eq.Add(lastOp);
		
			eq.accept(cv);
			currAnswer = cv.getAnswer(); 
			equation = Integer.toString(cv.getAnswer());
			if (lastchar == '+' || lastchar == '-' || lastchar == '*' || lastchar == '/') {
				equation += lastchar; 
			}
			String response = equation + "\n";
			try {
				out.write(response.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Didn't work, try again."); 
				e.printStackTrace();
			}
			hasSentLine = true; 
		} 
	}
	
	public int getStateOfContext() {
		if (context.getState().equals(states.get(0))) {
			return 0; 
		}
		else if (context.getState().equals(states.get(1))) {
			return 1; 
		}
		else if (context.getState().equals(states.get(2))) {
			return 2; 
		}
		else if (context.getState().equals(states.get(3))) {
			return 3; 
		}
		else if (context.getState().equals(states.get(4))) {
			return 4; 
		}
		else if (context.getState().equals(states.get(5))) {
			return 5; 
		}
		else if (context.getState().equals(states.get(6))) {
			return 6; 
		}
		else {
			return -1;
		}
	}
	
	
}
