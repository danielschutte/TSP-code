package tsp;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Storage {

    //declare vars
    //selected algoritm
    //Object[] selectObj = Frame.menu.getSelectedObjects();
    //arraylist for places
    protected ArrayList<StorageSpace> sp = new ArrayList(0);

    //arraylist for routes
    ArrayList<RouteCheck> posRoute = new ArrayList(0);

    //times robot needs to move between a row in seconds
    private int timeOneY = 1;//time height
    private int timeOneX = 1;//time width

    //size storage
    private int x;
    private int y;

    //chocing algoritm
    String algoA = "volledige enumeratie";
    String algoB = "aangepast simpel gretig";

    //end declare vars
    //functions
    //constructor
    public Storage(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    //removing everything from list
    public void clearList()
    {
        sp.clear();
    }

    //adding an place to check to the list
    public void addToList(StorageSpace x)
    {
        sp.add(x);
    }

    //calculates fastest
    public RouteCheck calcFastest()
    {//returns a RouteCheck class.

        //creating finalInfo var
        RouteCheck finalInfo = null;

        //chooses what algoritm to follow
        if (sp.size() <= 8)//if (algoA == selectObj)
        {
            //Volledige enumeratie
            //calculate
            int count1 = 0;
            while (count1 < sp.size())
            {//each order
                int xStart = 1;
                int yStart = 1;
                int thisPlaceX = 1;
                int thisPlaceY = 1;
                int timeTaken = 0;
                int timeTotalTaken = 0;
                ArrayList<Integer> allTimes = new ArrayList<>();

                for (StorageSpace v : sp)
                //each item in order
                {//checking the route for this order
                    //set thisPlace values
                    //check time and set in timeTaken var
                        thisPlaceX = v.getX();
                        thisPlaceY = v.getY();
                    
                    if (thisPlaceY > yStart)
                    {
                        timeTaken = ((thisPlaceY - yStart) * timeOneY);
                        if (thisPlaceX > xStart)
                        {
                            timeTaken += ((thisPlaceX - xStart) * timeOneX);
                        } else if (thisPlaceX < xStart)
                        {
                            timeTaken += ((xStart - thisPlaceX) * timeOneX);
                        }
                    } else if (thisPlaceY < yStart)
                    {
                        timeTaken = ((yStart - thisPlaceY) * timeOneY);
                        if (thisPlaceX > xStart)
                        {
                            timeTaken += ((thisPlaceX - xStart) * timeOneX);
                        } else if (thisPlaceX < xStart)
                        {
                            timeTaken += ((xStart - thisPlaceX) * timeOneX);
                        }
                    }

                    //set start values = thisPlace values
                    xStart = thisPlaceX;
                    yStart = thisPlaceY;
                    
                    //saving the time from this item to an ArrayList
                    allTimes = new ArrayList<>();
                    allTimes.add(Integer.valueOf(timeTaken));
                    //end code for each item in one route
                }
                
                //back to code for one entire route
                //adding all times from seperate items to one var
                for (Integer i : allTimes){
                 timeTotalTaken += i;   
                }
                
                //add vars (v, timeTaken) to a new RouteCheck object
                    RouteCheck toAdd = new RouteCheck();
                    toAdd.setTimeToDo(timeTotalTaken);
                    for(StorageSpace i : sp){
                    toAdd.addSpace(i);
                    }
                    posRoute.add(toAdd);

                //place last in front of array
                //sla alles in ArrayList sp op in vars
                ArrayList<StorageSpace> limitedTime = new ArrayList<>();
                for (StorageSpace v : sp)
                {
                    limitedTime.add(v);
                }
                
                //gooi alles weer terug in de arraylist op andere volgorde
                //set highest index this items have
                int maxIndex = limitedTime.size() - 1;
                int indexNum = 0;
                boolean firstTime2 = true;
                for (StorageSpace v : limitedTime)
                {
                    //add first item as last
                    if (firstTime2 == true)
                    {
                        sp.set(maxIndex, v);
                        //setting the other items one in front;
                        //making sure it only happens with the first item
                        firstTime2 = false;
                    } else
                    {
                        //setting all other items one early
                        sp.set(indexNum, v);
                        indexNum++;
                    }
                }

                //clearing the limitedTime ArrayList;
                limitedTime.clear();
                
                //done creating all posible routeCheck objects
                count1++;
               }
            

            //select RouteCheck with shortest time and add it to finalInfo
            int indexNumFastestRoute = 0;
            int timeFastestRoute = 0;
            int index = 0;
            boolean firstTimeCheck = true;
            for (RouteCheck v : posRoute)
            {
                if (firstTimeCheck == true)
                {
                    //getting first speed
                    timeFastestRoute = v.getTimeToDo();
                    indexNumFastestRoute = index;

                    //making sure it only happends the first time
                    firstTimeCheck = false;
                } else
                {
                    // adding to index so index is equal to the index of the index of the item it's looping trough
                    index++;

                    //determens if the next route is faster then the other ones that have passed
                    if (v.getTimeToDo() < timeFastestRoute)
                    {
                        //if it's shorter it saves the index as the index from the items with the shortest time.
                        indexNumFastestRoute = index;
                    }
                }
            }
            //return FinalInfo
            finalInfo = (posRoute.get(indexNumFastestRoute));

        } else if (sp.size() < 20)// if (algoB == selectObj)
        {
            //aangepast Simpel Gretig Algoritme
            //setting vars
            //ArrayList for saving the right route
            ArrayList<StorageSpace> limitedTime = new ArrayList<>();
            ArrayList<StorageSpace> safeCopy = sp;
            //setting a startpostion
            int xStart = 0;
            int yStart = 0;

            //calculate
            //first do the first half of the storage
            int count1 = 0;
            int safeCopySize = safeCopy.size();
                //check which one is the most close
                for (int item = 0; item < safeCopySize; item++)
                {
                    //makes sure only first half is checked
                    if (safeCopy.get(item).getX() <= (this.x / 2))
                    {
                        //algoritm here
                        //check closest item
                        int differenceX;
                        int differenceY;
                        int timeTaken;
                        int shortIndex = 0;
                        int shortestTimeTaken = 0;
                        int indexNum = 0;
                        
                        for (StorageSpace i : safeCopy)
                        {
                            //calculate difference X
                            differenceX = xStart - i.getX();
                            differenceX = Math.abs(differenceX);

                            //calculate difference Y
                            differenceY = yStart - i.getY();
                            differenceY = Math.abs(differenceY);

                            //calculating time taken
                            timeTaken = differenceX + differenceY;

                            //checking if it's the shortest
                            if (shortestTimeTaken == 0)
                            {
                                shortestTimeTaken = timeTaken;
                                shortIndex = indexNum;
                            } else if (timeTaken < shortestTimeTaken)
                            {
                                shortestTimeTaken = timeTaken;
                                shortIndex = indexNum;
                            }

                            //making sure indexNum is the index of the item
                            indexNum++;
                        }

                        // getting the closest item
                        StorageSpace shortItem = safeCopy.get(shortIndex);

                        //adding closest item to limitedTime
                        limitedTime.add(shortItem);

                        //setting new start vars
                        xStart = shortItem.getX();
                        yStart = shortItem.getY();

                        //deleting item from safeCopy
                        safeCopy.remove(shortIndex);
                    }
                }
                //gets second half of the storage
                safeCopySize = safeCopy.size();
                for (int item = 0; item <safeCopySize; item++)
                {
                    //makes sure only second half is checked
                    if (safeCopy.get(item).getX() > (this.x / 2))
                    {
                        //algoritm here
                        //check closest item
                        int differenceX;
                        int differenceY;
                        int timeTaken;
                        int shortIndex = 0;
                        int shortestTimeTaken = 0;
                        int indexNum = 0;
                        for (StorageSpace i : safeCopy)
                        {
                            //calculate difference X
                            differenceX = xStart - i.getX();
                            differenceX = Math.abs(differenceX);

                            //calculate difference Y
                            differenceY = yStart - i.getY();
                            differenceY = Math.abs(differenceY);

                            //calculating time taken
                            timeTaken = differenceX + differenceY;

                            //checking if it's the shortest
                            if (shortestTimeTaken == 0)
                            {
                                shortestTimeTaken = timeTaken;
                                shortIndex = indexNum;
                            } else if (timeTaken < shortestTimeTaken)
                            {
                                shortestTimeTaken = timeTaken;
                                shortIndex = indexNum;
                            }

                            //making sure indexNum is the index of the item
                            indexNum++;
                        }

                        // getting the closest item
                        StorageSpace shortItem = safeCopy.get(shortIndex);

                        //adding closest item to limitedTime
                        limitedTime.add(shortItem);

                        //setting new start vars
                        xStart = shortItem.getX();
                        yStart = shortItem.getY();

                        //deleting item from safeCopy
                        safeCopy.remove(shortIndex);
                    }
            }
                //create/trow final RouteCheck object
                RouteCheck fin = new RouteCheck();
                //adding all items to the route
                for (StorageSpace v : limitedTime)
                {
                    fin.addSpace(v);
                }

                //setting meaning of finalInfo
                finalInfo = fin;
        } else
            {
                //normal simpel gretig
                //declaring vars
                int differenceX;
                int differenceY;
                int timeTaken;
                int shortIndex = 0;
                int shortestTimeTaken = 0;
                int indexNum = 0;
                //ArrayList for saving the right route
                ArrayList<StorageSpace> limitedTime = new ArrayList<>();
                ArrayList<StorageSpace> safeCopy = sp;
                //setting a startpostion
                int xStart = 0;
                int yStart = 0;
                
                //algoritm itself here
                //
            }
        System.out.println(finalInfo);
        return finalInfo;
        //end functions
    }

    public String toString()
    {
        return "";
    }
}
