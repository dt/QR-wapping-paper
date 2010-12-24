package net.reisub.qrapping
import scala.util.Random
import scala.math

/* case class OutOfRoomException extends Exception */


/*
abstract class Placement(canvas : Canvas) {
  var qrcodes = List()

  def addQRCode(qrcode : qrcode) : Unit = {
    qrcodes = qrcodes :: qrcode
  }
  

}

*/

class GridPlacement( paperSize : (Int, Int), qrcodes : List[Item] ) /* extends Placement*/ {
	
	def generate() : List[Canvas] = {

      val (width,height) = paperSize 

      //A cell is the smallest unit on the grid. unit in pixels
      val smallest_qrcode = qrcodes min Ordering.by((_:Item).size)
      //make sure the cell size is the diagonal of the smalled "qrcode"
      val cell_size = math.sqrt(2 * math.pow(smallest_qrcode.size,2)) toInt
      
      val cells_x = width / cell_size
      val cells_y = height / cell_size
      var taken_cells = Array.ofDim[Boolean](cells_x, cells_y)

      val canvas = new Canvas(paperSize)
      
      var pages : List[Canvas] = List(canvas) 
		
      for (qrcode <- qrcodes) {
        if (!taken_cells.forall(arr => arr.forall(cell => cell))) {
          var rand_x = 0
          var rand_y = 0
          do {
            rand_x = Random.nextInt(cells_x)
            rand_y = Random.nextInt(cells_y)
          } while (taken_cells(rand_x)(rand_y))
          canvas.add(qrcode, rand_x * cell_size, rand_y * cell_size)
          taken_cells(rand_x)(rand_y) = true
        }
      }
      pages
	}
}
