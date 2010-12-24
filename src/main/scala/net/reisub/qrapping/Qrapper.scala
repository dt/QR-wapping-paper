package net.reisub.qrapping



object Qrapper {
  def main(args : Array[String]) : Unit = {
	val things = List("raa raa ah ah ah", "ro ma ro mama", "ga ga", "ooh la la")

	val canvas = new Canvas( 8.5, 11, 300)
	
	val size = 400
	
	val items = things.map( content => new Item(size, content))

	val placement = new GridPlacement(canvas, items)
	
	placement calculate()
	
	canvas write("png", "test.png")
	
	println("Done")
  }

}


