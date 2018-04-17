//Pawe³ Goliszewski - gr. nr 4

import java.util.Scanner;
public class Source {
	static Scanner inp = new Scanner( System.in );
	
	public static class Stack {
		 
	    String[] tab;
	    int top;
	 
	    public Stack(int MaxSize) {
	        tab = new String[MaxSize];
	        top = 0;
	    }
	 
	    boolean isEmpty() {
	        if ( top == 0 ) {
	            return true;
	        } else
	            return false;
	    }
	 
	    void push ( String elem ){ 
	        if ( top < tab.length ) {
	            tab[top] = elem;
	            top++;
	        } else {
	        	System.out.println("Przepe³nienie stosu!");
	        }
	    }
	 
	    String pop() {
	        if ( top <= 0 ) {
	        	System.out.println("Stos jest pusty!");
	        	return "";
	        }
	        else{
	        	String temp = tab[top - 1];
	        	top--;
	        	return temp;
	        }
	    }
	    
	    String top() {
	        if ( top <= 0 ) {
	        	System.out.println("Stos jest pusty!");
	        	return "";
	        }
	        else{
	        	return tab[ top - 1 ];
	        }
	    }
	    int topInt() {
	        if ( top <= 0 ) {
	        	System.out.println("Stos jest pusty!");
	        	return 0;
	        }
	        else{
	        	return Integer.parseInt( tab[ top - 1 ] );
	        }
	    }

		public int popInt() {
	        if ( top <= 0 ) {
	        	System.out.println("Stos jest pusty!");
	        	return 0;
	        }
	        else{
	        	String temp = tab[top - 1];
	        	top--;
	        	return Integer.parseInt(temp);
	        }
		}
	}
	
	
    public static int getPriority ( char operator ){
        switch ( operator ) {
        case '(':
        case ')':
        	return -1;
        case '=':
            return 0;
        case '<':
        case '>':
            return 1;
        case '+':
        case '-':
            return 2;
        case '*':
        case '/':
        case '%':
            return 3;
        case '^':
            return 4;
        case '~':
            return 5;
        default:
            return 6;
        }
    }
    
	static String cleanInfExpr( String expr ){
		int i;
		expr = expr.substring( 5 );
		for ( i = 0; i < expr.length(); i++ ){
			if (!( expr.charAt(i) >= 'a' && expr.charAt(i) <= 'z' || expr.charAt(i) == '=' || expr.charAt(i) == '<' 
					|| expr.charAt(i) == '>' || expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' 
					|| expr.charAt(i) == '/' || expr.charAt(i) == '^' || expr.charAt(i) == '%' || expr.charAt(i) == '~' 
					|| expr.charAt(i) == '(' || expr.charAt(i) == ')')){
					expr = expr.substring ( 0, i ) + expr.substring ( i + 1 );
					i--;
			}
		}
		return expr;
	}
	
	static String cleanOnpExpr( String expr ){
		int i;
		expr = expr.substring( 5 );
		for ( i = 0; i < expr.length(); i++ ){
			if (!( expr.charAt(i) >= 'a' && expr.charAt(i) <= 'z' || expr.charAt(i) == '=' || expr.charAt(i) == '<' 
					|| expr.charAt(i) == '>' || expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' 
					|| expr.charAt(i) == '/' || expr.charAt(i) == '^' || expr.charAt(i) == '%' || expr.charAt(i) == '~' )){
					expr = expr.substring ( 0, i ) + expr.substring ( i + 1 );
					i--;
			}
		}
		return expr;
	}
    
	private static boolean isExprOnpOK ( String expr ) {
		
		int operCounter = 0;
		
		for ( int i = 0; i < expr.length(); i++ ){
			if ( expr.charAt(i) >= 'a' && expr.charAt(i) <= 'z' ){
				operCounter++;
			}else if ( expr.charAt(i) != '~' ){
				operCounter--;
			}
			if ( operCounter < 1 )
				return false;
		}
		
		if ( operCounter != 1 )
			return false;
		
		return true;
	}
	
	private static boolean isExprInfOK ( String expr ) {
		
        boolean isLastOperand = false;
        int counter = 0;
        int bracketCounter = 0;
        char curChar;
		
        for ( int i = 0; i < expr.length(); i++ ){
        	curChar = expr.charAt ( i );
            if ( curChar == '(' ){
                bracketCounter++;
            } else if ( 'a' <= curChar && curChar <= 'z' ){
                if ( isLastOperand ){
                    return false;
                }
                isLastOperand = true;
                counter++;
            }
            else if ( curChar == ')' ){
                bracketCounter--;
                isLastOperand = false;
                if ( bracketCounter < 0 ){
                    return false;
                }
            
            } else {       	
                isLastOperand = false;
                if (curChar != '~'){
                    counter--;
                    if (counter < 0 ){
                        return false;
                    }
                }
               }

        	if ( i != expr.length() - 1 ){
	            if ( expr.charAt ( i ) == '(' && expr.charAt( i + 1 ) == ')' )
	                return false;
	            if ( expr.charAt ( i ) == '(' && getPriority( expr.charAt( i + 1 ) ) > -1 && getPriority( expr.charAt( i + 1 ) ) < 5 )
	            	return false;
	            if ( expr.charAt( i ) == ')' && getPriority( expr.charAt( i + 1 ) ) == 6 )
	                return false;
	            if ( expr.charAt ( i ) == '~' && getPriority ( expr.charAt ( i + 1 ) ) >= 0 && getPriority( expr.charAt( i+1 ) ) < 5 )
	                return false;
        	}
        }
        
        if ( counter == 1 && bracketCounter == 0 )
        	return true;
        else
        	return false;
	}
	
