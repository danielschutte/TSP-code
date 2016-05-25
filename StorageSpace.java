// class for which storage space must be accesed.
package tsp;

public class StorageSpace {

    //declar vars
    private int x;//horizontal
    private int y;//vertical
    private Dialog dialog;

    //constuctor
    public StorageSpace(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String toString()
    {
        return x + ", " + y + "\n";
    }
}
