package backend.object;

import java.awt.Color;
import java.util.Stack;

import backend.world.Circus;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Clown extends ImageObject  {
    private static Clown clown;

    final BarObject leftStick, rightStick;
    private Stack<GameObject> leftObjStack;
    private Stack<GameObject> rightObjStack;

    public Stack<GameObject> getLeftObjStack() {
        return this.leftObjStack;
    }

    public Stack<GameObject> getRightObjStack() {
        return this.rightObjStack;
    }

    public GameObject getLeftStick() {
        return this.leftStick;
    }

    public GameObject getRightStick() {
        return this.rightStick;
    }

    public boolean isClownOnFarLeft()
    {
        if(getX() == 0)
        {
            return true;
        }
        return false;
    }

    public boolean isClownOnFarRight()
    {
        if(getX() == Circus.getCircus(null).getWidth() - getWidth())
            return true;
        return false;
    }

    private Clown(int posX, int posY, String path) {
        super(posX, posY, path);
        leftStick = new BarObject(getX() + 30, getY() - 30, 150, Color.RED,this,true);
        rightStick = new BarObject(getX() + 210, getY() - 30, 130, Color.GREEN,this,false);
        leftObjStack = new Stack<GameObject>();
        rightObjStack = new Stack<GameObject>();

    }

    public static Clown getInstance(int posX, int posY, String path){

        synchronized (Clown.class) {
            if (clown == null) {
                clown = new Clown(posX, posY, path);
            }
        }
        return clown;
    }

    @Override
    public void setY(int mY) {
        // we need to talk about that it hink its not a good idea to hav eit empty like
        // that

    }
    // @Override
    // public void setX( int mX)
    // {
    //     super.setX(mX);
    // }

   

}
