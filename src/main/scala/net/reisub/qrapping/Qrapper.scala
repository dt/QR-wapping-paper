package net.reisub.qrapping



object Qrapper {
  def main(args : Array[String]) : Unit = {
	val things = List("raa raa ah ah ah", "ro ma ro mama", "ga ga", "ooh la la")

	val canvas = new Canvas( 8.5, 11, 300)
	
	val size = 400
	
	val items = things.map( content => new Item(size, content))
	
	/*
	val g = canvas getGraphics
		
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
	 */
	println("Done")
  }

}


