/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Worlds;
import Organisms.Organism;
import Organisms.Animals.Human;
import java.util.*;
import Organisms.Plants.Sosnowsky_Hogweed;
import java.io.*;
import GUI.UserInterface;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 *
 * @author Wojtek
 */
public abstract class World implements Serializable{
    protected UserInterface ui;
    private int maxId;
    protected int rand;
    protected final int x,y;
    protected final LinkedList<Organism> organisms;
    protected final LinkedList<String> communications;
    protected final LinkedList<Organism> deletequeue;
    private final ArrayList<Sosnowsky_Hogweed> weeds;
    private Human human;
    public World()
    {
        this.organisms=new LinkedList<>();
        this.communications=new LinkedList<>();
        this.weeds = new ArrayList<>();
        this.deletequeue=new LinkedList<>();
        this.human=null;
        this.x=20;
        this.y=20;
    }
    public World(int x, int y)
    {
        this.organisms=new LinkedList<>();
        this.communications=new LinkedList<>();
        this.weeds = new ArrayList<>();
        this.deletequeue=new LinkedList<>();
        this.human=null;
        this.x=x;
        this.y=y;
    }
    public void makeTurn()
    {
        this.communications.clear();
        LinkedList<Organism> organismstemp;
        organismstemp = (LinkedList)this.organisms.clone();
        organismstemp.forEach((Organism temp) -> {
            if(temp.getIni()>=0)
            {
            temp.action();
            temp.advanceAge();
            }
        });
        deletequeue.forEach((temp) -> {
            organisms.remove(temp);
        });
    }
    public abstract void drawWorld();
    public abstract JButton[][] prepMap(JFrame f);
    public int getX(){ return this.x; }
    public int getY(){ return this.y; }
    public int getmaxId() { return this.maxId; }
    public void addOrganism(Organism org)
    {
        if(org==null)
            return;
        if(org instanceof Human)
            this.setHuman((Human)org);
        else if(org instanceof Sosnowsky_Hogweed)
            this.weeds.add((Sosnowsky_Hogweed)org);
        org.setId(this.maxId);
        this.maxId++;
        ui.colorButt(org.getX(), org.getY(), org.draw());
        for (int i = 0; i < organisms.size(); i++) 
        {
            if(organisms.get(i).getIni() < org.getIni())
            {
                organisms.add(i, org);
                return;
            }
        }
        this.organisms.add(org);
    }
    public void addCommunicate(String com)
    {
        this.communications.add(com);
    }
    public Organism checkPos(int x, int y)
    {
        for(Organism temp:this.organisms)
        {
            if(temp.getX()==x && temp.getY()==y)
            {
                return temp;
            }
        }
        return null;
    }
    public void setHuman(Human human)
    {
     this.human=human;
    }
    public Human man()
    {
        return this.human;
    }

    public ArrayList<Sosnowsky_Hogweed> getWeed()
    {
        return this.weeds;
    }
    
    public void removeWeed(Sosnowsky_Hogweed temp)
    {
        this.weeds.remove(temp);
    }
    public LinkedList<Organism> getOrganisms()
    {
        return this.organisms;
    }
    public void setUI(UserInterface ui)
    {
        this.ui=ui;
    }
    public void delete(Organism o)
    {
        this.deletequeue.add(o);
    }
    //implementation of different world types
    public abstract void setRand();
    public int getRand() { return this.rand; }
    public abstract boolean isHex();
    public abstract Pos cellAround(int x, int y, int side);
    public abstract int sideNo();
    public abstract JRadioButton[] prepButtons(JFrame f, ButtonGroup group);
}
