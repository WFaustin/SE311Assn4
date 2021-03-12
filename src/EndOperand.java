
public class EndOperand extends Operand implements Element{
	
	public EndOperand() {
		super();  
	}
	
	public EndOperand(int num) {
		super(num);
	}
	
	public EndOperand(String n) {
		super();  
	}
	
	public void accept(Visitor v) {
		v.VisitEndOperand(this);
	}

}
