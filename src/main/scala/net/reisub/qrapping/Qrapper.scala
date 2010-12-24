package net.reisub.qrapping



object Qrapper {

  val dpi = 300;

  val paper = (8.5 inches, 11 inches)

  val things = 
	  List("raa raa ah ah ah", "ro ma ro mama", "ga ga", "ooh la la") ++
	  List("raa raa ah ah ah", "ro ma ro mama", "ga ga", "ooh la la") ++
	  List("raa raa ah ah ah", "ro ma ro mama", "ga ga", "ooh la la") ++
	  List("raa raa ah ah ah", "ro ma ro mama", "ga ga", "ooh la la")



  def main(args : Array[String]) : Unit = {
    val sizes = new ShuffleBag[Int]()

	sizes.addMany( 2.5 in , 2)
	sizes.addMany( 1.5 in , 5)
	sizes.addMany( .75 inches , 8)


	val items = things.map( content => new Item(sizes.draw(), content))

	val placement = new GridPlacement(paper, items)

    var pages = 0
	for(page <- (placement generate())) {
	  pages = pages + 1
	  println("Writing page "+pages)
	  page write("png", "page-"+pages+".png")
	}

	println("Done")
  }

  class DoubleInches(i : Double ) {
	  def inches = in
	  def in = (i * dpi) toInt
  }

  implicit def doubleToInches(d: Double) : DoubleInches = new DoubleInches(d)

}


