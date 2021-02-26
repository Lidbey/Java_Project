/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms.Animals;

import Organisms.Organism;
import Worlds.World;
import Organisms.Animal;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public class Turtle extends Animal implements Serializable{
    private boolean turtleAttack;
    public Turtle(int x, int y, World wrld)
    {
        super(x,y,2,1,wrld);
        this.turtleAttack=false;
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(128,128,0); }
    @Override
    public Organism copy() { return new Turtle(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        if(Math.random()<=0.25)
        {    
            this.turtleAttack=true;
            super.action();
            this.turtleAttack=false;
        }
    }
    @Override
    public boolean collision(Organism b)
    {
        if(this.getClass().getName().equals(b.getClass().getName()))
        {
            if(b.getAge()>10 && this.getAge()>10)
            {
            Organism abc=(Organism)this.copy();
            String communication=this.getClass().getName()+"s copulated, "
                    + "creating small "+this.getClass().getName();
            world.addCommunicate(communication);
            }
            this.turtleAttack=false;
            return false;
        }
        else if(this.strength>b.getStr())
        {
            this.turtleAttack=false;
            return !b.collision(this);
        }
        else if(this.strength<b.getStr())
        {
            if(this.turtleAttack==true || b.getStr()>=5)
            {
                this.setDead();
                String communication=this.getClass().getName() + " Got eaten by "
                        + b.getClass().getName();
                world.addCommunicate(communication);
                this.turtleAttack=false;
                return false;
            }
            else
            {
                String communication=this.getClass().getName() + " Stopped attack of "
                        + b.getClass().getName();
                world.addCommunicate(communication);
                this.turtleAttack=false;
                return true;
            }
        }
        this.turtleAttack=false;
        return false;
    }
}
