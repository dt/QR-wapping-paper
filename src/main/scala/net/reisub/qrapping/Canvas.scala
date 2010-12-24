package net.reisub.qrapping
import java.io.File
import javax.imageio.ImageIO;

import java.awt.Image
import java.awt.image.ImageObserver
import java.awt.image.BufferedImage
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp

class Canvas( width:Double, height:Double, dpi : Int ) extends ImageObserver {
	val margin = 0.5
	
	val x_dots = (dpi.toFloat * (width - margin)) toInt
	val y_dots = (dpi.toFloat * (height - margin)) toInt

	val buffer = new BufferedImage(x_dots, y_dots, BufferedImage.TYPE_INT_ARGB)
	
	def add(item:Item, x_pos : Int, y_pos : Int) = {
		val image = item.getImage
		val transform = new AffineTransform()
		
		transform.rotate(Math.toRadians(Math.random * 360), image.getWidth()/2, image.getHeight()/2)
		
		val op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR)
				
		buffer getGraphics() drawImage(op.filter(image, null), x_pos, y_pos, this)
	}
	
	def write(format: String,  filename: String) = {
			ImageIO.write(buffer, "png", new File("test.png"))
	}

	  
  def imageUpdate(img:Image, infoflags : Int, x :Int, y:Int, width:Int, height:Int ) : Boolean = {
   false
  }
}