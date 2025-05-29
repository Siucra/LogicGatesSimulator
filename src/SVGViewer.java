import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;
import com.kitfox.svg.app.beans.SVGIcon;

public class SVGViewer extends JPanel {
    private SVGIcon icon;

    public SVGViewer() {
        icon = new SVGIcon();
    }

    public void loadSVG(File file) {
        try {
            URI uri = file.toURI();
            icon.setSvgURI(uri);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (icon != null) {
            icon.paintIcon(this, g, 0, 0);
        }
    }
}
