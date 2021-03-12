
public class Operand extends Equation implements Element{
	
	public Operand() {
		super();  
	}
	
	public Operand(int num) {
		super(num);
	}
	
	public Operand(String n) {
		super();  
	}
	
	public String getOperator() {
		return ""; 
	}

	public void setOperator(String operator) {
		return; 
	}
	
	public void Add(Equation f) {
		return; 
	}
	
	public void Remove(Equation f) {
		return; 
	}
	
	public Equation GetChild(int index) {
		return null; 
	}
	
	public void accept(Visitor v) {
		v.VisitOperand(this);
	}

}
