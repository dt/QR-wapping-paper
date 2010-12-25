package net.reisub.qrapping
import java.io.File
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage
import java.awt.Image
import java.awt.image.ImageObserver
import java.awt.Color


class Canvas( paperSize : (Int,Int) ) extends ImageObserver {
  val (width, height) = paperSize
	
  val buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
  val graphics = buffer createGraphics()
  graphics setPaint ( Color.WHITE );
  graphics fillRect ( 0, 0, width, height );

  def add(item:Item, x_pos : Int, y_pos : Int) = {
    buffer getGraphics() drawImage(item.image, x_pos, y_pos, this)
  }
	
  def write(format: String,  filename: String) = {
    ImageIO.write(buffer, "png", new File(filename))
  }

  def imageUpdate(img:Image, infoflags : Int, x :Int, y:Int, width:Int, height:Int ) : Boolean = {
   false
  }
}