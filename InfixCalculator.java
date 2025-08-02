import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * InfixCalculator class provides methods to take in an infix expression from an input file in the command line, 
 * translate that infix expression to postfix, then evaluate that postfix expression and write the output to a file. 
 */
public class InfixCalculator {
	private String expression;
	private String[] expressionArray; 
	private String[] operators = {"!", "&", "|", "(", ")", "+", "-", "/", "*", "^", "<", "=", ">", "%"};
	
	private MyStack<String> stack;
	private MyQueue<String> queue;

	public InfixCalculator(String expression) {
		this.expression = expression.replaceAll("\\s+","");	
		String[] tempArray = (this.expression).split(""); 
			
		stack = new MyStack<String>();
		queue = new MyQueue<String>();
		
		expressionArray = determineTokens( tempArray );	
		expressionArray = determineIfNegativeOperand( expressionArray ); 
	}

	public String[] determineIfNegativeOperand(String[] array) {
		String negativeSign = "-";
		String[] tempArray = new String[expressionArray.length]; 
		String[] finalArray; 
		int index = 0;
		int size = expressionArray.length;
		
		for(int i = 0; i < expressionArray.length; i++) {
			if(i == 0) {	
				if( expressionArray[i].equals(negativeSign) || (expressionArray[i].equals("(") && expressionArray[i+1].equals(negativeSign))) {
					if( expressionArray[i].equals(negativeSign) ){
						tempArray[0] = ( expressionArray[0] + expressionArray[1]); 
						i++;
						size--;
					}
					else{ 
						tempArray[0] = expressionArray[0];
						tempArray[1] = expressionArray[1] + expressionArray[2];
						i = i + 2;
						size--;
					}	
				}
				else { 
					tempArray[0] = expressionArray[0];
				}
			}
			else {
                if(determineIfOperator(expressionArray[i]) == true) {
					if((i != expressionArray.length-1) && expressionArray[i+1].equals(negativeSign) && (determineIfOperator(expressionArray[i+2]) == false)) {
						tempArray[i] = expressionArray[i];
						tempArray[i+1] = ( expressionArray[i+1] + expressionArray[i+2]);
						i = i + 2;
						size--;
					}
					else {
						tempArray[i] = expressionArray[i];
					}
			    }
			    else {
				    tempArray[i] = expressionArray[i];
			    }
			
		    }	
		}
		finalArray = new String[size];
		index = 0;
		for (int j = 0; j < tempArray.length; j++) {
			if(tempArray[j] != null ){
				finalArray[index] = tempArray[j];
				index++;
			}
		}
		return finalArray;
	}

	public String[] determineTokens(String[] array) {
		int actualSize = 0; 
		int operandAmount = 0; 
		MyStack<String> elemStack = new MyStack<String>(); 
		for(int i = 0; i < array.length; i++) { 
			
			if(determineIfOperator(array[i]) == true) { 
				elemStack.push(array[i]);
				actualSize++;
				operandAmount = 0;
			}
			else {
				operandAmount++; 
				elemStack.push(array[i]);
				actualSize++;

				if(operandAmount > 1) {
					elemStack.pop();
					String temp = elemStack.peek();	
					elemStack.pop();

					temp = temp + array[i];

					elemStack.push(temp);

					operandAmount--;
					actualSize--;
				}	
			}
		}
		String[] strings = new String[actualSize]; 
		for(int i= actualSize - 1; i >= 0; i--) {
			strings[i] = elemStack.pop(); 
		}
		return strings;	
	}

	public boolean determineIfOperator(String currentExp) {
		for(String current: operators) {
			if(currentExp.equals(current)) {
				return true;
			}
		}	
		return false;
	}

	public int determinePrecedence( String operator ){
		
		int precedence = -1;
		switch(operator) {
		case "(":
		case ")":
			precedence = -1;
			break;
		case "^":
			precedence = 7;
			break;
		case "!":
			precedence = 6; 
			break;
		case "*":
		case "/":
		case "%":
			precedence = 5;
			break;
		case "+":
		case "-":
			precedence = 4;
			break;
		case "<":
		case ">":
			precedence = 3;
			break;
		case "=":
			precedence = 2;
			break;
		case "&":
			precedence = 1;
			break;
		case "|":
			precedence = 0;
			break;
		}
		return precedence;
	}
	
