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

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JTextField;

import acm.gui.TableLayout;
import acm.program.Program;


public class GUI extends Program implements ActionListener{

	String infix = "";
	JTextField input = new JTextField(""); 	// input text field is empty
	JTextField output = new JTextField(""); // output text field is empty
	
	public void init() {
		
		setSize(500, 500);
		setLayout(new TableLayout(7, 4));
		
		output.setEditable(false);
		output.setBackground(Color.WHITE);
		
		// To add we need the object and we need to give some constraint -> String
		add(input,"gridwidth = 4 height = 40");
		add(output,"gridwidth = 4 height = 40");

		String BUTTON_SIZE = "80";
		String button_label[]= {"C","","","","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
	
		String constraint = "width=" + BUTTON_SIZE + " height=" + BUTTON_SIZE;
		
		for (int i = 0; i < button_label.length; i++) {
			
			JButton cur_button = new JButton(button_label[i]);
			cur_button.setFont(new Font("Arial", Font.PLAIN, 20));
			cur_button.addActionListener(this);
			
			add(cur_button, constraint);
		}	
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getActionCommand()=="=") {	
			
			Queue infixQueue = new Queue();		//Creating infix queue
			Queue postfixQueue = new Queue();	//Creating postfix queue
			Stack opStack = new Stack();		//Creating stack that holds operators
			Stack evalStack = new Stack();		//Creating stack that will hold numbers from postfix evaluation
			
			String infix = input.getText();
			StringTokenizer st = new StringTokenizer(infix, "+-*/", true);	
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
					String prod = Double.toString(rslt); 		//Convert the double result back into a string, put it back into stack
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
			output.setText(value);
		}	
					
		
		else if(e.getActionCommand()=="C") {
			input.setText("");
			infix = "";
		
		}
		else {
			infix += e.getActionCommand();
			input.setText(infix);
		}
	}	
}


