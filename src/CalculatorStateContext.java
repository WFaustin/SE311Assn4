
public class CalculatorStateContext {
	
	private CalculatorState state; 
	
	public CalculatorStateContext() {
		setState(null); 
	}

	public CalculatorState getState() {
		return state;
	}

	public void setState(CalculatorState state) {
		this.state = state;
	}
	
	
	
}
