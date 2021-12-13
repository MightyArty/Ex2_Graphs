package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * In this class we make the dots (nodes),we declare the size of it and which font we are using
 */
public class Dot extends JPanel {

    private double x, y;
    private int id;
    public static final int DIAMETER = 20;

    public Dot(int id, double x, double y) {
        super();
        setBounds((int) x - DIAMETER / 2, (int) y - DIAMETER / 2, DIAMETER, DIAMETER);
        this.x = x;
        this.y = y;
        this.id = id;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillOval(0, 0, DIAMETER, DIAMETER);
        if (id > 9) {
            g2d.setFont(new Font("Ariel", Font.BOLD, 10));
        } else {
            g2d.setFont(new Font("Ariel", Font.BOLD, 12));
        }

        g2d.setColor(Color.GREEN);
        g2d.drawString(Integer.toString(this.id), DIAMETER / 4, DIAMETER * 3 / 4);
    }

}
