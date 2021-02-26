/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Worlds;

import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public class Pos implements Serializable{
    private final int x,y,side;
    public Pos(int x, int y, int side)
    {
        this.x=x;
        this.y=y;
        this.side=side;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getSide() { return side; }
}
