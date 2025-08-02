/**
 * Node class creates a node for a doubly linked list that contains a reference to the next and previous node and a data item
 */

public class DoubleNode<AnyType> {
	public AnyType data;
	public DoubleNode<AnyType> next;
	public DoubleNode<AnyType> prev;
	@Override
	public String toString() {
		return data + "";	
	}
}