package backend.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Cup extends Shape implements ObjectOnStick{

    public static final int SPRITE_HEIGHT = 30;
    public static final int SPRITE_WIDTH = 30;
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    

    private boolean caught;

    private Color[] colors = {Color.RED, Color.LIGHT_GRAY, Color.GREEN, Color.CYAN, Color.PINK};
    private Color cupColor;

    private final Clown clown;
	private int p;


    public Cup(int posX, int posY) {//deleted color from constructor and can make a new constructor with it if we want to make a cup of certain color
        this.x = posX;
        this.y = posY;
        this.visible = true;
        this.caught=false;
        clown=Clown.getInstance(0, 0, null);
        p=clown.getX();
        // create a bunch of buffered images and place into an array, to be displayed
        // sequentially
        cupColor = getRandomColor();
        spriteImages[0] = new BufferedImage(SPRITE_WIDTH, SPRITE_WIDTH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = spriteImages[0].createGraphics();
        g2.setColor(cupColor);
        int[] Xs = {10,15,25,30};
        int[] Ys = {0,30,30,0};
        Polygon reversetrapezium = new Polygon(Xs, Ys, 4);
        g2.fillPolygon(reversetrapezium);
        //g2.fillRect(getWidth()/2 - SPRITE_WIDTH/2 , getHeight()/2 - SPRITE_HEIGHT/2, SPRITE_WIDTH, SPRITE_HEIGHT);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.dispose();
        //System.out.println(spriteImages[0].getWidth());


    }
    
    public Color getColor()
    {
        return this.cupColor;
    }
    private Color getRandomColor(){
        Random random = new Random();
        int index = random.nextInt(colors.length); 
        return colors[index];
    }

    
    @Override
    public void setY(int mY) {
        if(isCaught())
            return;        
        this.y = mY;
    }

     @Override
     public void setX(int mX) {
    //     Clown clown = Clown.getInstance(0, 0, null);
    //     if(clown.getX() - getX() < 0)//rules for left stick
	// 	{
        if(isCaught())
        {
            if (clown.getX() != p) {          
            super.setX(mX);
            p = clown.getX(); 
            }
        }else
        {
            super.setX(mX);
        }
        
    }

    //from objonstick
    

	// 		if(mX<1400-clown.getWidth()+40 && mX>0)
	// 			super.setX(mX);
				
	// 	}
	// 	else{
	// 		if(mX > clown.getWidth()-60 && mX<1400  /*Circus.getWidth()*/)
	// 			super.setX(mX);
	// 	}
    //     System.out.println(1400-getWidth());
    //     System.out.println(mX);
    // }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return SPRITE_WIDTH;
    }

    @Override
    public int getHeight() {
       return SPRITE_HEIGHT;
    }
   
    @Override
    public boolean isCaught() {
        return caught;
    }

    @Override
    public void setIsCaught(boolean isCaught) {
       this.caught=isCaught;
    }
    
}

   // private boolean caught;

    // public boolean isCaught() {
    //     return caught;
    // }

    // public void setCaught(boolean caught) {
    //     this.caught = caught;
    // }

    // public Cup(int posX, int posY, String path) {
    //     super(posX, posY, path);
    // }
    