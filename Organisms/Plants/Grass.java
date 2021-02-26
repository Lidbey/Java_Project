/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms.Plants;

import Worlds.World;
import Organisms.Plant;
import Organisms.Organism;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public class Grass extends Plant implements Serializable{
    public Grass(int x, int y, World wrld)
    {
        super(x,y,0,wrld);
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(0,255,0); }
    @Override
    public Organism copy() { return new Grass(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        super.action();
    }
    @Override
    public boolean collision(Organism b)
    {
        return super.collision(b);
    }
}
