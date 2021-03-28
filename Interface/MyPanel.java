package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import code.*;



public class MyPanel extends JPanel {

    private double zoomFactor = 1;
    private double prevZoomFactor = 1.1;
    private boolean zoomer = true;

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (zoomer) {
            AffineTransform at = new AffineTransform();
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g2.transform(at);
            zoomer = true;
        }

        ArrayList a = Map.getSegmentList();

        for (int i = 0; i < a.size(); i++) {     // pas oublier de suppri si inutile apres

            Segment segment = (Segment) a.get(i);
            Shape l = new Line2D.Double((segment.getUpper_point().getX()), (segment.getUpper_point().getY()), (segment.getLower_point().getX()), (segment.getLower_point().getY()));
            g2.draw(l);
        }


    }


    public void setZoom(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        this.getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
        update(this.getGraphics());
    }

    public double getZoomFactor() {
        return zoomFactor;
    }
}

