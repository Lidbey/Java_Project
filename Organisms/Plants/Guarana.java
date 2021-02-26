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
public class Guarana extends Plant implements Serializable{
    public Guarana(int x, int y, World wrld)
    {
        super(x,y,0,wrld);
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(255,255,0); }
    @Override
    public Organism copy() { return new Guarana(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        super.action();
    }
    @Override
    public boolean collision(Organism b)
    {
        if(this.strength<b.getStr())
        {
            b.setStr(b.getStr()+3);
            world.addCommunicate("Animal "+b.getClass().getName()+" Ate Guarana,"
                    + "It is now much more powerful!");
        }
        return super.collision(b);
    }
}
