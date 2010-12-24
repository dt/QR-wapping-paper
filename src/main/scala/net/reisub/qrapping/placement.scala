package net.reisub.qrapping
import 

case class OutOfRoomException extends Exception



abstract class Placement(canvas : Canvas) {
  var things = List()

  def addQRCode(qrcode : Item) : Unit = {
    things = things :: qrcode
  }

}


class GridPlacement extends Placement {
}
