package org.rfl.landscape;

import javax.swing.*;
import java.awt.*;

public class Landscape extends Canvas {

    private final int width;
    private final int height;

    int[][] heightmap;
    int sizex = 50;
    int sizez = 50;

    public Landscape(int width, int height) {
        super();
        this.width = width;
        this.height = height;


    }

    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        JFrame frame = new JFrame("Landscape Generator");
        Landscape landscape = new Landscape(width, height);
        landscape.setSize(width, height);
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

    public int[][] generateMiles() {
        //int[][] startboard = new int[sizex][sizex];
        int[][] board = new int[sizex][sizex];
        int point1;
        int point2;
        int point3;
        int point4;
        int point5;
        int point6;
        int point7;
        int point8;
        int point9;
        int average;
//        for (int row = 0; row < sizex; row++) {
//            startboard[row][0] = 0;
//        }
//        for (int col = 0; col < sizex; col++) {
//            startboard[0][col] = 0;
//        }
//        for (int row = 0; row < sizex; row++) {
//            startboard[row][sizex - 1] = 0;
//        }
//        for (int col = 0; col < sizex; col++) {
//            startboard[sizex - 1][col] = 0;
//        } //makes edge of zeros


        for (int row = 0; row < sizex; row++) {
            for (int col = 0; col < sizex; col++) {
                board[row][col] = (int) ((350 - row * 6) * Math.random() - 50);
            }
        } // makes first board



        for (int smooth = 0; smooth < 4; smooth++) {
            for (int row = 1; row < sizex - 1; row++) {
                for (int col = 1; col < sizez - 1; col++) {
                    //point1 = board[row][col];
                    point2 = board[row - 1][col];
                    //point3 = board[row - 1][col + 1];
                    point4 = board[row][col + 1];
                    //point5 = board[row + 1][col + 1];
                    point6 = board[row + 1][col];
                    //point7 = board[row + 1][col - 1];
                    point8 = board[row][col - 1];
                    //point9 = board[row - 1][col - 1];
                    //average = (point1 + point2 + point3 + point4 + point5 + point6 + point7 + point8 + point9) / 9;
                    //average = ( point2 + point3 + point4 + point5 + point6 + point7 + point8 + point9) / 8;
                    average = ( point2 +  point4 +  point6 +  point8) / 4;
                    board[row][col] = average;
                }
            } //smooths out board
        }

        // set front and back edges to 0
        for(int i=0; i<sizez; i++) {
            board[0][i] = 0;
            board[sizex-1][i] = 0;
        }


        // if board below 0, it's water so set it to 0
        for (int row = 0; row < sizex ; row++) {
            for (int col = 0; col < sizex ; col++) {
                if (board[row][col] < 0) board[row][col] = 0;
            }
        }
        return board;
    }


    private void drawPolygon(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int[] xcoords = {x1, x2, x3, x4};
        int[] ycoords = {y1, y2, y3, y4};
        g.drawPolygon(xcoords, ycoords, 4);
    }

    private void fillPolygon(Graphics g, Color c, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int[] xcoords = {x1, x2, x3, x4};
        int[] ycoords = {y1, y2, y3, y4};
        g.setColor(c);
        g.fillPolygon(xcoords, ycoords, 4);
    }

    public void paint(Graphics g) {
        int[][] board = generateMiles();

        g.setColor(Color.green);

        int[][] xcoords = new int[sizex][sizez];
        int[][] ycoords = new int[sizex][sizez];


        for (int z = 0; z < sizez; z++) {
            for (int x = 0; x < sizex; x++) {
                int sx = 30 + x * 8 + z * 5;
                int sy = 400 + x * 3 - z * 4;
                xcoords[x][z] = sx;
                ycoords[x][z] = sy;
                ycoords[x][z] -= board[x][z];
            }
        }

        Color c = Color.white;

        for (int z = sizex-2; z>0; z--) {
            for (int x = 0; x < sizex - 1; x++) {
                int totalZ = board[x][z] + board[x+1][z] + board[x+1][z+1] + board[x][z+1];
                int height = totalZ/4;
                if (x == 0) {
                    System.out.println(height);
                }
                boolean odd =(x+z)%2==0;

                if (height >= 90) {
                    c = c = odd? new Color(122,122,122) : new Color(100,100,100);
                } else if ((height >= 35) && (height < 90)) {
                    c = odd? new Color(192,192,192) : new Color(170,170,170);;
                } else if ((height >= 20) && (height < 35)) {
                    c = odd? new Color(0,150,0) : new Color(0,120,0);
                } else if ((height >= 3) && (height < 35)) {
                    c = odd? new Color(0,255,0) : new Color(0,225,0);
                } else if ((height > 0) && (height < 3)) {
                    c = odd? new Color(255,255,0) : new Color(225,225,0);
                } else if (height ==  0) {
                    c = odd? new Color(0,0,255) : new Color(0,0,200);;
                }




                fillPolygon(g, c,
                        xcoords[x][z], ycoords[x][z],
                        xcoords[x + 1][z], ycoords[x + 1][z],
                        xcoords[x + 1][z + 1], ycoords[x + 1][z + 1],
                        xcoords[x][z + 1], ycoords[x][z + 1]);
            }


        }
    }
}