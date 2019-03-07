/* Name: Julien Phillips
 * ID: 260804197
 * 
 * This program creates a graphical user interface calculator. The user can either type in an expression or click on the 
 * buttons to enter an expression. When the equals button is pressed, the program uses two queues (an infix queue and a 
 * postfix queue) and a stack to convert an infix expression to postfix notation. Once in postfix notation, another stack
 * is used to evaluate the expression. The final evaluation of the expression has the precision of the double datatype and
 * is displayed in the output text field of the GUI. The clear button ("C") can be pressed and the user may then enter 
 * another expression to calculate.
 */

public class Queue {
	public listNode back;
	public listNode front;

	//adds node to front of queue
	public void Enqueue(String newStr){
		listNode node = new listNode(newStr);
		if(back == null) //empty queue
		{
			back = node;
			front = node;
			
		}else
		{
			node.next = back;
			back.previous = node;
			back = node;
		}
	}
	//removes node from front of queue
	public String Dequeue(){
		
		if(front == null)
		{
			System.out.println("Error: empty queue");
			return null;
		}
		String result_str = front.key_val;
		
		if(front.previous == null)
		{		
			front = null;
			back = null;
		}else
		{
			listNode pre_front = front.previous;
			pre_front.next = null;
			front = pre_front;
		}
		
		return result_str;
	}

	public boolean isEmpty(){
		return front == null;
	}		
}
