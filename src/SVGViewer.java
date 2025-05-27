import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.io.File;

public class SVGViewer extends JPanel {
    private SVGUniverse svgUniverse;
    private SVGDiagram diagram;

    public SVGViewer() {
        svgUniverse = new SVGUniverse();
    }

    public void loadSVG(File file) {
        try {
            URI uri = file.toURI();
            diagram = svgUniverse.getDiagram(svgUniverse.loadSVG(uri.toURL()));
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (diagram != null) {
            Graphics2D g2 = (Graphics2D) g;
            try {
				diagram.render(g2);
			} catch (SVGException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
