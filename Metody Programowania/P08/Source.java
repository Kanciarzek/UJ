//Pawe³ Goliszewski - gr. nr 4

import java.util.Scanner;

public class Source {

    static Scanner inp = new Scanner(System.in);
	
    static String toPostorder( int tab1[], int beg1, int tab2[], int beg2, int length ){
    	String a = "";
    	if ( length > 1 ){
	    	int i = beg2;
	    	while ( tab1[ beg1 ] != tab2[ i ] ){
	    		i++;
	    	}
	    	int firstPartLength = i - beg2;
	    	if ( firstPartLength > 0 )
	    		a += toPostorder ( tab1, beg1 + 1, tab2, beg2, firstPartLength );
	    	int secondPartLength = length - firstPartLength - 1;
	    	if ( secondPartLength > 0 )
	    		a += toPostorder ( tab1, beg1 + firstPartLength + 1, tab2, i + 1, secondPartLength );
    	}
    	return a + tab1 [ beg1 ] + ' ';
    }
    
    static String toPreorder( int tab1[], int beg1, int tab2[], int beg2, int length ){
    	String a = tab1 [ beg1 + length - 1 ] + " ";
    	if ( length > 1 ){
	    	int i = beg2;
	    	int end1val = tab1[ beg1 + length - 1 ];
	    	while ( end1val != tab2[ i ] ){
	    		i++;
	    	}
	    	int firstPartLength = i - beg2;
	    	if ( firstPartLength > 0 )
	    		a += toPreorder ( tab1, beg1, tab2, beg2, firstPartLength );
	    	int secondPartLength = length - firstPartLength - 1;
	    	if ( secondPartLength > 0 )
	    		a += toPreorder ( tab1, beg1 + firstPartLength, tab2, i + 1, secondPartLength );
    	}
    	return a;
    }
    
	public static void main(String[] args) {
		
		int size;
		int aOfData = inp.nextInt();
		int tab1[];
		int tab2[];
		String a;
		while ( aOfData > 0 ){
			size = inp.nextInt();
			tab1 = new int [ size ];
			tab2 = new int [ size ];
			a = inp.next();
			for ( int i = 0; i < size; i++ )
				tab1 [ i ] = inp.nextInt();
			inp.next();
			for ( int i = 0; i < size; i++ )
				tab2 [ i ] = inp.nextInt();		
			if ( a.charAt(1) == 'R' )
				System.out.print( toPostorder ( tab1, 0, tab2, 0, size ) + '\n' );
			else
				System.out.print( toPreorder ( tab1, 0, tab2, 0, size ) + '\n' );
			aOfData--;
		}

	}

}
/*Testy:
5
2
PREORDER 
2 1
INORDER
2 1
3
POSTORDER
3 1 2
INORDER
3 2 1
8
PREORDER
1 2 5 3 7 10 11 12
INORDER
2 1 3 11 10 12 7 5
8
POSTORDER
2 11 12 10 7 3 5 1
INORDER
2 1 3 11 10 12 7 5
11
POSTORDER
8 4 9 5 2 6 10 11 7 3 1
INORDER
8 4 2 5 9 1 6 3 10 7 11
Wynik:
1 2 
2 3 1 
2 11 12 10 7 3 5 1 
1 2 5 3 7 10 11 12 
1 2 4 8 5 9 3 6 7 10 11
*/

