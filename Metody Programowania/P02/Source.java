// Pawe³ Goliszewski - gr. 4

import java.util.Scanner;

public class Source {
	static Scanner inp = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int aOfTables = inp.nextInt();
		int size;
		long array[];
		int aOfLooking;
		long lookingArray[];
		int i;
		
		while ( aOfTables > 0 ){
			size = inp.nextInt();
			array = new long [ size ];
			i = 0;
			while ( i < size ){
				array [ i ] = inp.nextLong();
				i++;
			}
			aOfLooking = inp.nextInt();
			lookingArray = new long [ aOfLooking ];
			i = 0;
			while ( i < aOfLooking ){
				lookingArray [ i ] = inp.nextLong();
				i++;
			}
			
			i = 0;
			while ( i < aOfLooking ){
				System.out.print( "(" + amountOfValue ( array, lookingArray [ i ], size ) + " " + lookForIndex ( array, lookingArray [ i ], size ) + ")");
				i++;
			}
			System.out.print ( "\n" );
			size = removeDuplicates ( array, size );
			showArray ( array, size );
			aOfTables--;
		}
	}

	private static void showArray ( long[] array, int length ) {
		int i = 0;
		while ( i < length && i < 200 ){
			System.out.print ( array [ i ] + " " );
			if ( i % 50 == 49 )
				System.out.print ( "\n" );
			i++;
		}
		System.out.print ( "\n" );
	}

	private static int lookForIndex ( long[] array, long val, int length ) {
		int i;
		int end = length - 1;
		int beg = 0;
		
		if ( array [ beg ] == val && array [ end ] == val )
			return 0;
		
		while ( array [ beg ] <= val && array [ end ] >= val  ){
			i = beg + (int) ( ( ( val - array [ beg ] ) * ( end - beg ) ) * 1 / ( array [ end ] - array [ beg ] ));
			if ( val > array [ i ] )
				beg = i + 1;
			else if ( val < array [ i ] )
				end = i - 1;
			else
				return i;
		}
		return -1;
	}

	private static int amountOfValue ( long[] array, long val, int length ) {
		
		int iStart = 0, iEnd = -1;
		int end = length - 1;
		int beg = 0;
		
		if ( array [ 0 ] == val )
			iStart = 0;
		else {
			while ( beg <= end ){
				iStart = beg + (int) ( end - beg ) / 2;
				if ( val > array [ iStart ] )
					beg = iStart + 1;
				else if ( val < array [ iStart ] || array [ iStart - 1 ] == val )
					end = iStart - 1;
				else if ( val == array [ iStart ] )
					break;
				iStart = 0;
			}
		}
		
		end = length - 1;
		beg = 0;
		
		if ( array [ length - 1 ] == val )
			iEnd = length - 1;
		else{
			while ( beg <= end ){
				iEnd = beg + (int) ( end - beg ) / 2 ;
				if ( val > array [ iEnd ] || array [ iEnd + 1 ] == val )
					beg = iEnd + 1;
				else if ( val < array [ iEnd ]  )
					end = iEnd - 1;
				else if ( val == array [ iEnd ] )
					break;
				iEnd = -1;
			}
		}
		//System.out.print( iEnd + "||||" + iStart );
		return (iEnd - iStart + 1);
	}

	private static int removeDuplicates ( long[] array, int length ) {
		
		int i = 0;
		int curRemove = -1;
		int allRemove = 0;
		long curValue = array [ i ];
		
		while ( i < length ){	
			if ( array [ i ] == curValue ){
				curRemove++;
			} else {
				curValue = array [ i ];
				allRemove += curRemove;
				curRemove = 0;
				array [ i - allRemove ] = array [ i ];
			}
			i++;
		}
		return i - allRemove - curRemove;
	}
}
/*
Test1:
4 
12 
-1 1 2 2 2 3 5 5 7 7 9 9 
10 
1 2 3 -1 5 9 5 5 7 5
10 
1 2 2 2 2 2 3 3 3 3  
5 
1 2 3 4 0
10 
0 0 0 0 0 0 0 0 0 1 
4 
0 -1 1 2
10 
1 1 1 1 1 1 1 1 1 1 
3 
1 0 2
Wynik:
(1 1)(3 3)(1 5)(1 0)(2 6)(2 11)(2 6)(2 6)(2 8)(2 6)
-1 1 2 3 5 7 9 
(1 0)(5 4)(4 9)(0 -1)(0 -1)
1 2 3 
(9 0)(0 -1)(1 9)(0 -1)
0 1
(10 0)(0 -1)(0 -1)
1

----
Test2:
3 
12 
-1 1 2 2 2 3 5 5 7 7 9 9
3
1 2 7
55
1 2 3 4 5 6 7 8 9 10 11 12 13 14 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 54 54
5
14 44 -1 8 54
7
-2 -1 -1 -1 -1 -1 -1
4
-1 3 0 -2
Wynik:
(1 1)(3 3)(2 8)
-1 1 2 3 5 7 9 
(2 13)(1 44)(0 -1)(1 7)(2 54)
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 
51 52 54 
(6 6)(0 -1)(0 -1)(1 0)
-2 -1
*/