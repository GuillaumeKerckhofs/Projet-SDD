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

            for (int i = 0; i < a.size(); i++) {     // pas oublier de suppri si inutile apres

                Segment segment = (Segment) a.get(i);
                Shape l = new Line2D.Double((segment.getUpper_point().getX())+100, -(segment.getUpper_point().getY())+300, (segment.getLower_point().getX())+100, -(segment.getLower_point().getY())+300);
                g2.draw(l);
            }

            for (code.logique.Point inter:b){
                Shape l2 = new Line2D.Double(inter.getX()+100,-inter.getY()+300,inter.getX()+100,-inter.getY()+300);
                g2.setColor(Color.red);
                g2.draw(l2);

            }

            for (int i=0; i<100;i++){
                Shape l2 = new Line2D.Float(i*10+100,-0+300,i*10+100,-1+300);
                g2.setColor(Color.lightGray);
                g2.draw(l2);

                Font myFont = new Font ("Courier New", 1, 3);
                g.setFont (myFont);
                g.setColor(Color.black);
                g.drawString(String.valueOf(i*10), (i*10)+98,0+300+5);
            }

            for (int i=0; i<100;i++){
                Shape l2 = new Line2D.Float(-0+100,i*10-300,-1+100,i*10-300);
                g2.setColor(Color.lightGray);
                g2.draw(l2);

                Font myFont = new Font ("Courier New", 1, 3);
                g.setFont (myFont);
                g.setColor(Color.black);
                g.drawString(String.valueOf(i*10), -7+100,-(i*10)+300);

            }


            Shape axe_x = new Line2D.Float(-10000+100,0+300,100000+100,0+300);
            g2.setColor(Color.lightGray);
            g2.draw(axe_x);

            Shape axe_y = new Line2D.Float(0+100,-10000+300,0+100,10000+300);
            g2.setColor(Color.lightGray);
            g2.draw(axe_y);

            if (sweeplinecount<0){
                sweeplinecount=0;
            }
            else if (sweeplinecount>=Algo.getPrintQ().size()){
                sweeplinecount=Algo.getPrintQ().size()-1;
            }

            Shape sweepline = new Line2D.Double(0+100,-Algo.getPrintQ().get(sweeplinecount).getY()+300,1000+100,-Algo.getPrintQ().get(sweeplinecount).getY()+300);
            g2.setColor(Color.blue);
            g2.draw(sweepline);
            Shape point = new Line2D.Double(Algo.getPrintQ().get(sweeplinecount).getX()+100,-Algo.getPrintQ().get(sweeplinecount).getY()+300,Algo.getPrintQ().get(sweeplinecount).getX()+100,-Algo.getPrintQ().get(sweeplinecount).getY()+300);
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

