package backend.object;

import java.awt.Color;
import java.util.Stack;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Clown extends ImageObject{
    final GameObject leftStick,rightStick;
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


    public Clown(int posX, int posY, String path) {
        super(posX, posY, path);
        leftStick = new BarObject(getX() + 30, getY() - 30, 150, true, Color.RED);
        rightStick = new BarObject(getX() + 210, getY() - 30, 130, true, Color.GREEN);
        leftObjStack = new Stack<GameObject>();
        rightObjStack = new Stack<GameObject>();        
 
    }
    @Override
    public void setY(int mY) {
        //we need to talk about that it hink its not a good idea to hav eit empty like that
        
	}

    
}
