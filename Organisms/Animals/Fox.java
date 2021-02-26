/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms.Animals;

import Organisms.Organism;
import Worlds.World;
import Organisms.Animal;
import Worlds.Pos;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public class Fox extends Animal implements Serializable{
    public Fox(int x, int y, World wrld)
    {
        super(x,y,3,7,wrld);
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(255,153,51); }
    @Override
    public Organism copy() { return new Fox(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        int it=0;
        if(this.initiative>=0)
        {
            while(it++<100)
            {
                Pos position=world.cellAround(x,y,-1);
                while(position.getX()==-1)
                    position=world.cellAround(x,y,-1);
                Organism temp=this.world.checkPos(position.getX(), position.getY());
                if(temp==null || temp.getStr()<=this.strength && this.collision(temp))
                {
                    this.setPos(position.getX(), position.getY());
                    return;
                }
                if(temp.getStr()<=this.strength)
                    return;
            }
        }
    }
    @Override
    public boolean collision(Organism b)
    {
        return super.collision(b);
    }
}
