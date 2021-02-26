/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms;

import Worlds.Pos;
import Worlds.World;
import java.awt.Color;
import java.io.Serializable;
/**
 *
 * @author Wojtek
 */
public abstract class Plant extends Organism implements Serializable{
    public Plant(int x, int y, int strength, World wrld)
    {
        super(x,y,strength,0,wrld);
    }
    @Override
    public abstract Color draw();
    @Override
    public abstract Organism copy();
    @Override
    public boolean isAnimal() { return false; }
    @Override
    public void action()
    {
        if(Math.random()<=0.05 && this.initiative>=0)
        {
            Pos position=world.cellAround(x, y, -1);
            while(position.getX()==-1)
                position=world.cellAround(x,y,-1);
            if(world.checkPos(position.getX(),position.getY())==null)
            {
                Organism org=this.copy();
                org.setPos(position.getX(), position.getY());
            }
        }
    }
    @Override
    public boolean collision(Organism b)
    {
        if(this.strength>b.getStr())
        {
            return !b.collision(this);
        }
        else if (this.strength<b.getStr())
        {
            this.setDead();
            return false;
        }
        return false;
    }
}
