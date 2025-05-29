import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;
import com.kitfox.svg.app.beans.SVGIcon;

public class SVGViewer extends JPanel {
    private SVGIcon icon;

    public SVGViewer() {
        icon = new SVGIcon();
    }

    public void loadSVG(File file) {
        try {
            icon.setSvgURI(file.toURI());
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //superclass method clears the panel and prepares for a new drawing

        if (icon != null && icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {//checks if svg has valid dimensions
            Graphics2D g2 = (Graphics2D) g.create();//copies the original to scale without affecting original svg

            //gets current size of panel screen
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            //original width and height of svg
            int iconWidth = icon.getIconWidth();
            int iconHeight = icon.getIconHeight();

            //calcs how much svg has to scale to fit panel
            double scaleX = (double) panelWidth / iconWidth;
            double scaleY = (double) panelHeight / iconHeight;
            
            double scale = Math.min(scaleX, scaleY); // maintain aspect ratio

            //calcs pixel dimensions of scaled svg
            int scaledWidth = (int) (iconWidth * scale);
            int scaledHeight = (int) (iconHeight * scale);

            //centres on panel
            int x = (panelWidth - scaledWidth) / 2;
            int y = (panelHeight - scaledHeight) / 2;

            g2.translate(x, y);
            g2.scale(scale, scale);
            icon.paintIcon(this, g2, 0, 0);

            g2.dispose();
        }
    }
}
