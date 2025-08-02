public class MyQueue<AnyType> implements Queue<AnyType> {
	private MyDoublyLinkedList<AnyType> list;
	
	public MyQueue(){
		
		list = new MyDoublyLinkedList<AnyType>();
		
	}

	@Override
	public boolean isEmpty() {
		if(list.isEmpty() == true) {	
			return true;
		}
		return false;
	}

	@Override
	public void enqueue(AnyType data) {
		list.insertLast( data );
		
	}

	@Override
	public AnyType dequeue() {
		if(isEmpty() == false) {
			AnyType data = list.deleteFirst();
			return data;	
		}
		return null; 
	}

	@Override
	public AnyType peek() {
		if(isEmpty() == false ) {
			AnyType tempData = list.deleteFirst();			/* Delete the node at beginning of list and store data item */
			list.insertFirst(tempData);						/* Insert a new node with the same data item back at the beginning of the list */
			return tempData;	
		}
		return null;
	}
}