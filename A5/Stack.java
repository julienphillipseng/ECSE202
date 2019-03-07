/*Name: Julien Phillips
 * ID: 260804197
 * 
 * This program uses two queues, an infix queue and a postfix queue, and a stack to convert an infix expression 
 * to postfix notation. Once in postfix notation, another stack is used to evaluate the expression. The final 
 * evaluation of the expression has the precision of the double datatype.
 * 
 */

public class Stack {
	public listNode top;
    public Stack(){
        top = null;

    }
    //adds node to top of stack
    public void push(String newStr){
        listNode newTop = new listNode(newStr);
        if (top != null){
            top.next = newTop;
            newTop.previous = top;
        }

        top = newTop;
    }
    //removes node from top of stack
    public String pop(){
        if (top == null){
            System.out.println("Error: empty stack");
            return null;
        }
        else{
            String topStr = top.key_val;
            this.top = this.top.previous;
            return topStr;
        }
    }

    public boolean Empty(){
        return top == null;
    }

    public String top(){
        if (top == null){
            System.out.println("Error: empty stack");
            return null;
        }
        else{
            return top.key_val;
        }
    }
}

