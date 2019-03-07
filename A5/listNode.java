/*Name: Julien Phillips
 * ID: 260804197
 * 
 * This program uses two queues, an infix queue and a postfix queue, and a stack to convert an infix expression 
 * to postfix notation. Once in postfix notation, another stack is used to evaluate the expression. The final 
 * evaluation of the expression has the precision of the double datatype.
 * 
 */

//Creating a node
public class listNode {
	
	 public String key_val;
	 public listNode next;
	 public listNode previous;
	    
	 public listNode(String input) {

	    key_val = input;
	    next = null;
	    previous = null;
	 }
}
