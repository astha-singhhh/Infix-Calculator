public class MyStack<AnyType> implements Stack<AnyType> {
	private SingleLinkedList<AnyType> list;

	public MyStack() {
		list = new SingleLinkedList<AnyType>();	
	}

	@Override
	public boolean isEmpty() {
		if( list.isEmpty() == true ){
			return true;
		}
		return false;
	}

	@Override
	public void push(AnyType data) {
		list.insert(data);	
	}

	@Override
	public AnyType pop() {
		if( isEmpty() == true ) { 
			return null;
		}
		else { 	
			AnyType item = list.delete();
			return item;	
		}		
	}

	@Override
	public AnyType peek() {
		if(isEmpty() == false) { 
			AnyType item = list.delete(); 			/* Delete the node at beginning of list and store data item */
			list.insert(item);						/* Insert a new node with the same data item back at the beginning of the list */
			return item;
		}
		return null; 
	}
}