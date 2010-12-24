package net.reisub.qrapping

import java.awt.image.BufferedImage
import com.google.zxing._
import qrcode._
import client.j2se._
import java.awt.image.ImageObserver

// size in pixels
class Item(val size : Int, content :String ) {
	val writer = new QRCodeWriter

	def getImage : BufferedImage = {
		val matrix = writer encode(content, BarcodeFormat.QR_CODE, size, size, null)
		MatrixToImageWriter toBufferedImage(matrix)
	}
}
