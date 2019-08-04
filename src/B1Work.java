import org.w3c.dom.Node;

import java.util.Calendar;
import java.util.Stack;

public class B1Work {
	/*
	 * Grading:
	 * Correctly converts infix to postfix - 3pts
	 */
	public static String infixToPostfix(String infix) {
		/*
		 * Convert an infix math formula to postfix format
		 * Infix format will always include parens around any two values and a symbol
		 * You will not need to imply any PEMDAS rules besides parens
		 * EXAMPLES:
		 * infix: (1+2) postfix: 12+
		 * infix: (1+(2-3)) postfix: 123-+
		 * infix: ((1+2)-3) postfix: 12+3-
		 * infix: ((1+2)-(3*4)) postfix: 12+34*-
		 */

		char[] infArray = infix.toCharArray();
		Stack<Character> stack = new Stack<>();
		StringBuilder pfString = new StringBuilder();

		for (int i = 0; i < infArray.length; i++) {

			if(Character.isDigit(infArray[i])){
				pfString.append(infArray[i]);
			}else {

				switch (infArray[i]) {
					case ')':
						char temp = stack.pop();
						while(temp	!= '('){
							pfString.append(temp);

							temp=stack.pop();
						}
						break;
					case '(':
					case '+':
					case '-':
					case '*':
					case '/':
						stack.push(infArray[i]);
						break;
					default:
						break;
				}
			}
		}
		return pfString.toString();
	}
	/*
	 * Grading:
	 * Correctly converts postfix to infix - 2pts
	 */
	public static String postfixToInfix(String postfix) {
		/*
		 * Convert a postfix math formula to an infix format
		 * See above for conversion examples
		 * Make sure to include parens in the infix format
		 */
		char[] postArray = postfix.toCharArray();
		Stack<String> postStack = new Stack<>();

		for (int i = 0; i < postArray.length; i++) {
			if(Character.isDigit(postArray[i])){
				String temp = Character.toString(postArray[i]);
				postStack.push(temp);
			}
			else{
				switch (postArray[i]) {
					case '+':
					case '-':
					case '*':
					case '/':
						String uno = postStack.pop();
						String dos = postStack.pop();

						postStack.push("("+dos+ postArray[i]+uno+")");
						break;
					default:
						break;
				}
			}
		}
		return postStack.pop();
	}
	/*
	 * Grading:
	 * Correctly solves postfix formulas with +-/* - 1pt
	 */
	public static double solvePostfix(String postfix) {
		Stack<Integer> stack = new Stack<>();
		/*
		 * Use a Stack to help solve a postfix format formula for the numeric answer
		 * Order of operations is implied by where symbols/numbers exist
		 * EXAMPLES
		 * postfix: 12+ = 3
		 * postfix: 123-+ = 0 :: 2-3 = -1 :: 1 + (-1) = 0
		 */

		char[] postArray = postfix.toCharArray();
		Stack<Integer> operands = new Stack<>();

		double total=0;
		for (int i = 0; i < postArray.length; i++) {
			if(Character.isDigit(postArray[i])){
				operands.push(Integer.parseInt(Character.toString(postArray[i])));
			}else {
				Integer uno = operands.pop();
				Integer dos = operands.pop();
				switch (postArray[i]){
					case '+':
						operands.push(dos + uno);
						break;
					case '-':
						operands.push(dos - uno);
						break;
					case '*':
						operands.push(dos * uno);
						break;
					case '/':
						operands.push(dos / uno);
						break;
				}
			}

		}


		return operands.pop();
	}


}

