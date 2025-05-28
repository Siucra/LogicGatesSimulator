import javax.swing.SwingUtilities;

public class LogicGateApp {
	//Main application that handles logic gates
	
	public static boolean andGate(boolean a, boolean b) {
		return a & b;
	
	}
	
	public static boolean orGate(boolean a, boolean b) {
		return a || b;
	}
	
	public static boolean notGate(boolean input) {
		return !input;
	}
	
	public static boolean xorGate(boolean a, boolean b) {
		return a ^ b; //Exclusive OR
		
	}
	
	public static boolean norGate(boolean a, boolean b) {
		return !(a || b); 
		
	}
	
	public static void main(String[]args) {
		//()-> shortcut for  writing small function with no input
		//() -> called a lambda : takes no parameters,
		//-> separates the parameter from the code i want to run
		
		SwingUtilities.invokeLater(() -> new LogicGateGUI().setVisible(true));
		
	}
}
