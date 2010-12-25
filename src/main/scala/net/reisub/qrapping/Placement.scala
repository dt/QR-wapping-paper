package net.reisub.qrapping

import scala.util.Random
import scala.math
import scala.util.control.Breaks._

class GridPlacement( paperSize : (Int, Int), items : List[Item] ) /* extends Placement*/ {

  val tries = 30
  
  def generate() : List[Canvas] = {
    val p = (i:Int, j:Int) => { "("+i+","+j+")" }
    val q = (i:Int) => p(i, i)

    val (width,height) = paperSize 

    val qrcodes = items sortWith { (a, b) => {a.size > b.size} }
      
    //A cell is the smallest unit on the grid. unit in pixels
    val smallest_qrcode = qrcodes last

    //make sure the cell size is the diagonal of the smalled "qrcode"
    val cell_size = math.sqrt(2 * math.pow(smallest_qrcode.size,2)) toInt
      
    println("Cells are "+q(cell_size))
      
    val cells_x = width / cell_size
    val cells_y = height / cell_size
      
    println("Canvas is "+p(cells_x, cells_y))
         
    var pages : List[(Canvas, Array[Array[Boolean]])] = Nil 
  
    for (qrcode <- qrcodes) {
      var stuck = false
      
      breakable {
        while(true) {
        
          println()
          println("Placing a "+q(qrcode.size))
          for((page, occupied) <- pages)
            print_matrix("  ", occupied)
        
          val cells = qrcode.size / cell_size + 1
 
          breakable {
            for((page, occupied) <- pages) {
              for(i <- 0 until tries) {
                val rand_x = Random.nextInt(cells_x)
                val rand_y = Random.nextInt(cells_y)
                print("  Trying "+rand_x+","+rand_y+"...")
          
                if( occupied.slice(rand_y, rand_y + cells).map(  
                    r => r.slice(rand_x, rand_x + cells).count(c => !c )
                  ).sum >= (cells * cells) ) {
             
                  println(Console.GREEN + "Fits!" + Console.WHITE)            
                  println("    Claiming "+q(cells)+" cells:")
          
                  for (y <- rand_y until math.min(rand_y+cells, cells_y) ) {
                    for (x <- rand_x until math.min(rand_x+cells, cells_x) ) {
                      occupied(y)(x) = true
                      print(p(x,y)+" ")
                    }
                  }
  
                  page.add(qrcode, rand_x * cell_size, rand_y * cell_size)
                  stuck = false
                  pages = scala.util.Random.shuffle(pages)
            
                  break

                } else {
                  println("Nope")
                }

              } //if we finish this loop, we ran out of attempts for this page
 
              println("\tGoing to next page...")
            } // if we finish this loop, we ran out of pages (uh-oh)
        
            stuck = true
            println( Console.RED + "Could not find a "+q(cells)+" spot after "+tries+" tries" + Console.WHITE)
            pages = pages  :+ ( new Canvas(paperSize), Array.ofDim[Boolean](cells_y, cells_x)  ) 

          } // break to here on successful place (to stop trying)

          if (pages.length > 10)
            throw new RuntimeException("Buggy?")
        
          // if we placed this item, we move on ( as opposed to trying again with an extra page)
          if(!stuck) break;

        } // start again with this code (if we didn't break)
      } // break to here if we were not stuck
    } // done with this qrcode

    pages.map { p:(Canvas, Any) => p._1 }
  }
  
  def println(s : String) {
    if(Qrapper.verbose) Predef.println(s)
  }
  
  def print(s : String) {
    if(Qrapper.verbose) Predef.print(s)
  }

  def println() { println("") }

  def print_matrix(prefix : String, m : Array[Array[Boolean]]) {
    if(Qrapper.verbose) {
      println(Console.BLUE)
      for( row <- m) {
        println(prefix + ("+-" * row.length )+"+")
        print(prefix)
        for(cell <- row) {
          print("|")
          if( cell ) print(Console.CYAN + "x" + Console.BLUE ) else print(" ")
        }
        println("|")
      }
      println(prefix + ("+-" * m.head.length ) +"+")
      println(Console.WHITE)
    }
  }
}
