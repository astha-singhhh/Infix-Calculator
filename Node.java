public class Node<AnyType> {
	
	//Instance variable that represents a reference to the next node 
	public Node<AnyType> next; 
	
	//Instance variable that represents the data item that the node holds
	public AnyType data;
	
	@Override
	public String toString(){
		return data + "";
	}
}