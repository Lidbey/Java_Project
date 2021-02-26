/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms.Plants;

import Organisms.Organism;
import Organisms.Plant;
import Worlds.World;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public class Sow_Thistle extends Plant implements Serializable{
    public Sow_Thistle(int x, int y, World wrld)
    {
        super(x,y,0,wrld);
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(204,255,255); }
    @Override
    public Organism copy() { return new Sow_Thistle(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        super.action();
        super.action();
        super.action();
    }
    @Override
    public boolean collision(Organism b)
    {
        return super.collision(b);
    }
}
