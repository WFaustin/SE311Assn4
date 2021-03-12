
public interface Visitor {
	
	public void VisitExpression(Expression e); 
	
	public void VisitOperator(Operator opr);
	
	public void VisitOperand(Operand opd);
	
	public void VisitEndOperand(EndOperand eopd);
	
}
