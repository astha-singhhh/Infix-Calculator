/*
 * SimpleLinkedList interface provides method declarations to be implemented in a singly linked list, such as deleting and inserting
 * items at the beginning of the list, looking up, checking if the list is empty, and printing the list. 
 */

public interface SimpleLinkedList<AnyType> {
	public void insert(AnyType x); 						/* inserts a data item at front of list */
	
	public AnyType delete(); 							/* deletes data item at front of list */
	
	public boolean lookup(AnyType x); 					/* verifies that a data item is inside list */
	
	public boolean isEmpty(); 							/* checks to see if the list is empty */
	
	public void printList(); 							/* prints all the list items out */

}