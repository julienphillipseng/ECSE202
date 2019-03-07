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
