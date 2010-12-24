package net.reisub.qrapping

import com.google.zxing._
import qrcode._
import client.j2se._
import java.io.File
import java.awt.Image
import java.awt.image.ImageObserver
import java.awt.image.BufferedImage

object Qrapper extends ImageObserver {
  def main(args : Array[String]) : Unit = {
	val things = List("raa raa ah ah ah", "ro ma ro mama", "ga ga", "ooh la la")

		
	val width : Double = 8
	val height : Double = 10.5
	val dpi : Int = 300
	
	val x_dots = (dpi.toFloat * width) toInt
	val y_dots = (dpi.toFloat * height) toInt
	
	val canvas = new BufferedImage(x_dots, y_dots, BufferedImage.TYPE_INT_ARGB);
	
	val size = 100
	val writer = new QRCodeWriter

	val qrcodes = things.map(s => 
	  {
		val matrix = writer encode(s, BarcodeFormat.QR_CODE, size, size, null)
		MatrixToImageWriter toBufferedImage(matrix)
	  }
	)
	
	val g = canvas getGraphics
	
	for(qrcode <- qrcodes)
		g.drawImage(qrcode, 0, 0, this)
	println("Done")
  }
  
  def imageUpdate(img:Image, infoflags : Int, x :Int, y:Int, width:Int, height:Int ) : Boolean = {
   false
  }
}