	public void infixConvert(){
		for(String currentExp: expressionArray) { 
			String topOp = ""; 
			if(determineIfOperator(currentExp) == false) {
				queue.enqueue(currentExp);
			}
			else if(currentExp.equals("(")) { 
				stack.push(currentExp);
			}
			else if(currentExp.equals(")")) {
				topOp = stack.pop();
				while(!(topOp.equals("("))) {
					queue.enqueue(topOp);
					topOp = stack.pop();	
				}	
			}
			else {
				if(stack.isEmpty() == true) {
					stack.push(currentExp);
				}
				else {
					while(stack.isEmpty() == false) {
						topOp = stack.pop();
						if(topOp.equals("(")) {
							stack.push(topOp);
							break;
						}
						else if(determinePrecedence(topOp) >= determinePrecedence(currentExp)) {
							queue.enqueue(topOp);	
						}
						else {
							stack.push(topOp);
							break;
						}
					}
					stack.push(currentExp);
				}
			}	
		}
		while(stack.isEmpty() == false) {
			String temp = stack.pop();
			queue.enqueue(temp);
		}
	}

	public String postfixEval() {
		while(queue.isEmpty() == false) {
			String token = queue.dequeue();
			if(determineIfOperator(token) == false) {
				stack.push(token);
			}
			else {
				String operand1, operand2, output;
				if(token.equals("!")) { 
					operand1 = stack.pop();
					output = performNotOperation(operand1);
					stack.push(output);
				}
				else {
					operand2 = stack.pop();
					operand1 = stack.pop();
					output = performOperation(operand1, operand2, token);
					stack.push(output);
				}
			}
		}
		String answer = stack.pop();
		return answer;
	}

	public String performOperation( String operand1, String operand2, String operator) {
		double op1 = Double.parseDouble(operand1);
		double op2 = Double.parseDouble(operand2);
		double result = 0;
		switch(operator) {
		
		case "*":
			result = op1 * op2;
			break;
			
		case "/": 
			result = op1 / op2;
			break;
			
		case "+":
			result = op1 + op2;
			break;
			
		case "-":
			result = op1 - op2;
			break;
			
		case "<":
			if( op1 < op2 ){
				result = 1;
			}
			else {
				result = 0;
			}
			break;
		case ">":
			if(op1 > op2) {
				result = 1; 
			}
			else {
				result = 0;
			}
			break;
		case "=":
			if(op1 == op2) {
				result = 1; 
			}
			else {
				result = 0;
			}
			break;
		case "^":
			result = Math.pow(op1, op2);
			break;
		case "&":
		case "|":
			result = logicalAndOrOperation(op1, op2, operator);
			break;
		case "%":
			result = op1 % op2;
			break;
		}
		String answer = "" + result;
		return answer;
	}
    
	public double logicalAndOrOperation( double op1, double op2, String operator) {
		if(operator.equals("&")) {
			if(op1 == 1 && op2 == 1 ){
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			if( op1 == 0 && op2 == 0) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}

	public String performNotOperation(String operand) {
		double op1 = Double.parseDouble(operand);
		if( op1 == 1) {
			return "0";
		}
		else {
			return "1";
		}	
	}
	public static void main(String[] args) {
		InfixCalculator test; 
		String inputFileName = args[0]; 
	    String outputFileName = args[1];
		String currentLine = null; 
		String output = ""; 
		MyQueue<String> outputQueue = new MyQueue<String>(); 

		try {
			FileReader fileReader = new FileReader(inputFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((currentLine = bufferedReader.readLine()) != null){ 
				test = new InfixCalculator(currentLine); 
				test.infixConvert(); 
				output = test.postfixEval(); 
				outputQueue.enqueue(output);
			}
			
			bufferedReader.close();
			
		}
		
		catch(FileNotFoundException ex) {
            System.out.println("Unable to open input file " + inputFileName);                
        }
		
        catch(IOException ex) {
            System.out.println("Error reading input file "+ inputFileName);                  
        }
		
	    try {
	        FileWriter fileWriter = new FileWriter(outputFileName);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	        while(outputQueue.isEmpty() == false){ 
	        	String current = outputQueue.dequeue();
	        	bufferedWriter.write(current);
	        	bufferedWriter.newLine(); 
	        }
	        bufferedWriter.close();
	    }
	    catch(IOException ex) {
	    	System.out.println("Error writing to file " + outputFileName);
	    }
    }
}