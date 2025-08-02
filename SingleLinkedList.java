/**
 * SingleLinkedList class provides methods to creating a singly linked list. Methods include deleting and inserting
 * items at the beginning of the list, looking up whether a data item is inside the list, checking if the list is empty, and printing
 * the entire list. 
 */

public class SingleLinkedList<AnyType> implements SimpleLinkedList<AnyType> {
	private Node<AnyType> head;
	
	public SingleLinkedList() {
	
		head = null;
    }

	@Override
	public void insert(AnyType data) {
		Node<AnyType> newNode = new Node<AnyType>();
		newNode.data = data;
		if(isEmpty() == true) { 		
			head = newNode;
		}
		else {							
			newNode.next = head;
			head = newNode;
		}
		
	}

	@Override
	public AnyType delete(){
		if(isEmpty() == false) {
			AnyType tempData = head.data;
			head = head.next;
			return tempData;
		}
		return null;	
	}

	@Override
	public boolean lookup(AnyType data) {
		Node<AnyType> current = head;
		while(current != null){
			if(current.data.equals(data)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(head == null) {
			return true;
		}
		return false;
	}
	
    @Override 
	public void printList() {
		Node<AnyType> current = head;
		while(current != null) {
			System.out.print(current);
			current = current.next;
		}	
	}
}