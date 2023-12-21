package backend.object;

import java.util.Iterator;
import java.util.Stack;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ClownIteratorConcrete implements Iterator<GameObject> {
    private Stack<GameObject> stack;
    int i;
    int y;

    public ClownIteratorConcrete(Stack<GameObject> stack) {
        this.stack = stack;
      
    }

    @Override
    public boolean hasNext() {
      if (y < stack.size())
        return true;
      else
        return false;
    }

    @Override
    public GameObject next() {
        y++;
        return stack.get(i--);
    }
    public void setI(int i)
    {
        this.i = i;
    }

    public int getSize() {
        return stack.size();
    }
}
