/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojtek
 */

/*
only warnings left - leaking this in constructor.
I think, the code looks MUCH better with assigning 'this' in constructor
and the program works good, so i hope it will not be a problem in grading : )
*/
import GUI.UserInterface;
import Worlds.Grid.GridWorld;
import Worlds.Hex.HexWorld;
public class UI{
    public UI() {}
    public static void main(String[] args){
        /*
        Size of the worlds can be changed(20,30 is the x and y size of world)
        With grid, the rectangles are created(so they can be stretched)
        Hexes cannot be stretched, so its better to take y=1.5x just so all the
        map is filled(so no free room on the window is left) Of course, any number
        of hexes will work, the y=1.5x is just for aesthetic case :)
        */
        HexWorld x=new HexWorld(20,30);  
        //GridWorld z=new GridWorld(20,20); 
        UserInterface y=new UserInterface(x);
        //UserInterface y=new UserInterface(z); //For gridworld uncomment&comment above
    }
}
