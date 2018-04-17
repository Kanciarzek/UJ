//Pawe³ Goliszewski - gr. nr 4

import java.util.Scanner;


public class Source {

    static Scanner inp = new Scanner(System.in);
	
	static class Node { 
		   public int info; 
		   public Node left;    
		   public Node right;    
		                       
		   public Node(int info) { 
		        this.info = info; 
		        left = null; 
		        right = null; 
		   } 
	}
	
	static class NodeStack {
		public Node nodes[];
		public int top;
		
		public NodeStack( int maxsize ){
			nodes = new Node[maxsize];
			top = 0;
		}
		
	    boolean isEmpty() {
	        if (top == 0) {
	            return true;
	        } else
	            return false;
	    }
	    
	    void push ( Node node ){
	        if (top < nodes.length) {
	            nodes[top] = node;
	            top++;
	        } else {
	        	System.out.println("Przepe³nienie stosu!");
	        }
	    }
	    
	    Node pop () {
	        if (top <= 0) {
	        	System.out.println("Stos jest pusty!");
	        	return null;
	        } else {
	        	top--;
	        	return nodes [top];
	        }
	    }
	}
	
	
	private static Node makeBSTFromPostorder(int[] nodes, int startIndex, int endIndex) {
		Node cur = new Node( nodes[endIndex] );
		int lowerIndex = endIndex;
		while ( lowerIndex >= startIndex && nodes[endIndex] <= nodes [lowerIndex] )
			lowerIndex--;
		if ( endIndex != lowerIndex + 1 )
			cur.right = makeBSTFromPostorder ( nodes, lowerIndex + 1, endIndex - 1 );
		if ( startIndex != lowerIndex + 1 )
			cur.left = makeBSTFromPostorder ( nodes, startIndex, lowerIndex );

		
		return cur;
	}

	private static Node makeBSTFromPreorder(int[] nodes, int startIndex, int endIndex) {
		Node cur = new Node( nodes[startIndex] );
		int greaterIndex = startIndex;

		while ( greaterIndex <= endIndex && nodes[startIndex] >= nodes [greaterIndex] )
			greaterIndex++;

		if ( startIndex + 1 != greaterIndex )
			cur.left = makeBSTFromPreorder ( nodes, startIndex + 1, greaterIndex - 1 );
		if ( endIndex != greaterIndex - 1 )
			cur.right = makeBSTFromPreorder ( nodes, greaterIndex, endIndex );
		return cur;
	}
	
	public static void showPreorder ( Node first, int size ){

		Node cur;
		NodeStack stack = new NodeStack(size);
		stack.push(first);
		while ( !stack.isEmpty() ){
			cur = stack.pop();
			System.out.print( cur.info + " " );
			
			if ( cur.right != null )
				stack.push(cur.right);
			if ( cur.left != null )
				stack.push(cur.left);
		}
		System.out.print('\n');
	}
	
	public static void showInorder ( Node first, int size ){

		Node cur = first;
		NodeStack stack = new NodeStack(size);
		while ( !stack.isEmpty() || cur != null ){

			if ( cur != null ){
				stack.push( cur );			
				cur = cur.left;
			} else {
				cur = stack.pop();
				System.out.print( cur.info + " " );
				cur = cur.right;
			}
		}
		System.out.print('\n');
	}
	
	public static void showPostorder ( Node first, int size ){

		Node cur;
		NodeStack stack1 = new NodeStack(size);
		NodeStack stack2 = new NodeStack(size);
		stack1.push(first);
		while ( !stack1.isEmpty() ){
			cur = stack1.pop();
			stack2.push(cur);
			
			if ( cur.left != null )
				stack1.push(cur.left);
			if ( cur.right != null )
				stack1.push(cur.right);
		}
		while ( !stack2.isEmpty() ){
			cur = stack2.pop();
			System.out.print(cur.info + " ");
		}
		System.out.print('\n');
	}
	
	public static void showLevelorder ( Node first, int size ){
		Node queue[] = new Node [ size ];
		queue [ 0 ] = first;
		int start = 0;
		int end = 1;
		Node cur = first;
		while ( start != end ){
			cur = queue[start];
			start++;
			System.out.print(cur.info + " ");
			if ( cur.left != null ){
				queue[end] = cur.left;
				end++;
			}
			if ( cur.right != null ){
				queue[end] = cur.right;
				end++;
			}		
		}
		System.out.print('\n');
	}
	
	
	public static String parent ( Node first, int key ){
		Node cur = parentNode ( first, key );
		if ( cur != null ){
			return Integer.toString( cur.info );
		} else {
			return "BRAK";
		}
	}
	
