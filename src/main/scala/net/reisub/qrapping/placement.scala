package net.reisub.qrapping

abstract class Placement(canvas_size : (x: Int, y: Int)) {

  def addQRCode(code : Item)

}


class GridPlacement extends Placement {
}
