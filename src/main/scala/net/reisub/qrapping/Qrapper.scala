package net.reisub.qrapping



object Qrapper {
  def main(args : Array[String]) : Unit = {
	val things = List("raa raa ah ah ah", "ro ma ro mama", "ga ga", "ooh la la")

	val canvas = new Canvas( 8.5, 11, 300)

	val sizes = new ShuffleBag[Int]();
	sizes.addMany( (300 * 2.5).toInt, 2);
	sizes.addMany( (300 * 1.5).toInt, 5);
	sizes.addMany( (300 * 0.75).toInt, 8);
	
	
	val items = things.map( content => new Item(sizes.draw(), content))

	val placement = new GridPlacement(canvas, items)
	
	placement calculate()
	
	canvas write("png", "test.png")
	
	println("Done")
  }

}


