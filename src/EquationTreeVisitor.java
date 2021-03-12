
public class EquationTreeVisitor implements Visitor {
	
	public String finalString = "";
	private String saveLastOper = ""; 

	@Override
	public void VisitExpression(Expression e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void VisitOperator(Operator opr) {
		// TODO Auto-generated method stub
		saveLastOper = opr.getOperator() + "\n| \\" + "\n";
	}

	@Override
	public void VisitOperand(Operand opd) {
		// TODO Auto-generated method stub
		if (!(finalString.isEmpty() || finalString.trim().isEmpty())) {
			finalString = saveLastOper + finalString.substring(0, 1) + "  " + opd.getValue() + finalString.substring(1);
		}
		else {
			finalString += opd.getValue();
		} 
	}

	@Override
	public void VisitEndOperand(EndOperand eopd) {
		// TODO Auto-generated method stub
		finalString = saveLastOper + finalString.substring(0, 1) + "  " + eopd.getValue() + finalString.substring(1);
	}

}
