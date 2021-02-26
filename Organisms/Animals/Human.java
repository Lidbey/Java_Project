/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms.Animals;

import Organisms.Animal;
import Organisms.Organism;
import Worlds.Pos;
import Worlds.World;
import java.awt.Color;
import java.io.Serializable;


/**
 *
 * @author Wojtek
 */
public class Human extends Animal implements Serializable{
    private int side, remainingtours;
    private boolean powerused;
    public Human(int x, int y, World wrld)
    {
        super(x,y,5,4,wrld);
        this.side=6;
        this.powerused=false;
        this.remainingtours=-1;
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(0,0,0); }
    @Override
    public Organism copy() { return new Human(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        if (this.initiative >= 0)
	{
            if(remainingtours==5)
		world.addCommunicate("Human used his MAGICAL POTION!!!");
            Pos pos=world.cellAround(x, y, side);
            if(pos.getX()!=-1)
            {
		Organism temp = this.world.checkPos(pos.getX(), pos.getY());
		if (temp == null || this.collision(temp))
                    this.setPos(pos.getX(), pos.getY());
            }
            if (remainingtours > 0)
            {
                    this.strength--;
                    this.remainingtours--;
            }
            else if (remainingtours > -5)
                    this.remainingtours--;
            else if (remainingtours == -5)
                    this.powerused = false;
        }
    }
    @Override
    public boolean collision(Organism b)
    {
        return super.collision(b);
    }
    public void usePower()
    {
        if(this.powerused==false)
        {
            this.powerused=true;
            this.remainingtours=5;
            this.strength+=5;
        }
    }
    public void setSide(int side)
    {
        if(world.isHex())
        {
            this.side=side;
            switch (side) {
                case 4:
                    this.side=1;
                    break;
                case 5:
                    this.side=3;
                    break;
                case 3:
                    this.side=5;
                    break;
                case 1:
                    this.side=4;
                    break;
                default:
                    break;
            }
        }
        else
            this.side=side;
    }
    @Override
    public void setDead()
    {
        super.setDead();
        this.powerused=true;
        this.remainingtours=-1;
        this.side=4;
    }
}
