/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Worlds.Hex;

import Organisms.Organism;
import Worlds.Pos;
import Worlds.World;
import java.awt.Color;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import static java.lang.Math.sqrt;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

/**
 *
 * @author Wojtek
 */
public class HexWorld extends World implements Serializable{
    public HexWorld(int x, int y)
    {
        super(x,y);
    }
    @Override
    public boolean isHex() { return true; }
    @Override
    public void setRand() { this.rand=(int)(Math.random() * 6); }
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
        //real values are 750 x 450
        //750=(y+1/2)*worksize*sqrt(3)/(2*y)
        //750*2y=(y+1/2)*worksize*sqrt(3)
        //1500y/sqrt(3)=(y+1/2)*worksize
        //1500y/(sqrt(3)*(y+1/2)=worksize
        //now check if real values of other side is bigger
        //1.5x*worksize/(2*x)=450
        //worksize=4/3*450 <- 600-sideLength!
        int workVar=y;
        double workSize=(1500*y)/(sqrt(3)*(y+0.5));
        int sideLength=(int)workSize/(2*workVar);
        if(sideLength*1.5*x+2*sideLength>600)
        {
            workVar=x;
            workSize=600-sideLength;
            sideLength=(int)workSize/(2*workVar);
        }
        double offsetX=sideLength*sqrt(3);
        for(int i=0; i<y; i++)
        {
            for(int j=0; j<x; j++)
            {
                HexButton button=new HexButton();
                if(j%2==1)
                    button.setBounds((int)((i+0.5)*offsetX),(int)(j*1.5*sideLength), (int)(workSize/workVar),(int)(workSize/workVar));
                else
                    button.setBounds((int)(i*offsetX),(int)(j*1.5*sideLength), (int)(workSize/workVar),(int)(workSize/workVar));
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
            case 0:
                if(y==0)//lewo
                    return new Pos(-1,-1,0);
                return new Pos(x,y-1,0);
            case 1: //gora lewa
                if(x==0 || (x%2==0 && y==0))
                    return new Pos(-1,-1,1);
                if(x%2==0)
                    return new Pos(x-1,y-1,1);
                if(x%2==1)
                    return new Pos(x-1,y,1);
            case 2: //prawo
                if(y==this.getY()-1)
                    return new Pos(-1,-1,2);
                return new Pos(x,y+1,2);
            case 3://dol lewo
                if(x==this.getX()-1 || (x%2==0 && y==0))
                    return new Pos(-1,-1,3);
                if(x%2==0)
                    return new Pos(x+1,y-1,3);
                if(x%2==1)
                    return new Pos(x+1,y,3);
            case 4://gora prawo
                if(x==0 || (x%2==1 && y==this.getY()-1))
                    return new Pos(-1,-1,4);
                if(x%2==0)
                    return new Pos(x-1,y,4);
                if(x%2==1)
                    return new Pos(x-1,y+1,4);
            case 5://dol prawo
                if(x==this.getX()-1 || (x%2==1 && y==this.getY()-1))
                    return new Pos(-1,-1,5);
                if(x%2==0)
                    return new Pos(x+1,y,5);
                if(x%2==1)
                    return new Pos(x+1,y+1,5);
            default:
                return new Pos(-1,-1,-1);
        }
    }
    @Override
    public int sideNo() { return 6; }
    @Override
    public JRadioButton[] prepButtons(JFrame f, ButtonGroup group){
        JRadioButton up = new JRadioButton("/");
        JRadioButton down = new JRadioButton("\\");
        JRadioButton left = new JRadioButton("<");
        JRadioButton right = new JRadioButton(">");
        JRadioButton stop = new JRadioButton("");
        JRadioButton upleft=new JRadioButton("\\");
        JRadioButton downleft=new JRadioButton("/");
        up.setBounds(875,500,50,50);
        down.setBounds(875,600,50,50);
        upleft.setBounds(825,500,50,50);
        downleft.setBounds(825,600,50,50);
        stop.setBounds(850,550,50,50);
        left.setBounds(785,550,50,50);
        right.setBounds(900,550,50,50); 
        downleft.setVerticalTextPosition(SwingConstants.TOP);
        downleft.setHorizontalTextPosition(SwingConstants.CENTER);
        upleft.setVerticalTextPosition(SwingConstants.TOP);
        upleft.setHorizontalTextPosition(SwingConstants.CENTER);  
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
        f.add(upleft);
        f.add(downleft);
        group.add(stop);
        group.add(up);
        group.add(left);
        group.add(down);
        group.add(right);            
        group.add(upleft);
        group.add(downleft);
        JRadioButton[] arr={up,down,left,right,upleft,downleft, stop};
        return arr;
    }
}