	public static Node parentNode ( Node first, int key ){
		Node cur = first;
		while ( cur != null ){
			if ( (cur.left != null && cur.left.info == key) || (cur.right != null && cur.right.info == key) )
				return cur;
			if ( key > cur.info )
				cur = cur.right;
			else
				cur = cur.left;
		}
			return null;
	}
	
	public static void insert ( Node first, int val ){
		Node temp = new Node( val );
		Node cur = first;
		while ( true ){
			if ( val > cur.info ){
				if ( cur.right == null ){
					cur.right = temp;
					return;
				} else {
					cur = cur.right;
				}
			} else {
				if ( cur.info == val )
					return;
				if ( cur.left == null ){
					cur.left = temp;
					return;
				} else {
					cur = cur.left;
				}
			}
		}
	}
	
	public static Node delete(Node first, int val) {
		Node cur;
		Node tempFirst = first;
		Node preFirst = null;
		while ( true ){
		if ( tempFirst.info == val ){
			if ( tempFirst.left == null ){
				if ( preFirst == null ){
					tempFirst.info = tempFirst.right.info;
					tempFirst.left = tempFirst.right.left;
					tempFirst.right = tempFirst.right.right;
				} else {
					preFirst.right = tempFirst.right;
				}
				break;
			} else if ( tempFirst.right == null ) {
				if ( preFirst == null ){
					tempFirst.info = tempFirst.left.info;
					tempFirst.right = tempFirst.left.right;
					tempFirst.left = tempFirst.left.left;
				} else {
					preFirst.right = tempFirst.left;
				}
				break;
			} else {				
				Node temp = successorNode(tempFirst,val);
				tempFirst.info = temp.info;
				temp.info = val;					
				preFirst = tempFirst;
				tempFirst = tempFirst.right;
			}
		} else {
			cur = parentNode ( tempFirst, val );
			if ( cur.left != null && cur.left.info == val ){
				if ( cur.left.left == null ){
					cur.left = cur.left.right;
					break;
				} else if ( cur.left.right == null ) {
					cur.left = cur.left.left;
					break;
				} else {
					Node temp = successorNode(tempFirst,val);
					cur.left.info = temp.info;
					temp.info = val;
					preFirst = cur.left;
					tempFirst = cur.left.right;
				}
			} else {
				if ( cur.right.left == null ){
					cur.right = cur.right.right;
					break;
				} else if ( cur.right.right == null ) {
					cur.right = cur.right.left;
					break;
				} else {
					Node temp = successorNode(tempFirst,val);
					cur.right.info = temp.info;
					temp.info = val;
					preFirst = cur.right;
					tempFirst = cur.right.right;
				}
			}
		}
		}
		return first;
	}
	
	private static String predecessor(Node first, int val) {
		Node cur = first;
		while ( cur.info != val ){
			if ( val > cur.info )
				cur = cur.right;
			else
				cur = cur.left;
		}
		if ( cur == null )
			return "BRAK";
		if ( cur.left != null ){
			cur = cur.left;
			while ( cur.right != null )
				cur = cur.right;
			return Integer.toString( cur.info );
		} else {
			Node temp = parentNode( first, val );
			while ( temp != null && temp.right != cur ){
				cur = temp;
				temp = parentNode ( first, cur.info );
			}
			if (temp == null)
				return "BRAK";
			else
				return Integer.toString( temp.info );
		}
	}
	
	private static Node successorNode( Node first, int val ){
		Node cur = first;
		while ( cur.info != val ){
			if ( val > cur.info )
				cur = cur.right;
			else
				cur = cur.left;
		}
		if ( cur == null )
			return null;
		if ( cur.right != null ){
			cur = cur.right;
			while ( cur.left != null )
				cur = cur.left;
			return cur;
		} else {
			Node temp = parentNode( first, val );
			while ( temp != null && temp.left != cur ){
				cur = temp;
				temp = parentNode ( first, cur.info );
			}
			return temp;
		}
	}

	private static String successor(Node first, int val) {
		Node cur = successorNode ( first, val );
		if ( cur == null )
			return "BRAK";
		else
			return Integer.toString( cur.info );
	}
	
