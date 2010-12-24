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

class GridPlacement(canvas :Canvas, qrcodes : List[qrcode] ) /* extends Placement*/ {
	
	def calculate() = {
		
      val g = canvas.buffer getGraphics

      //A cell is the smallest unit on the grid. unit in pixels
      val smallest_qrcode = qrcodes min Ordering.by((_:qrcode).size)
      //make sure the cell size is the diagonal of the smalled "qrcode"
      val cell_size = math.sqrt(2 * math.pow(smallest_qrcode.size,2)) toInt
      
          
      val cells_x = canvas.x_dots / cell_size
      val cells_y = canvas.y_dots / cell_size
      for (qrcode <- qrcodes) {
        val rand_x = Random.nextInt(cells_x)
        val rand_y = Random.nextInt(cells_y)
        canvas.add(qrcode.getImage, rand_x * cell_size, rand_y * cell_size)
      }
      /*
      var x_pos = 0
      var y_pos = 0
      
      for(qrcode <- qrcodes) {
          x_pos = x_pos + qrcode.size
          
          if(x_pos > canvas.x_dots) {
              x_pos = 0
              y_pos = y_pos + qrcode.size
          }
              
          canvas.add(qrcode.getImage, x_pos, y_pos)
      }
      */
	}
}