	private static String onpToInf ( String expr ) {
		
		Stack stack = new Stack ( expr.length() );	
		Stack priorityStack = new Stack ( expr.length() );
		
		char curChar;
		String first;
		int firstPriority;
		String second;
		int secondPriority;
		for ( int i = 0; i < expr.length(); i++ ){
			curChar = expr.charAt(i);
			if ( curChar >= 'a' && curChar <= 'z' ){
				stack.push( String.valueOf( curChar ) );
				priorityStack.push( String.valueOf( getPriority( curChar ) ) );
			} else if ( curChar != '~' ) {

				first = stack.pop();
				firstPriority = priorityStack.popInt();
				second = stack.pop();
				secondPriority = priorityStack.popInt();
				if ( firstPriority <= getPriority( curChar ) )
					first = "(" + first + ")";
				
				if ( secondPriority < getPriority( curChar ) )
					stack.push( "(" + second + ")" + curChar + first );
				else
					stack.push( second + curChar + first );					
				
				priorityStack.push( String.valueOf( getPriority( curChar ) ) );
			}else{ // if ( curChar == '~' )
				first = stack.pop();
				firstPriority = priorityStack.popInt();
				if ( firstPriority <= getPriority(curChar) )
					stack.push( curChar + "(" + first + ")" );
				else
					stack.push( curChar + first );
				priorityStack.push( String.valueOf( getPriority( curChar ) ) );
			}
		}
		
		return stack.top();
	}


	private static String infToOnp ( String expr ) {
        String finalExpr = new String();
        Stack stack = new Stack( expr.length() );
        
        for ( int i = 0; i < expr.length(); i++ ){
        	char curChar = expr.charAt(i);
            if ( curChar == '(' ){
                stack.push( curChar+"" );
            }
            else if ( 'a' <= curChar && curChar <= 'z' ){

                finalExpr += curChar;
            }
            else if ( curChar == ')' ){
                
                while ( !stack.isEmpty() && !stack.top().equals( "(" ) ){
                    finalExpr += stack.pop();
                }
                
                if ( !stack.isEmpty() && stack.top().equals( "(" ) )
                	stack.pop();
            
            } else {
                
                while ( !stack.isEmpty() && getPriority ( stack.top().charAt(0) ) >= getPriority( curChar ) ){
                    if ( curChar == '~'  && stack.top().charAt(0) == '~' ){
                        break;
                    } else if ( curChar == '=' && stack.top().charAt(0) == '=' ){
                        break;
                    } else if ( curChar == '^' && stack.top().charAt(0) == '^' ){
                        break;
                    }

                   	finalExpr += stack.pop();
                }                        
                    stack.push ( curChar + "" );
            }
        }
        
        while ( !stack.isEmpty() ){
        	finalExpr += stack.pop();
        }
	
        return finalExpr;
	}
	
    public static void main (String[] args) {
    	
        int aOfOperations = inp.nextInt();
        inp.nextLine();
        
        while ( aOfOperations > 0 ){
            String expr = inp.nextLine();
            if ( expr.charAt(0) == 'I' ){
            	
                expr = cleanInfExpr( expr );
            
                if ( isExprInfOK ( expr ) ){
                    System.out.println( "ONP: " + infToOnp ( expr ) );
                } else {
                    System.out.println( "ONP: error" );
                }
            } else {
    			expr = cleanOnpExpr ( expr );
				if ( isExprOnpOK ( expr ) ){
					System.out.println ( "INF: " + onpToInf ( expr ) );
				} 
				else
					System.out.println ( "INF: error" );

            }
            aOfOperations--;
        }
    }   
} 

/*Test1:
10
ONP: oooooooooooo
ONP: xa~~=
INF: (a+~b)
INF: (a+b)c+
INF: a^b^c=x
INF: (a+b)^(b^c)
ONP: ab+bc^^
INF: a+()b*c
ONP: ccc+*df^-
ONP: abc**
Wynik:
INF: error
INF: x=~(~a)
ONP: ab~+
ONP: error
ONP: abc^^x=
ONP: ab+bc^^
INF: (a+b)^(b^c)
ONP: error
INF: c*(c+c)-d^f
INF: a*(b*c)
-----------
Test2:
12
INF: a^(b^(c^d))
ONP: abcd^^^
INF: ((a^b)^c)^d
ONP: ab^c^d^
INF: a^b^c^d
ONP: abcd^^^
INF: a+b+c+d
ONP: ab+c+d+
INF: a+(b+(c+d))
ONP: abcd+++
ONP: a(+b)
ONP: ab+~
Wynik:
ONP: abcd^^^
INF: a^(b^(c^d))
ONP: ab^c^d^
INF: a^b^c^d
ONP: abcd^^^
INF: a^(b^(c^d))
ONP: ab+c+d+
INF: a+b+c+d
ONP: abcd+++
INF: a+(b+(c+d))
INF: error
INF: ~(a+b)
--------------
Test3:
6
INF: a+(())b
INF: a(+(b))
INF: a(+b)
INF: (a+)+b
INF: (a+)b
INF: (a)+(b)
Wynik:
ONP: error
ONP: error
ONP: error
ONP: error
ONP: error
ONP: ab+
*/



