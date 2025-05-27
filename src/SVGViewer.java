//classes imported from SVG Salamander library for handling SVG files
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;

public class SVGViewer extends JPanel {
    private SVGDiagram diagram;

    public SVGViewer(String svgFilePath) {
        try {
            SVGUniverse universe = new SVGUniverse(); // Create an SVGUniverse, which manages loading and caching SVG files
            URI svgURI = universe.loadSVG(new File(svgFilePath).toURI().toURL());
            diagram = universe.getDiagram(svgURI);

            if (diagram == null) {// Checks if loading failed
                System.out.println("Failed to load SVG diagram: " + svgFilePath);
            }
        } catch (Exception e) {
            System.out.println("Error loading SVG file: " + svgFilePath);
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {// Called by Swing when the panel needs to be repainted
        super.paintComponent(g);// Clear the background
        if (diagram != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            try {
				diagram.render(g2);
			} catch (SVGException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public static void main(String[] args) {// The main method that creates a simple window and adds the SVG viewer
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simple SVG Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null); // Center window
            frame.add(new SVGViewer("and_gate.svg")); // Ensure file path is correct
            frame.setVisible(true);// Make the window visible
        });
    }
}
