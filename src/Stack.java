import java.util.ArrayList;

public class Stack {
	private ArrayList<Integer> elements;
	private int top=-1;
	
	public Stack() {
		elements=new ArrayList<Integer>();
	}
	
	//push an item to the stack
	public void push(int item) {
		elements.add(item);
		top++;
	}

	public boolean isEmpty(){
		return top<0;
	}

	public int size(){
		return top+1;
	}
	//pop the top item and return it
	public int pop() {
		//no elements in it
		if(top<0) {
			System.out.println("No elements in the stack");
			return -1;
		}
		Integer element = elements.remove(top);
		top--;
		return element;
	}

	public int peek(){
		if(top<0){
			return -1;
		}
		return elements.get(top);
	}

	public void display() {
		//no elements in the stack
		if(top<0) {
			System.out.println("No elements in the stack!");
		}else {
			for (Integer t : elements) {
				System.out.print(t.toString()+"  ");
			}
			System.out.println();
		}
	}
	
	//Display all elements between start and end indexes, while making sure the indexes are in the bound
	public void display(int start, int end) {
		if(start>end) {
			System.out.println("The start index should be less or equal to the end index!");
		}else if(start > top || end > top || start < 0) {
			System.out.println("Index out of bound!");
		}else {
			for(int i=start; i<=end ; i++) {
				System.out.print(elements.get(i).toString()+"  ");
			}
			System.out.println();
		}
	}
	
	//return the index of the first occurance of a given element. If failed to find the element, return -1.
	public int search(Integer element) {
		for(int i=0; i<elements.size(); i++) {
			if(elements.get(i).equals(element)) {
				return i;
			}
		}
		return -1;
	}
	

}
