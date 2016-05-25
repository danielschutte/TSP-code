package tsp;

import java.util.*;

public class RouteCheck {

    private int timeToDo;
    public ArrayList<StorageSpace> route = new ArrayList<>();

    //constructor for volledige enumeratie
    public RouteCheck(StorageSpace sp, int ttd)
    {
        this.timeToDo = ttd;
        this.route.add(sp);
    }

    //constructor for simpel gretig variation
    public RouteCheck()
    {
    }

    //functions
    public int getTimeToDo()
    {
        return this.timeToDo;
    }

    //add route
    public void addSpace(StorageSpace sp)
    {
        this.route.add(sp);
    }
    
    public void setTimeToDo(int ttd){
        this.timeToDo = ttd;
    }

    public String toString()
    {
        System.out.println("time: " + timeToDo + ", spaces: " + route);
        return "" + route;
    }
}
