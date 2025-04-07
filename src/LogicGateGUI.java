import javax.swing.*; //Provides GUI Components
import java.awt.*; //Provides basic GUI tools
import java.awt.event.*; // Provides event listeners

//JFrame is the basic SWING window 
public class LogicGateGUI extends JFrame{
	
	private JCheckBox inputA, inputB; //checkboxes
	private JLabel outputLabel; // displays output
	private JComboBox<String> gateSelector; //dropdown menu to choose logic gate
	private JLabel imageLabel; //display logic gate image
	
	public LogicGateGUI() {
		//constructor for GUI layout
		setTitle("My Logic Gate Simulator");//title
		setSize(600,400); //size of screen
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //exits program when window is closed
		setLayout(new FlowLayout());
		
		//declaring variables
		inputA = new JCheckBox("Input A");
		inputB = new JCheckBox("Input B");
		outputLabel = new JLabel("Output: false");//checkbox initial false value
		
		String[] gates = {"AND", "OR", "XOR", "NOT (A only)"};
		gateSelector = new JComboBox<>(gates);
		
		JButton evaluateButton = new JButton("Evaluate");//creates a combo box with gate names
		evaluateButton.addActionListener(e -> evaluateGate());//listener event
		
		imageLabel = new JLabel(); //constructs an image
		add(imageLabel); //adds image to the main GUI
		
		gateSelector.addActionListener(e -> updateGateImage());
		
		//adds each GUI component
		add(gateSelector);
		add(inputA);
		add(inputB);
		add(outputLabel);
		add(evaluateButton);
		
		
	}

	private void updateGateImage() {
		String selectedGate = (String) gateSelector.getSelectedItem();
		
		String imagePath = switch(selectedGate) {
		case "AND" -> "images/AND_Gate.png";
		default -> null;
		};

		//error catching
		if (imagePath != null) {
			imageLabel.setIcon(new ImageIcon(imagePath));
		}
		else {
			imageLabel.setIcon(null);
		}
	}

	public void evaluateGate() {
		//gets the value of the checkboxes, if checked set to true
		boolean a = inputA.isSelected();
		boolean b = inputB.isSelected();
		
		//gets selected item from dropdown menu
		String selectedGate = (String) gateSelector.getSelectedItem();
		boolean result = false;
		
		switch(selectedGate) {
			case "AND":{
				result = LogicGateApp.andGate(a,b);

				break;//exits loop
			}
			case "OR":{
				result = LogicGateApp.orGate(a,b);
				break;//exits loop
			}
			case "XOR":{
				result = LogicGateApp.xorGate(a, b);
				break;//exits loop
			}
			case "NOT (A only)":{
				result = LogicGateApp.notGate(a);
				break;//exits loop
			}
		}
		outputLabel.setText("Output: " + result);//display result
	}
	
	
}
