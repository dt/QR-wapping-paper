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


/* Utility class for mapping between "blocks" and grid cells
TODO: Implement the conversion logic
*/
class GridSpace(cells_x : Int, cells_y : Int, val cell_size : Int) {
      var taken_cells = Array.ofDim[Boolean](cells_x, cells_y)

      def cells_used(block_size : Int) : Int = {
          return ((block_size : Double) / cell_size ceil) toInt
      }

      def block_fits(x : Int, y : Int, block_size : Int) : Boolean = {
          return taken_cells(x)(y)
      }

      def take_block(x : Int, y : Int, block_size : Int) : Unit = {
          taken_cells(x)(y) = true
      }

      def all_taken() : Boolean = {
          return taken_cells.forall(arr => arr.forall(cell => cell))
      }

}

class GridPlacement( paperSize : (Int, Int), qrcodes : List[Item] ) /* extends Placement*/ {

	def generate() : List[Canvas] = {

      val (width,height) = paperSize 

      //A cell is the smallest unit on the grid. unit in pixels
      val smallest_qrcode = qrcodes min Ordering.by((_:Item).size)
      //make sure the cell size is the diagonal of the smalled "qrcode"
      val cell_size = math.sqrt(2 * math.pow(smallest_qrcode.size,2)) toInt

      val cells_x = width / cell_size
      val cells_y = height / cell_size
      var grid = new GridSpace(cells_x, cells_y, cell_size)

      val canvas = new Canvas(paperSize)

      var pages : List[Canvas] = List(canvas) 

      for (qrcode <- qrcodes) {
        if (!grid.all_taken()) {
          var rand_x = 0
          var rand_y = 0
          do {
            rand_x = Random.nextInt(cells_x)
            rand_y = Random.nextInt(cells_y)
          } while (grid.block_fits(rand_x, rand_y, qrcode.size))
          canvas.add(qrcode, rand_x * cell_size, rand_y * cell_size)
          // todo: larger codes take more cells
          grid.take_block(rand_x, rand_y, qrcode.size)
          }
        }
      pages
	}
}
