/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms;
import Worlds.World;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
public abstract class Organism implements Serializable{
    protected World world;
    protected int x,y,age,id,strength,initiative;
    public Organism(int x, int y, int strength, int ini, World world) {
        this.world=world;
        this.x=x;
        this.y=y;
        this.age=0;
        this.id=0;
        this.strength=strength;
        this.initiative=ini;
    }
    public abstract boolean isAnimal();
    public abstract Color draw();
    public abstract void action();
    public abstract boolean collision(Organism org);
    public abstract Organism copy();
    
    public int getStr() { return this.strength;}
    public void setStr(int str) { this.strength=str; }
    public int getId() { return this.id; }
    public void setId(int id) { this.id=id; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public void setPos(int x, int y) { this.x=x; this.y=y; }
    public int getIni() { return this.initiative; }
    public void setIni(int ini) { this.initiative=ini; }
    public void setDead() { this.initiative=-1; this.x=-10; this.y=-10; world.delete(this); }
    public World getWorld() { return this.world; }
    public int getAge() { return this.age; }
    public void setAge(int age) { this.age=age; }
    public void advanceAge() { this.age++; }
}
