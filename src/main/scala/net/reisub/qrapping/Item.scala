package net.reisub.qrapping

import java.awt.image.BufferedImage
import com.google.zxing._
import qrcode._
import client.j2se._
import java.awt.image.BufferedImage
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.ImageObserver

// size in pixels
class Item(dims : Int, content :String ) {
	val writer = new QRCodeWriter
	
    lazy val image = {
		val matrix = writer encode(content, BarcodeFormat.QR_CODE, dims, dims, null)
		val raw = MatrixToImageWriter toBufferedImage(matrix)
		val transform = new AffineTransform()
		
		if( Qrapper.rotation ) {
			val rotate = 90 * util.Random.nextInt(3)
			transform.rotate(math.toRadians(Item.angles.draw + rotate), raw.getWidth/2,raw.getHeight/2)
		}
		val op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR)
		op.filter(raw, null)
		
	}
	
	lazy val height = image.getHeight
	lazy val width = image.getWidth
	
	lazy val size = math.max(image.getWidth , image.getHeight ) - 20
}

object Item {
	val angles = new ShuffleBag[Int]
	
	angles.addMany(0, 4)
	angles.add(10)
	angles.add(15)
	angles.add(30)
	angles.add(45)
	angles.add(45)
}