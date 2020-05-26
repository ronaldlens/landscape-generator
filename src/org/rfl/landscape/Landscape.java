package org.rfl.landscape;

import javax.swing.*;
import java.awt.*;

public class Landscape extends Canvas {

    private final int width;
    private final int height;

    int[][] heightmap;
    int sizex = 50;
    int sizez = 50;

    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        JFrame frame = new JFrame("Landscape");
        Landscape landscape = new Landscape(width, height);
        landscape.setSize(width,height);
        frame.add(landscape);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setResizable(false);

        // when the close ball is clicked, exit the app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // place it not related to anything else
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    public Landscape(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public void generate() {
        heightmap = new int[sizex][sizez];
        for(int x=0; x<sizex; x++) {
            for(int z=0; z<sizez; z++) {
                heightmap[x][z] = 0;
            }
        }
    }

    private void drawPolygon(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int[] xcoords = {x1, x2, x3, x4};
        int[] ycoords = {y1, y2, y3, y4};
        g.drawPolygon(xcoords, ycoords, 4);
    }

    public void paint(Graphics g) {
        generate();

        g.setColor(Color.green);

        int[][] xcoords = new int[sizex][sizez];
        int[][] ycoords = new int[sizex][sizez];



        for(int z=0; z<sizez; z++) {
            for(int x=0; x<sizex; x++) {
                int sx = 30 + x*8 + z*6;
                int sy = 400 + x*3 - z*4;
                xcoords[x][z] = sx;
                ycoords[x][z] = sy;
            }
        }

        for(int z=0; z<sizez-1; z++) {
            for (int x = 0; x < sizex-1; x++) {
                drawPolygon(g,
                        xcoords[x][z], ycoords[x][z],
                        xcoords[x+1][z], ycoords[x+1][z],
                        xcoords[x+1][z+1], ycoords[x+1][z+1],
                        xcoords[x][z+1], ycoords[x][z+1]);
            }
        }
    }


}
