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
public class Sheep extends Animal implements Serializable{
    public Sheep(int x, int y, World wrld)
    {
        super(x,y,4,4,wrld);
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(255,204,153); }
    @Override
    public Organism copy() { return new Sheep(this.getX(), this.getY(), this.world); }
    @Override
    public void action()
    {
        super.action();
    }
    @Override
    public boolean collision(Organism b)
    {
        return super.collision(b);
    }
}
