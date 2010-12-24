package net.reisub.qrapping

import scala.collection.mutable.Queue;

class ShuffleBag[T] {

	var bag : Queue[T] = new Queue;
	var items : List[(T, Int)] = Nil
	
	def add(item : T) = {
		addMany(item, 1)
	}
	
	def addMany(item : T, count : Int) = {
		items =  (item , count) :: items
	}
	
	def refill() = {
		var all_items : List[T] = Nil
		for( (item, count) <- items)
			for(i <- 0 to count )
				all_items = item :: all_items
			
		all_items = scala.util.Random.shuffle(all_items)
		
		bag ++= all_items
	}
	
	def draw() = {
		if(bag.isEmpty)
			refill()
		bag.dequeue
	}
}