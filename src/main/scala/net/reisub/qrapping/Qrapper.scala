package net.reisub.qrapping

import com.google.zxing._
import qrcode._
import client.j2se._
import java.io.File
import java.awt.image.BufferedImage
import java.Graphics

object Qrapper {
  def main(args : Array[String]) : Unit = {
	  println("Hello World")
	  
	  val writer = new QRCodeWriter();
	  writer.encode()
  }
}
