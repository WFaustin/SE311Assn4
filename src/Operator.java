
public class Operator extends Equation implements Element {
	
	
	public Operator() {
		super();  
	}
	
	public Operator(int num) {
		super();
	}
	
	public Operator(String n) {
		super(n);  
	}
	
	public int getValue() {
		return -1; 
	}

	public void setValue(int value) {
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
		v.VisitOperator(this);
	}
	
}
