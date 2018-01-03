package tools;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class DrawImage {
	
	private static BufferedImage rimage;
	
	public static Image drawWithRotation(BufferedImage image, int degree, int size) {
		 Image tmp = image.getScaledInstance(size, size, Image.SCALE_AREA_AVERAGING);
		//    BufferedImage dimg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		
		rimage = new BufferedImage(size, size, image.getType());
		Graphics2D g2 =  rimage.createGraphics();
		g2.rotate(Math.toRadians(degree), size/2, size/2);
	//	g2.translate(size/2, size / 2);
		g2.drawImage(tmp, 0, 0, size, size, null);
		g2.dispose();	
		return rimage;
	}
	
/*	public static Image drawWithRotation(BufferedImage image, int degree, ivec2 size) {
		 Image tmp = image.getScaledInstance(size.x, size.y, Image.SCALE_AREA_AVERAGING);
		//    BufferedImage dimg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		
		rimage = new BufferedImage(size.x, size.y, image.getType());
		Graphics2D g2 =  rimage.createGraphics();
		g2.rotate(Math.toRadians(degree), size.x/2, size.y/2);
	//	g2.translate(size/2, size / 2);
		g2.drawImage(tmp, 0, 0, size.x, size.y, null);
		g2.dispose();	
		return rimage;
	}*/
	
	public static Image drawImgScaled(BufferedImage image, int sizeX, int sizeY) {
		Image tmp = image.getScaledInstance(sizeX, sizeY, Image.SCALE_AREA_AVERAGING);
		rimage = new BufferedImage(sizeX, sizeY, image.getType());
		Graphics2D g2 =  rimage.createGraphics();
		g2.drawImage(tmp, 0, 0, sizeX, sizeY, null);
		g2.dispose();	
		return rimage;
	}
}
