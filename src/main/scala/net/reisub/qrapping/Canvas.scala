package net.reisub.qrapping
import java.io.File
import javax.imageio.ImageIO;

import java.awt.Image
import java.awt.image.ImageObserver
import java.awt.image.BufferedImage


class Canvas( width:Double, height:Double, dpi : Int ) extends ImageObserver {
	
	val x_dots = (dpi.toFloat * width) toInt
	val y_dots = (dpi.toFloat * height) toInt

	val buffer = new BufferedImage(x_dots, y_dots, BufferedImage.TYPE_INT_ARGB)
	
	def add(image:Image, x_pos : Int, y_pos : Int) = {
		buffer getGraphics() drawImage(image, x_pos, y_pos, this)

	}
	
	def write(format: String,  filename: String) = {
			ImageIO.write(buffer, "png", new File("test.png"))
	}

	  
  def imageUpdate(img:Image, infoflags : Int, x :Int, y:Int, width:Int, height:Int ) : Boolean = {
   false
  }
}