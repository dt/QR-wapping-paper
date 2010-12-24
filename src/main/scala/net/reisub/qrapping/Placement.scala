package net.reisub.qrapping
import scala.util.Random

/* case class OutOfRoomException extends Exception */


/*
abstract class Placement(canvas : Canvas) {
  var items = List()

  def addQRCode(qrcode : Item) : Unit = {
    items = items :: qrcode
  }
  

}

*/

class GridPlacement(canvas :Canvas, items : List[Item] ) /* extends Placement*/ {
	
	def calculate() = {
		
      val g = canvas.buffer getGraphics

      //A cell is the smallest unit on the grid. unit in pixels
      val smallest_item = items min Ordering.by((_:Item).size)
      val cell_size = smallest_item.size
      
          
      val cells_x = canvas.x_dots / cell_size
      val cells_y = canvas.y_dots / cell_size
      for (item <- items) {
        val rand_x = Random.nextInt(cells_x)
        val rand_y = Random.nextInt(cells_y)
        canvas.add(item.getImage, rand_x * cell_size, rand_y * cell_size)
      }
      /*
      var x_pos = 0
      var y_pos = 0
      
      for(item <- items) {
          x_pos = x_pos + item.size
          
          if(x_pos > canvas.x_dots) {
              x_pos = 0
              y_pos = y_pos + item.size
          }
              
          canvas.add(item.getImage, x_pos, y_pos)
      }
      */
	}
}
