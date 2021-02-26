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
public abstract class Animal extends Organism implements Serializable{
    public Animal(int x, int y, int strength, int ini, World wrld)
    {
        super(x,y,strength,ini,wrld);
    }
    @Override
    public abstract Organism copy();
    @Override
    public abstract Color draw();
    @Override
    public boolean isAnimal() { return true; }
    @Override
    public void action() {
        if(this.initiative>=0)
        {
            Pos position=world.cellAround(x,y,-1);
            while(position.getX()==-1)
                position=world.cellAround(x,y,-1);
            Organism temp=this.world.checkPos(position.getX(), position.getY());
            if(temp==null || this.collision(temp))
            {
                this.setPos(position.getX(), position.getY());
            }
        }
    }
    @Override
    public boolean collision(Organism b)
    {
        if(this.getClass().getName().equals(b.getClass().getName()))
        {
            if(b.getAge()>10 && this.getAge()>10)
            {
                for(int i=0; i<world.sideNo();i++)
                {
                    Pos pos=world.cellAround(x, y, i);
                    if(pos.getX()!=-1 && world.checkPos(pos.getX(), pos.getY())==null)
                    {
                    Organism abc=this.copy();
                    String communication=this.getClass().getName()+"s copulated, "
                        + "creating small "+this.getClass().getName();
                    world.addCommunicate(communication);
                    return false;
                    }
                }
            }
            return false;
        }
        else if(this.strength>b.getStr())
        {
            return !b.collision(this);
        }
        else if(this.strength<b.getStr())
        {
            this.setDead();
            String communication=this.getClass().getName() + " Got eaten by "
                    + b.getClass().getName();
            world.addCommunicate(communication);
            return false;
        }
        return false;
    }
}