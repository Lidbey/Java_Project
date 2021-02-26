/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms.Animals;
import Organisms.Animal;
import Worlds.World;
import Organisms.Organism;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public class Wolf extends Animal implements Serializable{
    public Wolf(int x, int y, World wrld)
    {
        super(x,y,9,5,wrld);
        wrld.addOrganism(this);
    }
    @Override
    public Color draw() { return new Color(192,192,192); }
    @Override
    public Organism copy() { return new Wolf(this.getX(), this.getY(), this.world); }
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
