package net.reisub.qrapping

object Qrapper {
  
  val things = List (
    "https://github.com/tinystatemachine/Qrapping-Paper"    
  )

  lazy val paper = (8.5 inches, 11 inches)

  lazy val dpi = 300;

  lazy val (small   , med,      large,   huge) = 
           (0.75 in , 1.25 in , 2.25 in, 2.75 in)
  
  lazy val frequencies = 
           (10,       7,        2,       1)
    
  lazy val rotation = on
  
  lazy val verbose = on
  
  def main(args : Array[String]) : Unit = {
    println()
    println("QRapper starting up...")  
    println()
    
    println("Paper is "+paper._1+" by "+paper._2)
      
    println("QR Code sizes are "+ small + "px, "+med+"px, and "+large+"px.")
    
    val (small_count, med_count, large_count, huge_count) = frequencies
    val sizes = new ShuffleBag[Int]()
    sizes.addMany( huge  , huge_count)
    sizes.addMany( large , large_count)
    sizes.addMany( med   , med_count)
    sizes.addMany( small , small_count)
      
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

  val off = false
  val on = true
  
  class DoubleInches(i : Double ) {
    def inches = in
    def in = (i * dpi) toInt
  }

  implicit def doubleToInches(d: Double) : DoubleInches = new DoubleInches(d)

}


