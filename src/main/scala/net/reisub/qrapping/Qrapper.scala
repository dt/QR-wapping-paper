package net.reisub.qrapping


import java.io.File
import javax.imageio.ImageIO;

import java.awt.Image
import java.awt.image.ImageObserver
import java.awt.image.BufferedImage

object Qrapper extends ImageObserver {
  def main(args : Array[String]) : Unit = {
	val things = List("raa raa ah ah ah", "ro ma ro mama", "ga ga", "ooh la la")

		
	val width : Double = 8
	val height : Double = 10.5
	val dpi : Int = 300
	val size = 400
	
	val x_dots = (dpi.toFloat * width) toInt
	val y_dots = (dpi.toFloat * height) toInt
	
	val canvas = new BufferedImage(x_dots, y_dots, BufferedImage.TYPE_INT_ARGB);
	
	
	val g = canvas getGraphics
	
	val items = things.map( content => new Item(size, content))
	
	var x_pos = 0
	var y_pos = 0
	
	for(qrcode <- items) {
		x_pos = x_pos + size
		
		if(x_pos > x_dots) {
			x_pos = 0
			y_pos = y_pos + size
		}
			
		g.drawImage(qrcode.generate, x_pos, y_pos, this)
	}
	
	ImageIO.write(canvas, "png", new File("test.png"))
	println("Done")
  }
  
  def imageUpdate(img:Image, infoflags : Int, x :Int, y:Int, width:Int, height:Int ) : Boolean = {
   false
  }
}


