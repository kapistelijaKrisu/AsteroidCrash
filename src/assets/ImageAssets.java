package assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import main.Main;

public class ImageAssets {
	//background
	 public static final BufferedImage bg1= ImageAssets.loadImage("bg.jpg");
	 public static final BufferedImage metal= ImageAssets.loadImage("metal.png");
	 //entity imgs
	 
	 public static final BufferedImage plate= ImageAssets.loadImage("plate.png");
	 public static final BufferedImage player= ImageAssets.loadImage("pShip.png");
	 public static final BufferedImage eShip= ImageAssets.loadImage("eShip.png");
	 //visual effect sprites

	 public static final SpriteSheet explosion= new SpriteSheet(ImageAssets.loadImage("ani7.png"));
	 public static final SpriteSheet asteroids= new SpriteSheet(ImageAssets.loadImage("astroids.png"));
	 //bubbles
	 public static final BufferedImage redBullet= ImageAssets.loadImage("redBullet.png");
	 public static final BufferedImage blueBubble= ImageAssets.loadImage("bubble.png");
	 public static final BufferedImage orangeBubble= ImageAssets.loadImage("orangeBubble.png");
	 public static final BufferedImage greenBubble= ImageAssets.loadImage("greenBubble.png");


    	/*public static void init(){
    		 bg1 = ResourceLoader.loadImage("bg.png");
    		 metal = ResourceLoader.loadImage("metal.png");

    	        explosion = new SpriteSheet(ResourceLoader.loadImage("ani7.png"));
    	        asteroids = new SpriteSheet(ResourceLoader.loadImage("astroids.png"));
    	  
    	       
    	        player = ResourceLoader.loadImage("pship.png");
    	        eShip = ResourceLoader.loadImage("eShip.png");
    	        plate = ResourceLoader.loadImage("plate.png");
    	        
    	        redBullet = ResourceLoader.loadImage("redBullet.png");      
    	        blueBubble = ResourceLoader.loadImage("bubble.png");
    	        orangeBubble = ResourceLoader.loadImage("orangeBubble.png");
    	        greenBubble = ResourceLoader.loadImage("greenBubble.png");
    	}*/
    	
    	public static BufferedImage loadImage(String imageName)
    	{
    		try {		
    			URL url = Main.class.getClassLoader().getResource(imageName);
    			//System.out.println("this url " +url + " \nimage name is: " + imageName);
    			BufferedImage img = ImageIO.read(url);
    			//System.out.println("this img " + img);
    			return img;
    		} catch (IOException e) {
    			//System.out.println("this null catch");
    			e.printStackTrace();
    		}		
    		return null;
    	}
    /*	public static BufferedImage loadImage(String imageName)
    	{
    		InputStream is = ia.getClass().getClassLoader().getResourceAsStream("resources/" +imageName);
    		try {
    			System.out.println(is);
				return ImageIO.read(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}*/
}  
  

