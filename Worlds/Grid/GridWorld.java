/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Worlds.Grid;

import Worlds.World;
import Organisms.Organism;
import Worlds.Pos;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Wojtek
 */
import javax.swing.*;

public class GridWorld extends World implements Serializable{
    public GridWorld(int x, int y)
    {
        super(x,y);
    }
    @Override
    public boolean isHex() { return false; }
    @Override
    public void setRand() { this.rand=(int)(Math.random() * 4); }
    @Override
    public void drawWorld()
    {
        this.communications.forEach((String temp) -> {
            ui.addText(temp+"\n");
        });
        this.organisms.forEach((Organism temp) -> {
            if(temp.getIni()>=0 && temp.getX()>=0 && temp.getY()>=0)
            {
                ui.colorButt(temp.getX(), temp.getY(), temp.draw());
            }
        });
    }
    @Override
    public JButton[][] prepMap(JFrame f)
    {
        JButton[][] current=new JButton[y][x];
        
        for(int i=0; i<y; i++)
        {
            for(int j=0; j<x; j++)
            {
                JButton button=new JButton();
                button.setBounds(i*(700/y), j*(450/x), 700/y, 450/x);
                button.setBackground(Color.WHITE);
                button.setForeground(Color.GRAY);
                button.addActionListener(ui);
                f.add(button);
                current[i][j]=button;
            }
        }
        return current;
    }
    @Override
    public Pos cellAround(int x, int y, int side)
    {
        if(side==-1)
        {    
            this.setRand();
            side=this.getRand();
        }
        switch (side) {
            case 0: //lewo
                if(y==0)
                    return new Pos(-1,-1,0);
                return new Pos(x,y-1,0);
            case 1: //dół
                if(x==0)
                    return new Pos(-1,-1,1);
                return new Pos(x-1,y,1);
            case 2: //prawo
                if(y==this.getY()-1)
                    return new Pos(-1,-1,2);
                return new Pos(x,y+1,2);
            case 3: //góra
                if(x==this.getX()-1)
                    return new Pos(-1,-1,3);
                return new Pos(x+1,y,3);
            default:
                return new Pos(-1,-1,-1);
        }
    }
    @Override
    public int sideNo() { return 4; }
    @Override
    public JRadioButton[] prepButtons(JFrame f, ButtonGroup group){
        JRadioButton up = new JRadioButton("^");
        JRadioButton down = new JRadioButton("v");
        JRadioButton left = new JRadioButton("<");
        JRadioButton right = new JRadioButton(">");
        JRadioButton stop = new JRadioButton("");
        up.setBounds(850,500,50,50);
        down.setBounds(850,600,50,50);
        stop.setBounds(850,550,50,50);
        left.setBounds(785,550,50,50);
        right.setBounds(900,550,50,50);
        JRadioButton upleft=null;
        JRadioButton downleft=null;
        up.setVerticalTextPosition(SwingConstants.TOP);
        up.setHorizontalTextPosition(SwingConstants.CENTER);
        down.setVerticalTextPosition(SwingConstants.BOTTOM);
        down.setHorizontalTextPosition(SwingConstants.CENTER);
        left.setHorizontalTextPosition(SwingConstants.LEFT);
        right.setHorizontalTextPosition(SwingConstants.RIGHT);
        f.add(stop);
        f.add(up);
        f.add(left);
        f.add(right);
        f.add(down);
        group.add(stop);
        group.add(up);
        group.add(left);
        group.add(down);
        group.add(right);
        JRadioButton[] arr={up,down,left,right,upleft,downleft, stop};
        return arr;
    }
}
