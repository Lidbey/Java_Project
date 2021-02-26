/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms.Animals;

import Organisms.Organism;
import Worlds.World;
import java.awt.Color;
import Organisms.Animal;
import Organisms.Plants.Sosnowsky_Hogweed;
import Worlds.Pos;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public class Cyber_Sheep extends Animal implements Serializable{
    public Cyber_Sheep(int x, int y, World wrld)
    {
        super(x,y,11,4,wrld);
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(150,75,0); }
    @Override
    public Organism copy() { return new Cyber_Sheep(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        if(this.initiative>=0)
        {
            if(!world.getWeed().isEmpty())
            {
                int xt=-1;
                int yt=-1;
                int diff=9999999;
                for(Sosnowsky_Hogweed temp:world.getWeed())
                {
                    if(diff>Math.abs(x-temp.getX())+Math.abs(y-temp.getY()))
                    {
                        if(!world.isHex())
                            diff=Math.abs(x-temp.getX())+Math.abs(y-temp.getY());
                        else
                            diff=Math.abs(x-temp.getX())+Math.abs(y-temp.getY())+
                                    Math.abs(x+y-temp.getX()-temp.getY());
                        xt=temp.getX();
                        yt=temp.getY();
                    }
                }
                int side=this.checkSide(x, y, xt, yt);
                Pos position=world.cellAround(x,y,side);
                Organism temp=this.world.checkPos(position.getX(), position.getY());
                if(temp==null || this.collision(temp))
                {
                    this.setPos(position.getX(), position.getY());
                }
            }
            else
                super.action();
        }
    }
    @Override
    public boolean collision(Organism b)
    {
        if(b instanceof Sosnowsky_Hogweed)
            return !b.collision(this);
        return super.collision(b);
    }
    private int checkSide(int x, int y, int xd, int yd)
    {
        if(x==xd)
        {
            if(y<yd)
                return 2;
            if(y>yd)
                return 0;
            else
                return -1;
        }
        else if(x<xd) //musi iść w górę
        {
            if(!world.isHex())
                return 3;
            else
            {
                if(y>yd) //musi iść w lewo
                    return 3;
                else
                    return 5;
            }
        }
        else //musi isć w dół
        {    
            if(!world.isHex())
                return 1;
            else
            {
                if(y>=yd)
                    return 1;
                else
                    return 4;
            }
        }
    }
}