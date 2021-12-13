package Gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handle drawing panel
 */
public class DrawArea extends JLayeredPane {

    /**
     * the drawings  that are drown in the draw area
     */
    private List<CustomDrawing> drawings;

    public DrawArea() {
        super();
        drawings = new ArrayList<>();
    }

    /**
     * draw a custom shape given in param
     * @param drawing
     */
    public void drawShape(CustomDrawing drawing) {
        this.drawings.add(drawing);
        super.repaint();
    }

    /**
     * draw the drawings in the list in every resizing/ or change
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawings.forEach( drawing->drawing.draw(g));
    }

    /**
     * clear the drawing area
     */
    public void clear() {
        this.removeAll();
        this.drawings.clear();
        this.repaint();
    }

}
