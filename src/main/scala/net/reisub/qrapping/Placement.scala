package net.reisub.qrapping

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
	}
}
