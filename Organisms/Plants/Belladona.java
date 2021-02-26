/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms.Plants;

import Organisms.Organism;
import Worlds.World;
import Organisms.Plant;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public class Belladona extends Plant implements Serializable{
    public Belladona(int x, int y, World wrld)
    {
        super(x,y,99,wrld);
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(102,0,102); }
    @Override
    public Organism copy() { return new Belladona(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        super.action();
    }
    @Override
    public boolean collision(Organism b)
    {
        this.setDead();
        b.setDead();
        String communication="Animal "+b.getClass().getName()+
                " Died due to eating Belladonas flowers";
        world.addCommunicate(communication);
        return false;
    }
}
