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
		setTitle("Logic Gate Simulator");//title
		setSize(600,400); //size of screen
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //exits program when window is closed
		setLayout(new BorderLayout());
		
		//controls panel
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new FlowLayout());
		
		//declaring variables
		inputA = new JCheckBox("Input A");
		inputB = new JCheckBox("Input B");
		outputLabel = new JLabel("Output: false");//checkbox initial false value
		
		String[] gates = {"AND", "OR", "XOR", "NOT (A only)","NOT (B only)"};
		gateSelector = new JComboBox<>(gates);
		
		JButton evaluateButton = new JButton("Evaluate");//creates a combo box with gate names
		evaluateButton.addActionListener(e -> evaluateGate());//listener event for evaluate button
		
		imageLabel = new JLabel(); //constructs an image
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(controlsPanel, BorderLayout.NORTH);
		add(imageLabel, BorderLayout.CENTER);
		
		//lambda : when action happens on gateSelector, run updateGateImage()
		gateSelector.addActionListener(e -> updateGateImage());
		
		//adds each GUI component
		controlsPanel.add(gateSelector);
		controlsPanel.add(inputA);
		controlsPanel.add(inputB);
		controlsPanel.add(outputLabel);
		controlsPanel.add(evaluateButton);
		
	}

	private void updateGateImage() {
		String selectedGate = (String) gateSelector.getSelectedItem();
		
		//uses switch expression to choose a value for imagepath
		//-> lambda expression : sepearates parameters
		String imagePath = switch(selectedGate) {
		case "AND" -> "images/AND_gate.png";
		case "OR" -> "images/OR_gate.png";
		case "XOR" -> "images/XOR_gate.png";
		case "NOT (A only)" -> "images/NOT_A_gate.png";
		case "NOT (B only)" -> "";
		default -> null;
		};

		//error catching
		if (imagePath != null) {
			ImageIcon icon = new ImageIcon(imagePath);
			Image scaled = icon.getImage().getScaledInstance(200,150, Image.SCALE_SMOOTH);
			imageLabel.setIcon(new ImageIcon(scaled));
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
				
				//AND image replacement
				String imagePath = "images/AND_gate_Evaluated.png";
				ImageIcon icon = new ImageIcon(imagePath);
				Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
				imageLabel.setIcon(new ImageIcon(scaled));
				break;//exits loop
			}
			case "OR":{
				result = LogicGateApp.orGate(a,b);
				
				//OR image replacement
				String imagePath = "images/OR_gate_Evaluated.png";
				ImageIcon icon = new ImageIcon(imagePath);
				Image scaled = icon.getImage().getScaledInstance(200,150,Image.SCALE_SMOOTH);
				imageLabel.setIcon(new ImageIcon(scaled));
				break;//exits loop
			}
			case "XOR":{
				result = LogicGateApp.xorGate(a, b);
				
				//XOR image replacement
				String imagePath = "images/XOR_gate_Evaluated.png";
				ImageIcon icon = new ImageIcon(imagePath);
				Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
				imageLabel.setIcon(new ImageIcon(scaled));
				break;//exits loop
			}
			case "NOT (A only)":{
				result = LogicGateApp.notGate(a);
				
				//NOT A Image replacement
				String imagePath = "images/NOT_A_gate_Evaluated.png";
				ImageIcon icon = new ImageIcon(imagePath);
				Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);	
				imageLabel.setIcon(new ImageIcon(scaled));
				break;//exits loop
			}
			case "NOT (B only)":{
				result = LogicGateApp.notGate(b);
				
				//NOT B Image Replacement
				String imagePath = "";
				ImageIcon icon = new ImageIcon(imagePath);
				Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
				imageLabel.setIcon(new ImageIcon(scaled));
				break;//exit loop
				}
			
		}
		outputLabel.setText("Output: " + result);//display result

	}
	
	
}
