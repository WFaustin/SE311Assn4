import java.util.ArrayList;

public abstract class Equation {
	
	protected ArrayList<Equation> children; 
	
	protected int value; 
	
	protected String operator; 
	
	public Equation() {
		setValue(-1); 
		setOperator(""); 
		children = new ArrayList<>(); 
	}
	
	public Equation(int num) {
		setValue(num); 
		setOperator(""); 
		children = new ArrayList<>(); 
	}
	
	public Equation(String op) {
		setValue(-1); 
		if (!(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"))) {
			setOperator("+"); 
		}
		else {
			setOperator(op);
		}
		children = new ArrayList<>(); 
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void Add(Equation f) {
		children.add(f); 
	}
	
	public void Remove(Equation f) {
		if (children.contains(f)) {
			children.remove(f); 
		}
	}
	
	public Equation GetChild(int index) {
		return children.get(index);
	}
	
	
	public void accept(Visitor v) {
		return; 
	}
	
	
}
