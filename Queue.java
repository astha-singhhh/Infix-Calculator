public interface Queue<AnyType> {
	public boolean isEmpty();					//Checks if the queue is empty 
	
	public void enqueue(AnyType data);			// Inserts an item at the front of the queue 
	
	public AnyType dequeue();					// Deletes an item at the front of the queue and returns it 
	
	public AnyType peek();						// Returns an item at the front of the queue without deleting it 

}