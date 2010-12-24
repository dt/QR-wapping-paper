package net.reisub.qrapping

abstract class Placement(canvas_size : (x: Int, y: Int)) {

  def addQRCode(code : Item)

  def write(file : File, format : String)
}


class GridPlacement extends Placement {
}
