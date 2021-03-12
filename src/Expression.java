
public class Expression extends Equation implements Element {
	
	
	public Expression() {
		super(); 
	}
	
	public Expression(int num) {
		super(); 
	}
	
	public Expression(String num) {
		super(); 
	}

	public String getOperator() {
		return ""; 
	}

	public void setOperator(String operator) {
		return; 
	}

	public int getValue() {
		return -1; 
	}

	public void setValue(int value) {
		return; 
	}
	
	public void accept(Visitor v) {
		v.VisitExpression(this);
		for (int i = 0; i < children.size(); i++) {
			children.get(i).accept(v);
		}
	}
	
}
