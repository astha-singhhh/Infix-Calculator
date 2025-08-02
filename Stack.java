/**
 * Stack interface provides method declarations to be implemented in a stack class, such as pushing and popping
 * items at the top of the stack, peeking at the current item on the top of the stack, and checking if the stack is empty 
 */

public interface Stack<AnyType> {
	public boolean isEmpty();			/* Checks to see if stack is empty */
	
	public void push(AnyType x);		/* Inserts an item at the top of the stack */
	
	public AnyType pop();				/* Deletes an item at the top of the stack and returns it */
	
	public AnyType peek();				/* Returns the item at the top of the stack without deleting it */

}