/* Julien Phillips
 * ID: 260804197
 * 
 * This program should take an input from the console. The user 
 * inputs an infix expression and the program uses stacks and 
 * queues to generate the appropriate postfix expression.
 */


import java.util.*;
import acm.program.ConsoleProgram;


public class In2pJ extends ConsoleProgram{
	public void run() {
		String str = readLine("Enter String: ");
		StringTokenizer st = new StringTokenizer(str, "+-*/", true);
		while (st.hasMoreTokens()){
			//enqueue each token into the input queue
			//increment to the next token st.nextToken()
		}	
			
		/* 1. The infix expression is read from left to right and put into an input queue
		 * 2. The input queue is scanned token by token.
		 * 3. If the character scanned is an operand, enqueue it into postfix queue
		 * 4. If the character scanned is an operator: 
		 * 		a) if its precedence is greater than the operator at top of stack --> push into stack
		 * 		b) else its precedence is less/equal to operator at top of stack --> pop the operator(s) from stack until precendence of the scanned one is less/equal to operator at top.
		 * 			--> push scanned operator into stack 
		 * 5. Repeat all steps until infix expression input queue is scanned
		 * 6. Dequeue the postfix queue, then print each character to obtain postfix expression
		 */
		
		
		
		
		
		
		
	}
}
