
public class CalculatorVisitor implements Visitor{
	
	private int firstOperand; 
	private String operator; 
	private int secondOperand; 
	private int answer; 

	
	@Override
	public void VisitExpression(Expression e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void VisitOperator(Operator opr) {
		// TODO Auto-generated method stub
		operator = opr.getOperator(); 
	}

	@Override
	public void VisitOperand(Operand opd) {
		// TODO Auto-generated method stub
		firstOperand = opd.getValue(); 
	}

	@Override
	public void VisitEndOperand(EndOperand eopd) {
		// TODO Auto-generated method stub
		secondOperand = eopd.getValue(); 
		switch(operator) {
		case "+":
			answer = firstOperand + secondOperand; 
			break; 
		case "-":
			answer = firstOperand - secondOperand; 
			break; 
		case "*":
			answer = firstOperand * secondOperand; 
			break; 
		case "/":
			if(secondOperand != 0) {
				answer = firstOperand / secondOperand; 
			}
			else {
				answer = 0;
			}
			break; 
		default:
			answer = 0; 
			break; 
		}
	}

	public int getAnswer() {
		return answer;
	}

}
