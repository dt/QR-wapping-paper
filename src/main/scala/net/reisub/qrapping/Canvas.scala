package net.reisub.qrapping
import java.io.File
import javax.imageio.ImageIO;

import java.awt.Image
import java.awt.image.ImageObserver
import java.awt.image.BufferedImage
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp

class Canvas( paperSize : (Int,Int) ) extends ImageObserver {
	val (width, height) = paperSize

	val buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

	def add(item:Item, x_pos : Int, y_pos : Int) = {
		val image = item.getImage
		val transform = new AffineTransform()

		transform.rotate(Canvas.angles.draw, image.getWidth()/2, image.getHeight()/2)

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

object Canvas {
	val angles = new ShuffleBag[Int]

	angles.addMany(0, 4)
	angles.add(10)
	angles.add(30)
	angles.addMany(45, 2)
	angles.add(70)
}
