/**
 * DoublyLinkedList class provides methods to creating a doubly linked list. Methods include deleting and inserting
 * items at the front and end of the list, looking up whether a data item is inside the list, checking if the list is empty, and 
 * printing all the items foward and in reverse.  
 */

public class MyDoublyLinkedList<AnyType> implements DoublyLinkedList<AnyType> {
	
	
	/** Instance variable, head, that is a Node reference to the front of the doubly linked list */
	private DoubleNode<AnyType> head;
	
	/** Instance variable, tail, that is a Node reference to the end of the doubly linked list */
	private DoubleNode<AnyType> tail;
    
	public MyDoublyLinkedList() {	
		head = null;
		tail = null;	
	}
	/**
	 * Inserts a data item at the front of the list, and covers the cases for when the list is empty or contains items
	 */

	@Override
	public void insertFirst(AnyType data) {
		DoubleNode<AnyType> newNode = new DoubleNode<AnyType>();
		newNode.data = data;
		if(isEmpty() == true) { 
			head = newNode;
			tail = head;
		}
		else { 
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
	}

	/** Deletes the data item at the front of the list and returns that item. Returns null if the list is empty.
	 * 	Also covers the case for when only one item is contained in list. 
	 */
	@Override
	public AnyType deleteFirst() {
		if( isEmpty() == false ){ 
			AnyType tempData = head.data;
			
            //only one item in list
			if (head == tail) {			
				head = null;
				tail = null;
				return tempData;
			}

			// more than one item in list
			else {						
				head = head.next;
				head.prev = null;
				return tempData;
			}
		}
		return null;
	}
	
	
	/** Deletes the data item at the end of the list and returns that item. Returns null if the list is empty.
	 * 	Also covers the case for when only one item is contained in list. 
	 */

	@Override
	public AnyType deleteLast() {
		if(isEmpty() == false) {
			AnyType tempData = tail.data;
			if(tail == head) {	
				tail = null;
				head = null;
				return tempData;
			}
			else {							/* More than one data item */
				tail = tail.prev;
				tail.next = null;
				return tempData;
			}
		}
		return null;
	}

	@Override
	public void insertLast(AnyType data) {
		
		DoubleNode<AnyType> newNode = new DoubleNode<AnyType>();
		
		newNode.data = data;
		
		if (isEmpty() == true) {
			head = newNode;
			tail = newNode;
		}
		else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}	
	}

	@Override
	public boolean isEmpty() {
		if (head == null){
			return true;
		}
		return false;
	}

	@Override
	public boolean lookup(AnyType data) {
		
		DoubleNode<AnyType> current = head; 
		
		while (current != null) { 
			if (current.data.equals(data)) {  
				return true;
			}
			current = current.next;
		}
		return false;	
	}

	/** Prints each item in the list starting from the front of the list and moving to the end */
	@Override
	public void printList() {
		DoubleNode<AnyType> current = head;
		while(current != null) {
			System.out.print( current + " " );
			current = current.next;
		}	
	}

	/** Prints each item in the list starting from the end of the list and moving to the front */
	@Override
	public void printListRev() {
		DoubleNode<AnyType> current = tail;
		while(current != null) {
			System.out.println( current + " " );
			current = current.prev;
		}	
	}
}