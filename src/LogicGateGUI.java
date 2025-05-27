import javax.swing.*; //Provides GUI Components
import java.awt.*; //Provides basic GUI tools
import java.awt.event.*; // Provides event listeners
import java.io.File;

//JFrame is the basic SWING window 
public class LogicGateGUI extends JFrame{
	
	private JCheckBox inputA, inputB; //check-boxes
	private JLabel outputLabel; // displays output
	private JComboBox<String> gateSelector; //drop-down menu to choose logic gate
	private SVGViewer viewer; //display logic gate SVG image
	
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
		
		JButton evaluateButton = new JButton("Evaluate");//creates a combo box with gate names
		evaluateButton.addActionListener(e -> evaluateGate());//listener event for evaluate button
		
		SVGViewer viewer = new SVGViewer(); //viewer for SVG logic gates
		viewer.loadSVG(new File("and_gate.svg")); //default gate on load
		
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
		case "XOR" -> "images/XOR_gate.svg";
		case "NOT (A only)" -> "images/NOT_A_gate.svg";
		case "NOT (B only)" -> "images/NOT_B_gate.svg";
		case "NOR" -> "images/NOR_gate.svg";
		default -> null;
		};
		
		if (fileName != null) {
			viewer.loadSVG(new File(fileName));
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
			case "AND" -> result = LogicGateApp.andGate(a,b);
			case "OR" -> result = LogicGateApp.orGate(a,b);
			case "XOR" -> result = LogicGateApp.xorGate(a, b);
			case "NOT (A only)" -> result = LogicGateApp.notGate(a);
			case "NOT (B only)" -> result = LogicGateApp.notGate(b);
			case "NOR" -> result = LogicGateApp.norGate(a, b);
			
		}
		outputLabel.setText("Output: " + result);//display result

	}
	
	
}
