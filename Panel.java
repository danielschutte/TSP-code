package tsp;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Panel extends JPanel implements ActionListener {

    private Frame frame;
    private int PositionX, PositionY;
    protected int xRobotStart, yRobotStart;
    protected int xCurrentRobotPos, yCurrentRobotPos;
    protected Timer animation;
    private Graphics2D graphs;
    private Image logo;
    protected int locationRobot;
    protected int counter = 0;
    private int packageLocX, packageLocY;
    protected int xPackageStart, yPackageStart;
    private int xCurrentPackageLoc, yCurrentPackageLoc;
    private int locationPackage;
    private Dialog dialog;

    public Panel() {
        dialog = new Dialog(this.frame);
        this.frame = frame;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(420, 420));
        animation = new Timer((2 * dialog.getValue()), this);
        animation.addActionListener(this);
        this.xCurrentRobotPos += (100 / dialog.getValue());
        this.yCurrentRobotPos += (100 / dialog.getValue());
        this.xRobotStart = xCurrentRobotPos;
        this.yRobotStart = yCurrentRobotPos;
        this.xCurrentPackageLoc += (100 / dialog.getValue());
        this.yCurrentPackageLoc += (100 / dialog.getValue());
        this.xPackageStart = xCurrentPackageLoc;
        this.yPackageStart = yCurrentPackageLoc;

//        try
//        {
//            logo = ImageIO.read(new File("C:\\Users\\Daniel\\Downloads\\decepticon logo.png"));
//        } catch (IOException ex)
//        {
//            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void actionPerformed(ActionEvent e) {
        
        setRouteRobot();
        repaint();
    }

    public void setRouteRobot() {

        if (counter < dialog.result.route.size()) {
            StorageSpace sp = dialog.result.route.get(this.counter);
            int xx = sp.getX();
            int yy = sp.getY();
            xx = (xx * 80) - 40;
            yy = (yy * 80) - 40;
            setRobotLocation(xx, yy);

        }

    }

    public void setRobotXY(int x, int y) {
        y = (y * (400 / dialog.getValue())) + (200 / dialog.getValue());
        x = (x * (400 / dialog.getValue())) - (200 / dialog.getValue());
        //setRobotLocation(x, y);
    }

    public void setRobotLocation(int x, int y) {

        // set new robot x-position
        PositionX = x - this.xRobotStart;

        // set new robot y-position
        PositionY = y - this.yRobotStart;

        if (xCurrentRobotPos != PositionX) {
            // robot is checking if it's on said x-position
            if (xCurrentRobotPos <= PositionX) {
                // should it go right?
                if (xCurrentRobotPos != PositionX) {
                    xCurrentRobotPos += 1;
                    //    repaint();
                }
                // or should it go left?
            } else if (xCurrentRobotPos != PositionX) {
                xCurrentRobotPos -= 1;
                // repaint();
            }

        } // robot is checking if it's on said y-position
        else if (yCurrentRobotPos <= PositionY) {
            // should it go right?
            if (yCurrentRobotPos != PositionY) {
                yCurrentRobotPos += 1;
                // repaint();
            }
            // or should it go left?
        } else if (yCurrentRobotPos != PositionY) {
            yCurrentRobotPos -= 1;
            //repaint();
        }

        //stop animation if done (?)
        if (xCurrentRobotPos == PositionX && yCurrentRobotPos == PositionY) {
            this.counter++;
            setRouteRobot();
        }
    }

    public void moveRobot(int loc) {
        int width = dialog.getValue(), length;
        int x = 0, y = 0;

        while (loc > width) {
            y++;
            loc -= width;
        }
        x = loc;
        y = (y * (400 / dialog.getValue())) + (200 / dialog.getValue());
        x = (x * (400 / dialog.getValue())) - (200 / dialog.getValue());
        //setRobotLocation(x, y);
    }

    public void setPackageLocation(int x, int y) {
        //set location package
        packageLocX = x - xPackageStart;
        packageLocY = y - yPackageStart;

        if (packageLocX != xCurrentPackageLoc) {
            xCurrentPackageLoc = packageLocX;
        }
        if (packageLocY != yCurrentPackageLoc) {
            yCurrentPackageLoc = packageLocY;
        }

    }

    public void setPackage(int loc) {
        int width = dialog.getValue(), length;
        int x = 0, y = 0;

        while (loc > width) {
            y++;
            loc -= width;
        }
        x = loc;
        y = (y * (400 / dialog.getValue())) + (200 / dialog.getValue());
        x = (x * (400 / dialog.getValue())) - (200 / dialog.getValue());
        setPackageLocation(x, y);
    }

    public int setLocationToX(int loc) {
        int width = dialog.getValue(), length;
        int x = 0, y = 0;

        while (loc > width) {
            y++;
            loc -= width;
        }
        x = loc;
        y = (y * (400 / dialog.getValue())) + (100 / dialog.getValue());
        x = (x * (400 / dialog.getValue())) - (300 / dialog.getValue());

        return x;
    }

    public int setLocationToY(int loc) {
        int width = dialog.getValue(), length;
        int x = 0, y = 0;

        while (loc > width) {
            y++;
            loc -= width;
        }
        x = loc;
        y = (y * (400 / dialog.getValue())) + (100 / dialog.getValue());
        x = (x * (400 / dialog.getValue())) - (300 / dialog.getValue());

        return y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.BLACK);
        int xStart = 0;
        int yStart = 0;
        int xPackageStart = 0;
        int yPackageStart = 0;
        // draw table

        g2.drawRect(0, 0, 400, 400);
        for (int j = 0; j < dialog.getValue(); j++) {
            for (int i = 0; i < dialog.getValue(); i++) {
                g2.drawRect(xStart, yStart, 400 / dialog.getValue(), 400 / dialog.getValue());
                xStart = xStart + (400 / dialog.getValue());
            }
            xStart = 0;
            yStart = yStart + (400 / dialog.getValue());
        }

        //draw package
        int listSize = dialog.listModel.getSize();

        ArrayList DrawPackages = new ArrayList();

        if (counter < listSize) {
            for (int i = 0; i < dialog.listModel.getSize(); i++) {
                for (int j = 0; j < dialog.listModel.getSize(); j++) {
                    for (StorageSpace sp : dialog.thisList) {
                        int x1 = sp.getX();
                        int y1 = sp.getY();

                        x1 = (x1 * 80) - 40;
                        y1 = (y1 * 80) - 40;
                        g2.setColor(Color.red);
                        g2.drawRect(x1 - 20, y1 - 20, 200 / dialog.getValue(), 200 / dialog.getValue());
                    }
                }
            }
        }

//        if (xCurrentRobotPos == xCurrentRobotPos && yCurrentRobotPos == yCurrentPackageLoc)
//        {
//            g2.setStroke(new BasicStroke(10));
//            g2.setColor(Color.green);
//            g2.drawRect(xCurrentPackageLoc, yCurrentPackageLoc, 200 / dialog.getValue(), 200 / dialog.getValue());
//        }
        //draw robot
        g2.setColor(Color.green);
        g2.drawRect(xCurrentRobotPos, yCurrentRobotPos, 200 / dialog.getValue(), 200 / dialog.getValue());

    }

}
