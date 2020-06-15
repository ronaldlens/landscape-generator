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

    public Landscape(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public int[][] generateMiles() {
        int[][] startboard = new int[sizex][sizex];
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
        for (int row = 0; row < sizex; row++) {
            startboard[row][0] = 0;
        }
        for (int col = 0; col < sizex; col++) {
            startboard[0][col] = 0;
        }
        for (int row = 0; row < sizex; row++) {
            startboard[row][sizex-1] = 0;
        }
        for (int col = 0; col < sizex; col++) {
            startboard[sizex-1][col] = 0;
        } //makes edge of zeros


        for (int row = 1; row <= sizex-1; row++) {
            for (int col = 1; col <= sizex-1; col++) {
                startboard[row][col] = (int) ((200-row*3) * Math.random()-50);
            }
        } // makes first board

        for (int smooth=0; smooth<7; smooth++) {
            for (int row = 1; row < sizex-1; row++) {
                for (int col = 1; col < sizex-1; col++) {
                    point1 = startboard[row][col];
                    point2 = startboard[row - 1][col];
                    point3 = startboard[row - 1][col + 1];
                    point4 = startboard[row][col + 1];
                    point5 = startboard[row + 1][col + 1];
                    point6 = startboard[row + 1][col];
                    point7 = startboard[row + 1][col - 1];
                    point8 = startboard[row][col - 1];
                    point9 = startboard[row - 1][col - 1];
                    average = (point1+  point2 + point3 + point4 + point5 + point6 + point7 + point8 + point9) / 9;
                    board[row][col] = average;
                }
            } //smooths out board
        }

//        for (int row = 1; row < sizex-1; row++){
//            for (int col = 1; col < 10; col++){
//                point1 = board[row][col];
//                point2 = board[row - 1][col];
//                point3 = board[row - 1][col + 1];
//                point4 = board[row][col + 1];
//                point5 = board[row + 1][col + 1];
//                point6 = board[row + 1][col];
//                point7 = board[row + 1][col - 1];
//                point8 = board[row][col - 1];
//                point9 = board[row - 1][col - 1];
//                average = (point1 + point2 + point3 + point4 + point5 + point6 + point7 + point8 + point9) / 9;
//                board[row][col] = average;
//            }
//        } //smooths out edges even more
//        for (int row = 1; row < sizex-1; row++){
//            for (int col = sizex-9; col < sizex-1; col++){
//                point1 = board[row][col];
//                point2 = board[row - 1][col];
//                point3 = board[row - 1][col + 1];
//                point4 = board[row][col + 1];
//                point5 = board[row + 1][col + 1];
//                point6 = board[row + 1][col];
//                point7 = board[row + 1][col - 1];
//                point8 = board[row][col - 1];
//                point9 = board[row - 1][col - 1];
//                average = (point1 + point2 + point3 + point4 + point5 + point6 + point7 + point8 + point9) / 9;
//                board[row][col] = average;
//            }
//        } //smooths out edges even more
//        for (int row = 1; row < 10; row++){
//            for (int col = 1; col < sizex-1; col++){
//                point1 = board[row][col];
//                point2 = board[row - 1][col];
//                point3 = board[row - 1][col + 1];
//                point4 = board[row][col + 1];
//                point5 = board[row + 1][col + 1];
//                point6 = board[row + 1][col];
//                point7 = board[row + 1][col - 1];
//                point8 = board[row][col - 1];
//                point9 = board[row - 1][col - 1];
//                average = (point1 + point2 + point3 + point4 + point5 + point6 + point7 + point8 + point9) / 9;
//                board[row][col] = average;
//            }
//        } //smooths out edges even more
//        for (int row = 1; row < sizex-1; row++){
//            for (int col = sizex-9; col < sizex-1; col++){
//                point1 = board[row][col];
//                point2 = board[row - 1][col];
//                point3 = board[row - 1][col + 1];
//                point4 = board[row][col + 1];
//                point5 = board[row + 1][col + 1];
//                point6 = board[row + 1][col];
//                point7 = board[row + 1][col - 1];
//                point8 = board[row][col - 1];
//                point9 = board[row - 1][col - 1];
//                average = (point1 + point2 + point3 + point4 + point5 + point6 + point7 + point8 + point9) / 9;
//                board[row][col] = average;
//            }
//        } //smooths out edges even more
        for (int row = 1; row < sizex-1; row++) {
            for (int col = 1; col < sizex-1; col++) {
                if (board[row][col]<0) board[row][col]=0;
            }
        }
        return board;
    }



    private void drawPolygon(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int[] xcoords = {x1, x2, x3, x4};
        int[] ycoords = {y1, y2, y3, y4};
        g.drawPolygon(xcoords, ycoords, 4);
    }

    public void paint(Graphics g) {
        int[][] board = generateMiles();

        g.setColor(Color.green);

        int[][] xcoords = new int[sizex][sizez];
        int[][] ycoords = new int[sizex][sizez];


        for (int z = 0; z < sizez; z++) {
            for (int x = 0; x < sizex; x++) {
                int sx = 30 + x * 8 + z * 6;
                int sy = 400 + x * 3 - z * 4;
                xcoords[x][z] = sx;
                ycoords[x][z] = sy;
                ycoords[x][z] -= board[x][z];
            }
        }

        for (int z = 0; z < sizez - 1; z++) {
            for (int x = 0; x < sizex - 1; x++) {

                drawPolygon(g,
                        xcoords[x][z], ycoords[x][z],
                        xcoords[x + 1][z], ycoords[x + 1][z],
                        xcoords[x + 1][z + 1], ycoords[x + 1][z + 1],
                        xcoords[x][z + 1], ycoords[x][z + 1]);
            }
        }
    }


}
