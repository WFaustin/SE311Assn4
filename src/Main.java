
public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GUI g = new GUI(); 
		System.out.println("Starting project...");
		
		Client c = new Client(); 
		Server s = new Server(); 
		
		
		
		while(true) {
			if(c.isClientClosed() == true) {
				System.out.println("Server and client ending");
				break; 
			}
		}
		
		s.stop(); 
		
	}

}
