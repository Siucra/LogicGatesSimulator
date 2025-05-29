import javax.swing.*; //Provides GUI Components
import java.awt.*; //Provides basic GUI tools
import java.awt.event.*; // Provides event listeners
import java.io.File;

//JFrame is the basic SWING window 
public class LogicGateGUI extends JFrame{
	
	private JCheckBox inputA, inputB; //check-boxes
	private JLabel outputLabel; // displays output
	private JComboBox<String> gateSelector; //drop-down menu to choose logic gate
	private SVGViewer viewer;
	
	public LogicGateGUI() {//constructor for GUI layout
		setTitle("Logic Gate Simulator");
		setSize(600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //exits program when window is closed
		setLayout(new BorderLayout());
		
		//controls panel
		JPanel controlsPanel = new JPanel(new FlowLayout());
		
		//declaring variables
		inputA = new JCheckBox("Input A");
		inputB = new JCheckBox("Input B");
		outputLabel = new JLabel("Output: false");//check-box initial false value
		
		String[] gates = {"AND", "OR", "XOR", "NOT (A only)","NOT (B only)","NOR"};
		gateSelector = new JComboBox<>(gates);

		// Create SVG viewer before wiring action listeners
		viewer = new SVGViewer();
		viewer.loadSVG(new File("images/and_gate.svg")); // default gate on load
		
		JButton evaluateButton = new JButton("Evaluate");//creates a combo box with gate names
		evaluateButton.addActionListener(e -> evaluateGate());//listener event for evaluate button
		
		//adds each GUI component
		controlsPanel.add(gateSelector);
		controlsPanel.add(inputA);
		controlsPanel.add(inputB);
		controlsPanel.add(outputLabel);
		controlsPanel.add(evaluateButton);
		
		add(controlsPanel, BorderLayout.NORTH);
		add(viewer, BorderLayout.CENTER);
		
		//lambda : when action happens on gateSelector, run updateGateImage()
		gateSelector.addActionListener(e -> updateGateImage());
		
	}

	private void updateGateImage() {
		String selectedGate = (String) gateSelector.getSelectedItem();
		
		//uses switch expression to choose a value for image-path
		//-> lambda expression : separates parameters
		String fileName = switch(selectedGate) {
		case "AND" -> "images/AND_gate.svg";
		case "OR" -> "images/OR_gate.svg";
		case "NOT (A only)" -> "images/NOT_A_gate.svg";
		case "NOT (B only)" -> "images/NOT_B_gate.svg";
		case "NOR" -> "images/NOR_gate.svg";
		case "XOR" -> "images/XOR_gate.svg";
		//nand
		default -> null;
		};
		
		if (fileName != null) {
			viewer.loadSVG(new File(fileName));
		}

	}
	
	private void switchToEvaluatedGate(String gateName) {
		
		String baseFileName = switch(gateName) {//get the name of the file
		case "AND" -> "AND_gate";
		case "OR" -> "OR_gate";
		case "NOT (A only)" -> "NOT_A_gate";
		case "NOT (B only)" -> "NOT_B_gate";
		case "NOR" -> "NOR_gate";
		default -> null;
		};
		
		if(baseFileName != null) {
			File evaluatedFile = new File("images/" + baseFileName + "_Evaluated.svg");
			if(evaluatedFile.exists()) {
				viewer.loadSVG(evaluatedFile);
			}
			else {
				viewer.loadSVG(new File("images/" + baseFileName +".svg")); //load back to default gate
				System.out.println("Evaluated SVG not found for "+ gateName +", using default.");
			}
			
		}
		
	}
	

	public void evaluateGate() {
		//gets the value of the check-boxes, if checked set to true
		boolean a = inputA.isSelected();
		boolean b = inputB.isSelected();
		
		//gets selected item from drop-down menu
		String selectedGate = (String) gateSelector.getSelectedItem();
		boolean result = false;
		
		switch(selectedGate) {
			case "AND" -> result = LogicGateApp.andGate(a,b);
			case "OR" -> {result = LogicGateApp.orGate(a,b);
			switchToEvaluatedGate("OR");
			}
			
			case "NOT (A only)" -> result = LogicGateApp.notGate(a);
			case "NOT (B only)" -> result = LogicGateApp.notGate(b);
			case "NOR" -> result = LogicGateApp.norGate(a, b);
			case "XOR" -> result = LogicGateApp.xorGate(a, b);
		}
		outputLabel.setText("Output: " + result);//display result

	}
	
	
}
