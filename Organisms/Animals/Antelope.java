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
public class Antelope extends Animal implements Serializable{
    private boolean attack;
    public Antelope(int x, int y, World wrld)
    {
        super(x,y,4,4,wrld);
        this.attack=false;
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(255,0,255); }
    @Override
    public Organism copy() { return new Antelope(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        if(this.initiative>=0)
        {
            Pos position;
            while(true)
            {
            Pos position2=world.cellAround(x, y, -1);
            while(position2.getX()==-1)
                position2=world.cellAround(x, y, -1);
            position=world.cellAround(position2.getX(), position2.getY(), position2.getSide());
            if(position.getX()!=-1)
                break;
            }
            Organism temp=this.world.checkPos(position.getX(), position.getY());
            if(temp==null || this.collision(temp))
            {
                this.attack=true;
                this.setPos(position.getX(), position.getY());
                if(this.attack==false)
                    super.action();
                this.attack=false;
            }
        }
    }
    @Override
    public boolean collision(Organism b)
    {
        if(Math.random()<=0.5 && !(b instanceof Organisms.Animals.Antelope) 
                && !(b instanceof Organisms.Animal) && b!=null)
        {
        String communication=this.getClass().getName()+" Managed to escape"
                + " from "+b.getClass().getName();
        world.addCommunicate(communication);
        if(this.attack==true)
            this.attack=false;
        else
            super.action();
        return false;
        }
        else if (b instanceof Organisms.Animals.Antelope)
        {
            Pos pos;
            for(int i=0; i<world.sideNo(); i++)
            {
                pos=world.cellAround(x, y, i);
                pos=world.cellAround(pos.getX(), pos.getY(),i);
                if(pos.getX()!=-1 && world.checkPos(pos.getX(), pos.getY())==null)
                    return super.collision(b);
            }
            return false;
        }
        else
            return super.collision(b);
    }
}
