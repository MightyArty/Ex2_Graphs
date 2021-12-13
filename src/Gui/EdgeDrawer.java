package Gui;

import api.EdgeData;
import api.GeoLocation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * This class makes the edge from one node to another.
 */
public class EdgeDrawer {

    private final DrawArea drawArea;

    public EdgeDrawer(DrawArea drawArea) {
        this.drawArea = drawArea;
    }

    public void drawEdge(EdgeData edge, GeoLocation src, GeoLocation dst, double weight, Color color) {
        this.drawArea.drawShape(new CustomDrawing() {
            @Override
            public void draw(Graphics g) {
                g.setColor(Color.magenta);
                g.setFont(new Font("Ariel", Font.TRUETYPE_FONT, 10));

                g.drawString(String.format("%.2f", weight), (int) (src.x() * 0.25 + dst.x() * 0.75), (int) (src.y() * 0.25 + dst.y() * 0.75));
                g.setColor(color);
                drawArrowLine(g, src, dst, 15, 3);
            }
        });
    }

    private void drawArrowLine(Graphics g, GeoLocation src, GeoLocation dst, int d, int h) {
        double dx = dst.x() - src.x();
        double dy = dst.y() - src.y();
        double distance = Math.sqrt(dx * dx + dy * dy) - 10;
        double xm = distance - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / distance, cos = dx / distance;

        x = xm * cos - ym * sin + src.x();
        ym = xm * sin + ym * cos + src.y();
        xm = x;

        x = xn * cos - yn * sin + src.x();
        yn = xn * sin + yn * cos + src.y();
        xn = x;

        int[] xpoints = {(int) dst.x(), (int) xm, (int) xn};
        int[] ypoints = {(int) dst.y(), (int) ym, (int) yn};

        g.drawLine((int) src.x(), (int) src.y(), (int) dst.x(), (int) dst.y());
        g.fillPolygon(xpoints, ypoints, xpoints.length);
    }

}
