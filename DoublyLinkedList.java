public interface DoublyLinkedList<AnyType> {
	public void insertFirst(AnyType data); 					
	public AnyType deleteFirst(); 							
	public AnyType deleteLast();							
	public void insertLast(AnyType data);					
	public boolean lookup(AnyType x); 						
	public boolean isEmpty(); 							
	public void printList(); 							
	public void printListRev(); 						
}