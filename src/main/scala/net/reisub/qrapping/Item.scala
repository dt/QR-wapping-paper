package net.reisub.qrapping

import java.awt.image.BufferedImage
import com.google.zxing._
import qrcode._
import client.j2se._
import java.awt.image.ImageObserver

object Item {
	val writer = new QRCodeWriter
}

class Item( size : Int, content :String ) {
	
	def generate : BufferedImage = {
		val matrix = Item.writer encode(content, BarcodeFormat.QR_CODE, size, size, null)
		MatrixToImageWriter toBufferedImage(matrix)
	}
}