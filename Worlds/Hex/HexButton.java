package Worlds.Hex;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;
import javax.swing.JButton;

public class HexButton extends JButton implements Serializable{
        private static final int SIDES = 6;
        private int side_length;
        public int width;

        public HexButton() {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
        }

        @Override
        public void paintComponent(Graphics g) {
            Polygon hex = new Polygon();
            for (int i = 0; i < SIDES; i++) {
                hex.addPoint((int) (width/2 + side_length * Math.cos(Math.PI/6 + i * 2 * Math.PI / SIDES)), //calculation for side
                        (int) (width/2 + side_length * Math.sin(Math.PI/6 + i * 2 * Math.PI / SIDES)));   //calculation for side
            }       
            g.drawPolygon(hex);
            g.setColor(this.getBackground());
            g.fillPolygon(hex);
            super.paintComponent(g);
        }
        @Override
        public void setBounds(int x, int y, int height, int width)
        {
            this.side_length=width/2;
            this.width=width;
            super.setBounds(x,y,width,height);
        }
    }