
public class LogicGateApp {
	//Main application that handles logic gates
	
	public static boolean andGate(boolean a, boolean b) {
		return a & b;
	
	}
	
	public static boolean orGate(boolean a, boolean b) {
		return a || b;
	}
	
	public static boolean notGate(boolean a) {
		return !a;
	}
	
	public static boolean xorGate(boolean a, boolean b) {
		return a ^ b; //Exclusive OR
		
	}
}
