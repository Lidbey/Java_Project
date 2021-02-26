/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Worlds.World;
import Organisms.Plants.*;
import Organisms.Animals.*;
import Organisms.Organism;

/**
 *
 * @author Wojtek
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserInterface implements ActionListener, Serializable{
    private JFrame f;
    private JButton r,n,l,s,q;
    private JRadioButton up,down,left,right,stop, upleft, downleft;
    private JTextArea logs;
    private JScrollPane scroll;
    private JButton[][] map;
    private JPopupMenu popupmenu;
    private ButtonGroup group;
    private JFrame tut;
    private int sx,sy;
    private World world;
    public UserInterface(World world)
    {
        this.world=world;
        world.setUI(this);
        f=new JFrame();
        popupmenu=new JPopupMenu("Edit");
        tut=new JFrame();
        r=new JButton("Special Ability");
        n=new JButton("Next Turn");
        l=new JButton("Load Game");
        s=new JButton("Save Game");
        q=new JButton("Quit Game");
        logs=new JTextArea();
        scroll = new JScrollPane(logs);
        group=new ButtonGroup();
        this.prepGui();
    }
    
    public void setRadio(int direction)
    {
        this.group.clearSelection();
        switch (direction) {
            case 3:
                down.setSelected(true);
                break;
            case 1:
                up.setSelected(true);
                break;
            case 2:
                right.setSelected(true);
                break;
            case 0:
                left.setSelected(true);
                break;
            case 4:
                if(upleft!=null)
                    upleft.setSelected(true);
                break;
            case 5:
                if(downleft!=null)
                    downleft.setSelected(true);
                break;
            default:
                break;
        }
    }
    
    private void saveGame()
    {
        try {
            File myobj=new File("./World2.ser");
            myobj.createNewFile();
            FileOutputStream fileout=new FileOutputStream("./World2.ser");
            ObjectOutputStream out=new ObjectOutputStream(fileout);
            out.writeObject(this.world);
            out.close();
            fileout.close();
        }
        catch(IOException i){}
    
    }
    
    private void loadGame()
    {
        World newWorld;
        try {
            FileInputStream fileIn = new FileInputStream("./World2.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newWorld = (World)in.readObject();
            in.close();
            fileIn.close();
        } 
        catch (IOException i) {return;}
        catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            return;
        }         
        /*f.dispose();
        tut.dispose();
        new UserInterface(newWorld);
        newWorld.drawWorld();*/
        this.world=newWorld;
        this.loadWorld();
    }
    
    private class MoveAction extends AbstractAction
    {
        int direction;
        MoveAction(int direction)
        {
            this.direction=direction;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(world.man()!=null)
            {
                world.man().setSide(direction);
                setRadio(direction);
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==r)
        {   
            if(world.man()!=null && world.man().getIni()!=-1)
                world.man().usePower();
            else
                world.addCommunicate("You doesn't exist in this world any more!");
        }
        else if(e.getSource()==n)
        {
            world.getOrganisms().forEach((Organism temp) -> {
                if(temp.getIni()>=0)
                {
                    map[temp.getY()][temp.getX()].setBackground(Color.WHITE);
                }
            });
            logs.setText("");
            world.makeTurn();
            world.drawWorld();
        }
        else if(e.getSource()==l)
            this.loadGame();
        else if(e.getSource()==s)
            this.saveGame();
        else if(e.getSource()==q)
            System.exit(0);
        else if(e.getSource()==up)
        {
            if(world.man()==null)
                return;
            world.man().setSide(1);
        }
        else if(e.getSource()==left)
        {
            if(world.man()==null)
                return;
            world.man().setSide(0);
        }
        else if(e.getSource()==right)
        {
            if(world.man()==null)
                return;
            world.man().setSide(2);
        }
        else if(e.getSource()==down)
        {
            if(world.man()==null)
                return;
            world.man().setSide(3);
        }
        else if(e.getSource()==upleft)
        {
            if(world.man()==null)
                return;
            world.man().setSide(4);
        }   
        else if(e.getSource()==downleft)
        {
            if(world.man()==null)
                return;
            world.man().setSide(5);
        }
        else if(e.getSource()==stop)
        {
            if(world.man()==null)
                return;
            world.man().setSide(6);
        }
        //Warnings are here but they are OK, as giving world to constructor
        //already assign this animal to the world
        else if("Antelope".equals(e.getActionCommand()))
            new Antelope(sx,sy, world);
        else if("Fox".equals(e.getActionCommand()))
            new Fox(sx,sy, world);
        else if("Human".equals(e.getActionCommand()))
            new Human(sx,sy, world);
        else if("Sheep".equals(e.getActionCommand()))
            new Sheep(sx,sy, world);
        else if("Turtle".equals(e.getActionCommand()))
            new Turtle(sx,sy, world);
        else if("Wolf".equals(e.getActionCommand()))
            new Wolf(sx,sy, world);
        else if("Belladona".equals(e.getActionCommand()))
            new Belladona(sx,sy, world);
        else if("Grass".equals(e.getActionCommand()))
            new Grass(sx,sy, world);
        else if("Guarana".equals(e.getActionCommand()))
            new Guarana(sx,sy, world);
        else if("Sosnowsky_Hogweed".equals(e.getActionCommand()))
            new Sosnowsky_Hogweed(sx,sy, world);
        else if("Sow_Thistle".equals(e.getActionCommand()))
            new Sow_Thistle(sx,sy, world);
        else if("Cyber_Sheep".equals(e.getActionCommand()))
            new Cyber_Sheep(sx,sy, world);
        else
        {
            for(int i=0; i<world.getY(); i++)
            {
                for(int j=0; j<world.getX(); j++)
                {
                    if(e.getSource()==map[i][j])
                    {
                        if(map[i][j].getBackground()!=Color.WHITE)
                            return;
                        popupmenu.show(f,map[i][j].getX(), map[i][j].getY());
                        sy=i;
                        sx=j;
                        System.out.println("X is "+Integer.toString(j)+
                                " Y is "+Integer.toString(i));
                    }
                }
            }
        }
    }
    public void colorButt(int x, int y, Color color)
    {
        map[y][x].setBackground(color);
    }
    public void addText(String temp)
    {
        temp=temp.replace("Organisms.Animals.", "");
        temp=temp.replace("Organisms.Plants.", "");
        logs.append(temp);
    }
    private void prepPopupMenu()
    {
        JMenuItem Antelopem=new JMenuItem("Antelope");
        JMenuItem Foxm=new JMenuItem("Fox");
        JMenuItem Humanm=new JMenuItem("Human");
        JMenuItem Sheepm=new JMenuItem("Sheep");
        JMenuItem Turtlem=new JMenuItem("Turtle");
        JMenuItem Wolfm=new JMenuItem("Wolf");
        JMenuItem Belladonam=new JMenuItem("Belladona");
        JMenuItem Grassm=new JMenuItem("Grass");
        JMenuItem Guaranam=new JMenuItem("Guarana");
        JMenuItem Sosnowsky_Hogweedm=new JMenuItem("Sosnowsky_Hogweed");
        JMenuItem Sow_Thistlem=new JMenuItem("Sow_Thistle");
        JMenuItem Cyber_Sheepm=new JMenuItem("Cyber_Sheep");
        
        Antelopem.addActionListener(this);
        Foxm.addActionListener(this);
        Humanm.addActionListener(this);
        Sheepm.addActionListener(this);
        Turtlem.addActionListener(this);
        Wolfm.addActionListener(this);
        Belladonam.addActionListener(this);
        Grassm.addActionListener(this);
        Guaranam.addActionListener(this);
        Sosnowsky_Hogweedm.addActionListener(this);
        Sow_Thistlem.addActionListener(this);
        Cyber_Sheepm.addActionListener(this);
        
        popupmenu.add(Foxm);popupmenu.add(Antelopem);popupmenu.add(Humanm);
        popupmenu.add(Sheepm);popupmenu.add(Turtlem);popupmenu.add(Wolfm);
        popupmenu.add(Belladonam);popupmenu.add(Grassm);popupmenu.add(Guaranam);
        popupmenu.add(Sosnowsky_Hogweedm);popupmenu.add(Sow_Thistlem);
        popupmenu.add(Cyber_Sheepm);
    }
    private void prepTut()
    {
        tut.setBounds(1000,0,100,730);
        tut.setLayout(new GridLayout(12,1));
        JButton Antelope=new JButton("Antelope"); 
        Antelope.setBackground(new Color(255,0,255)); tut.add(Antelope);
        JButton Fox=new JButton("Fox"); 
        Fox.setBackground(new Color(255,153,51)); tut.add(Fox);
        JButton Human=new JButton("Human"); 
        Human.setBackground(new Color(0,0,0)); tut.add(Human);
        JButton Sheep=new JButton("Sheep"); 
        Sheep.setBackground(new Color(255,204,153)); tut.add(Sheep);
        JButton Turtle=new JButton("Turtle"); 
        Turtle.setBackground(new Color(128,128,0)); tut.add(Turtle);
        JButton Wolf=new JButton("Wolf"); 
        Wolf.setBackground(new Color(192,192,192)); tut.add(Wolf);
        JButton Belladona=new JButton("Belladona"); 
        Belladona.setBackground(new Color(102,0,102)); tut.add(Belladona);
        JButton Grass=new JButton("Grass"); 
        Grass.setBackground(new Color(0,255,0)); tut.add(Grass);
        JButton Guarana=new JButton("Guarana"); 
        Guarana.setBackground(new Color(255,255,0)); tut.add(Guarana);
        JButton Sosnowsky_Hogweed=new JButton("Sosnowsky_Hogweed"); 
        Sosnowsky_Hogweed.setBackground(new Color(255,0,0)); tut.add(Sosnowsky_Hogweed);
        JButton Sow_Thistle=new JButton("Sow_Thistle"); 
        Sow_Thistle.setBackground(new Color(204,255,255)); tut.add(Sow_Thistle);
        JButton Cyber_Sheep=new JButton("Cyber_Sheep"); 
        Cyber_Sheep.setBackground(new Color(150,75,0)); tut.add(Cyber_Sheep);
        tut.setVisible(true);
    }
    
    private void prepButtons()
    {   
        r.addActionListener(this);
        n.addActionListener(this);
        l.addActionListener(this);
        s.addActionListener(this);
        q.addActionListener(this);
        
        r.setBounds(775,10,200,75);
        n.setBounds(775,100,200,75);
        l.setBounds(775,190,200,75);
        s.setBounds(775,280,200,75);
        q.setBounds(775,370,200,75);
        
        f.add(r);
        f.add(n);
        f.add(l);
        f.add(s);
        f.add(q);
    }
    
    private void prepListeners()
    {
        JRadioButton[] arr=world.prepButtons(f, group);
        up=arr[0];
        down=arr[1];
        left=arr[2];
        right=arr[3];
        upleft=arr[4];
        downleft=arr[5];
        stop=arr[6];
        
        stop.addActionListener(this);
        up.addActionListener(this);
        left.addActionListener(this);
        down.addActionListener(this);
        right.addActionListener(this);
        stop.setSelected(true);
        
        if(world.isHex())
        {
            upleft.addActionListener(this);
            downleft.addActionListener(this);
        }
        
    }
    
    private void prepHotkeys()
    {
        JLabel g=new JLabel();
        g.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"),"up");
        g.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"),"down");
        g.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"),"left");
        g.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"),"right");
        g.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Q"),"upleft");
        g.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Z"),"downleft");
        g.getActionMap().put("up", new MoveAction(1));
        g.getActionMap().put("down", new MoveAction(3));
        g.getActionMap().put("left", new MoveAction(0));
        g.getActionMap().put("right", new MoveAction(2));
        g.getActionMap().put("upleft", new MoveAction(4));
        g.getActionMap().put("downleft", new MoveAction(5));
        f.add(g);
    }       
    
    private void prepGui()
    {        
        map=world.prepMap(f);
        f.setBounds(0,0,1000,730);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.prepPopupMenu();
        this.prepTut();
        this.prepButtons();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scroll.setBounds(0,475,700,200);
        logs.setEditable(false);
        f.getContentPane().add(scroll); 
        this.prepListeners();
        this.prepHotkeys();
        f.setLayout(null);
        f.setVisible(true);
    }
    private void loadWorld()
    {
        f.getContentPane().removeAll();
        f.getContentPane().repaint();
        tut.getContentPane().removeAll();
        tut.getContentPane().repaint();
        popupmenu.removeAll();
        r.removeAll();
        n.removeAll();
        l.removeAll();
        s.removeAll();
        q.removeAll();
        logs.removeAll();
        scroll.removeAll();
        group.clearSelection();
        f.dispose();
        tut.dispose();
        world.setUI(this);
        f=new JFrame();
        popupmenu=new JPopupMenu("Edit");
        tut=new JFrame();
        r=new JButton("Special Ability");
        n=new JButton("Next Turn");
        l=new JButton("Load Game");
        s=new JButton("Save Game");
        q=new JButton("Quit Game");
        logs=new JTextArea();
        scroll = new JScrollPane(logs);
        group=new ButtonGroup();
        this.prepGui();
    }
     
    
}
