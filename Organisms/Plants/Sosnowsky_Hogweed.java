/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms.Plants;

import Organisms.Animals.Cyber_Sheep;
import Organisms.Organism;
import Worlds.World;
import Organisms.Plant;
import Worlds.Pos;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public class Sosnowsky_Hogweed extends Plant implements Serializable{
    public Sosnowsky_Hogweed(int x, int y, World wrld)
    {
        super(x,y,99,wrld);
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(255,0,0); }
    @Override
    public Organism copy() { return new Sosnowsky_Hogweed(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        Organism temp;
        Pos pos;
        for(int i=0; i<world.sideNo(); i++)
        {
            pos=world.cellAround(x, y, i);
            if(pos.getX()==-1) continue;
            temp=this.world.checkPos(pos.getX(), pos.getY());
            if(temp==null || !temp.isAnimal() || temp instanceof Cyber_Sheep)
                continue;
            temp.setDead();
            world.addCommunicate("Animal "+temp.getClass().getName()+
                        " Died due to walking too close to Sosnowsky Hogweed");
        }
    }
    @Override
    public boolean collision(Organism b)
    {
        if(b==null)
            return false;
        if(b instanceof Cyber_Sheep)
        {
            this.setDead();
            this.setPos(-44, -44);
            world.addCommunicate("Cyber Sheep eliminated Sosnowsky Hogweed!");
            this.world.removeWeed(this);
            return false;
        }
        else
        {
        b.setDead();
        world.addCommunicate("Animal "+b.getClass().getName()+" Tried to eat"
                + " Sosnowsky Hogweed and died");
        return false;
        }
    }
}
