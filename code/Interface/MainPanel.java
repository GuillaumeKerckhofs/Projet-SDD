package code.Interface;

import code.logique.Algo;
import code.logique.Map;
import code.logique.Segment;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

    /**
     *
     * @author Thanasis1101
     * @version 1.0
     *
     * https://stackoverflow.com/users/7053344/thanasis1101
     * https://stackoverflow.com/questions/6543453/zooming-in-and-zooming-out-within-a-panel
     *
     * Overwrite par Brenart Thomas et Kerckhofs Guillame dans le cadre d'un projet d'étude
     *
     */
    public class MainPanel extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener {


        private double zoomFactor = 1;
        private double prevZoomFactor = 1;
        private boolean zoomer;
        private boolean dragger;
        private boolean released;
        private double xOffset = 0;
        private double yOffset = 0;
        private int xDiff;
        private int yDiff;
        private int sweeplinecount =0;
        private Point startPoint;
        ArrayList<code.logique.Point> b = new ArrayList<code.logique.Point>();

        public MainPanel() {


            initComponent();

        }

        public void setSweeplinecount(int sweeplinecount) {
            this.sweeplinecount = sweeplinecount;
        }

        public int getSweeplinecount() {
            return sweeplinecount;
        }

        private void initComponent() {
            addMouseWheelListener(this);
            addMouseMotionListener(this);
            addMouseListener(this);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            Graphics2D g2 = (Graphics2D) g;

            if (zoomer) {
                AffineTransform at = new AffineTransform();

                double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
                double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

                double zoomDiv = zoomFactor / prevZoomFactor;

                xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
                yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

                at.translate(xOffset, yOffset);
                at.scale(zoomFactor, zoomFactor);
                prevZoomFactor = zoomFactor;
                g2.transform(at);
                zoomer = false;
            }

            if (dragger) {
                AffineTransform at = new AffineTransform();
                at.translate(xOffset + xDiff, yOffset + yDiff);
                at.scale(zoomFactor, zoomFactor);
                g2.transform(at);

                if (released) {
                    xOffset += xDiff;
                    yOffset += yDiff;
                    dragger = false;
                }

            }

            // All drawings go here



            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);


            ArrayList a = Map.getSegmentList();
            b = Algo.getIntersection();
            int graph_constant_x=100;
            int graph_constant_y=300;
            int graph_gap=10;

            for (int i = 0; i < a.size(); i++) {     // pas oublier de suppri si inutile apres

                Segment segment = (Segment) a.get(i);
                Shape l = new Line2D.Double((segment.getUpper_point().getX())+graph_constant_x, -(segment.getUpper_point().getY())+graph_constant_y, (segment.getLower_point().getX())+100, -(segment.getLower_point().getY())+graph_constant_y);
                g2.draw(l);
            }

            for (code.logique.Point inter:b){
                Shape l2 = new Line2D.Double(inter.getX()+graph_constant_x,-inter.getY()+graph_constant_y,inter.getX()+graph_constant_x,-inter.getY()+graph_constant_y);
                g2.setColor(Color.red);
                g2.draw(l2);

            }

            for (int i=0; i<100;i++){
                Shape l2 = new Line2D.Double(i*graph_gap+graph_constant_x,-0+graph_constant_y,i*graph_gap+graph_constant_x,-1+graph_constant_y);
                g2.setColor(Color.lightGray);
                g2.draw(l2);

                Font myFont = new Font ("Courier New", 1, 3);
                g.setFont (myFont);
                g.setColor(Color.black);
                g.drawString(String.valueOf(i*graph_gap), (i*graph_gap)+graph_constant_x-2,0+graph_constant_y+5);
            }

            for (int i=0; i<100;i++){
                Shape l2 = new Line2D.Double(-0+graph_constant_x,i*graph_gap-graph_constant_y,-1+graph_constant_x,i*graph_gap-graph_constant_y);
                g2.setColor(Color.lightGray);
                g2.draw(l2);

                Font myFont = new Font ("Courier New", 1, 3);
                g.setFont (myFont);
                g.setColor(Color.black);
                g.drawString(String.valueOf(i*graph_gap), -7+graph_constant_x,-(i*graph_gap)+graph_constant_y);

            }


            Shape axe_x = new Line2D.Double(-10000+graph_constant_x,0+graph_constant_y,10000+graph_constant_x,0+graph_constant_y);
            g2.setColor(Color.lightGray);
            g2.draw(axe_x);

            Shape axe_y = new Line2D.Double(0+graph_constant_x,-10000+graph_constant_y,0+graph_constant_x,10000+graph_constant_y);
            g2.setColor(Color.lightGray);
            g2.draw(axe_y);

            if (sweeplinecount<0){
                sweeplinecount=0;
            }
            else if (sweeplinecount>=Algo.getPrintQ().size()){
                sweeplinecount=Algo.getPrintQ().size()-1;
            }

            Shape sweepline = new Line2D.Double(0+graph_constant_x,-Algo.getPrintQ().get(sweeplinecount).getY()+graph_constant_y,1000+graph_constant_x,-Algo.getPrintQ().get(sweeplinecount).getY()+graph_constant_y);
            g2.setColor(Color.blue);
            g2.draw(sweepline);
            Shape point = new Line2D.Double(Algo.getPrintQ().get(sweeplinecount).getX()+graph_constant_x,-Algo.getPrintQ().get(sweeplinecount).getY()+graph_constant_y,Algo.getPrintQ().get(sweeplinecount).getX()+graph_constant_x,-Algo.getPrintQ().get(sweeplinecount).getY()+graph_constant_y);
            g2.setColor(Color.green);
            g2.draw(point);


        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

            zoomer = true;

            //Zoom in
            if (e.getWheelRotation() < 0) {
                zoomFactor *= 1.1;
                repaint();
            }
            //Zoom out
            if (e.getWheelRotation() > 0) {
                zoomFactor /= 1.1;
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point curPoint = e.getLocationOnScreen();
            xDiff = curPoint.x - startPoint.x;
            yDiff = curPoint.y - startPoint.y;

            dragger = true;
            repaint();

        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            released = false;
            startPoint = MouseInfo.getPointerInfo().getLocation();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            released = true;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        public void setB(ArrayList<code.logique.Point> b) {
            this.b = b;
        }
    }