	public static void main(String[] args) {
		int aOfData;
		int nOfData = 1;
		int nOfNodes;
		int nodes[];
		String operation;
		int operationData;
		int nOfOperations;
		Node first;
		aOfData = inp.nextInt();
		while ( nOfData <= aOfData ){
			System.out.println("ZESTAW: " + nOfData);
			nOfNodes = inp.nextInt();
			nodes = new int [ nOfNodes ];
			operation = inp.next();
			for ( int i = 0; i < nOfNodes; i++ )
				nodes [ i ] = inp.nextInt();
			if ( operation.charAt(1) == 'R' )
				first = makeBSTFromPreorder ( nodes, 0, nOfNodes - 1 );
			else
				first = makeBSTFromPostorder ( nodes, 0, nOfNodes - 1 );
			nOfOperations = inp.nextInt();
			while ( nOfOperations > 0 ){
				operation = inp.next();
				switch ( operation.charAt(1) ){
					case 'R':
						if ( operation.charAt(3) == 'O' ){
							System.out.println("PREORDER:");
							showPreorder(first, nOfNodes);
						} else {
							operationData = inp.nextInt();
							System.out.println("PREDECESSOR " + operationData + ": " + predecessor( first, operationData ));
						}
						break;
					case 'N':
						if ( operation.charAt(2) == 'O' ){
							System.out.println("INORDER:");
							showInorder(first, nOfNodes);
						} else { //INSERT
							operationData = inp.nextInt();
							insert (first, operationData);
							nOfNodes++;
						}
						break;
					case 'O':
						System.out.println("POSTORDER:");
						showPostorder(first, nOfNodes);
						break;
					case 'E':
						if ( operation.charAt(2) == 'V' ){
							System.out.println("LEVELORDER:");
							showLevelorder(first, nOfNodes);
						} else {
							operationData = inp.nextInt();
							first = delete(first, operationData);
						}
						break;
					case 'A':
						operationData = inp.nextInt();
						System.out.println("PARENT " + operationData + ": " + parent(first, operationData) );
						break;
					case 'U':
						operationData = inp.nextInt();
						System.out.println("SUCCESSOR " + operationData + ": " + successor(first, operationData) );
				}
					
				
				nOfOperations--;
			}
			nOfData++;
		}
	}
}

/* 
Test:
2 
10 
PREORDER  
50 25 12 37 30 33 43 75 87 93 
20 
PREORDER
INORDER
POSTORDER 
LEVELORDER 
PARENT 33
PARENT 25
PARENT 12
PARENT 37
PARENT 30 
SUCCESSOR  50 
PREDECESSOR 50 
PARENT 50 
DELETE  50 
PARENT 50 
POSTORDER 
LEVELORDER 
INSERT 35 
LEVELORDER
DELETE 93
LEVELORDER
10
POSTORDER
12 33 30 43 37 25 93 87 75 50
8
PARENT 33
POSTORDER
INORDER
PREORDER
LEVELORDER
DELETE 25
DELETE 37
LEVELORDER



Wynik:
ZESTAW: 1
PREORDER:
50 25 12 37 30 33 43 75 87 93 
INORDER:
12 25 30 33 37 43 50 75 87 93 
POSTORDER:
12 33 30 43 37 25 93 87 75 50 
LEVELORDER:
50 25 75 12 37 87 30 43 93 33 
PARENT 33: 30
PARENT 25: 50
PARENT 12: 25
PARENT 37: 25
PARENT 30: 37
SUCCESSOR 50: 75
PREDECESSOR 50: 43
PARENT 50: BRAK
PARENT 50: BRAK
POSTORDER:
12 33 30 43 37 25 93 87 75 
LEVELORDER:
75 25 87 12 37 93 30 43 33 
LEVELORDER:
75 25 87 12 37 93 30 43 33 35 
LEVELORDER:
75 25 87 12 37 30 43 33 35 
ZESTAW: 2
PARENT 33: 30
POSTORDER:
12 33 30 43 37 25 93 87 75 50 
INORDER:
12 25 30 33 37 43 50 75 87 93 
PREORDER:
50 25 12 37 30 33 43 75 87 93 
LEVELORDER:
50 25 75 12 37 87 30 43 93 33 
LEVELORDER:
50 30 75 12 43 87 33 93 



 */

