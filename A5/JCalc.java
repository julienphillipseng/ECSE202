/*Name: Julien Phillips
 * ID: 260804197
 * 
 * This program uses two queues, an infix queue and a postfix queue, and a stack to convert an infix expression 
 * to postfix notation. Once in postfix notation, another stack is used to evaluate the expression. The final 
 * evaluation of the expression has the precision of the double datatype.
 * 
 */

import java.util.StringTokenizer;
import acm.program.ConsoleProgram;


public class JCalc extends ConsoleProgram{
	public void run() {
		
		
		Queue infixQueue = new Queue();		//Creating infix queue
		Queue postfixQueue = new Queue();	//Creating postfix queue
		Stack opStack = new Stack();		//Creating stack that holds operators
		Stack evalStack = new Stack();		//Creating stack that will hold numbers from postfix evaluation
		
		String str = readLine("Enter expression: ");
		StringTokenizer st = new StringTokenizer(str, "+-*/", true);	
		//adds each token from string into the infix queue
		while (st.hasMoreTokens()){
				infixQueue.Enqueue(st.nextToken());
		}
		
		//dequeues the infix queue element by element, and creates postfix queue
		while (!infixQueue.isEmpty()){
			String next = infixQueue.Dequeue();
			if (next.equals("*")||next.equals("/")){
				if (opStack.Empty()){
					opStack.push(next);
				}
				else if (opStack.top().equals("*")||opStack.top().equals("/")){
					postfixQueue.Enqueue(opStack.pop());
					opStack.push(next);
				}
				else {
					opStack.push(next);
				}	
			}
			
			else if (next.equals("+")||next.equals("-")) {
				if (opStack.Empty()){
					opStack.push(next);
				}
				else if (opStack.top().equals("+")||opStack.top().equals("-")){
					postfixQueue.Enqueue(opStack.pop());
					opStack.push(next);
				}
				else {
					while (!opStack.Empty()){
						postfixQueue.Enqueue(opStack.pop());
						}
					opStack.push(next);
				}
			}
			
			else{
				postfixQueue.Enqueue(next);	
			}		
		}
		
		//adds the remaining elements in the operators stack to the postfix queue
		while (!opStack.Empty()){
			postfixQueue.Enqueue(opStack.pop());
			}
		
		//evaluates the postfix queue by adding elements to the stack, and removing them when calculations must be performed
		while (!postfixQueue.isEmpty()){
			String elem = postfixQueue.Dequeue();
			if (elem.equals("*")){
				String elem1 = evalStack.pop();
				String elem2 = evalStack.pop();
				double num1 = Double.parseDouble(elem1);	//Convert string to double to be able to perform calculation
				double num2 = Double.parseDouble(elem2);	//Convert srting to double to be able to perform calculation
				double rslt = num2 * num1;
				String prod = Double.toString(rslt);		//Convert the double result back into a string, put it back into stack
				evalStack.push(prod);
			}
			else if (elem.equals("/")){
				String elem1 = evalStack.pop();
				String elem2 = evalStack.pop();
				double num1 = Double.parseDouble(elem1);
				double num2 = Double.parseDouble(elem2);
				double rslt = num2 / num1;
				String quot = Double.toString(rslt);
				evalStack.push(quot);
			}
			else if (elem.equals("+")){
				String elem1 = evalStack.pop();
				String elem2 = evalStack.pop();
				double num1 = Double.parseDouble(elem1);
				double num2 = Double.parseDouble(elem2);
				double rslt = num2 + num1;
				String sum = Double.toString(rslt);
				evalStack.push(sum);
			}
			else if (elem.equals("-")){
				String elem1 = evalStack.pop();
				String elem2 = evalStack.pop();
				double num1 = Double.parseDouble(elem1);
				double num2 = Double.parseDouble(elem2);
				double rslt = num2 - num1;
				String diff = Double.toString(rslt);
				evalStack.push(diff);
			}
			else {
				evalStack.push(elem);
			}
		}
		
		String value = evalStack.pop();
		print(str + " = " + value);
		
	}	
}

