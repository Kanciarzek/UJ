//Paweł Goliszewski - gr. nr 4

import java.util.Scanner;

public class Source {

    static Scanner inp = new Scanner(System.in);
	
	private static int lookForNMinNumber ( int[] array, int beg, int length, int n ) {
		if ( length < 50 ){
			return sortAndLookForNMinNumber ( array, beg, length, n );
		} else {
			int q[] = new int[ length / 5 + 1 ];
			int pivot;
			for ( int i = 0; i < length / 5; i++ ){
				insertionSort ( array, 5 * i + beg, 5 );
				q [ i ] = array [ 5 * i + 2 + beg ];
			}
			if ( length % 5 != 0 ){
				insertionSort ( array, beg + length - length % 5, length % 5 );
				q [ length / 5 ] = array [ length - 1 ];
				pivot = lookForNMinNumber ( q, 0, length / 5 + 1, length / 10 + 1 );
			} else {
				pivot = lookForNMinNumber ( q, 0, length / 5 + 1, length / 10 + 1 );
			}
			int j = beg;
			for ( int i = beg; i < beg + length; i++ ){
				if ( array [ i ] < pivot ){
					swap ( array, i, j );
					j++;
				}
			}
			if ( n <= j - beg ){
				return lookForNMinNumber ( array, beg, j - beg, n );
			}
			for ( int i = j; i < beg + length; i++ ){
				if ( array [ i ] == pivot ){
					swap ( array, i, j );
					j++;
				}
			}
			if ( n <= j - beg ){
				return pivot;
			} else {
				return lookForNMinNumber ( array, j, length - j + beg, n - j + beg );
			}	
		}
	}
    
	private static void swap ( int[] array, int n1, int n2 ){
		if ( n1 != n2 ){
			int temp = array [ n1 ];
			array [ n1 ] = array [ n2 ];
			array [ n2 ] = temp;
		}
	}

	private static int sortAndLookForNMinNumber ( int[] array, int beg, int length, int n) {
		insertionSort ( array, beg, length );
		return array [ n + beg - 1 ];
	}
	
	private static void insertionSort ( int[] array, int beg, int length) {
		for ( int i = beg; i < length + beg - 1; i++ ){
            int index = i;
            for (int j = i + 1; j < length + beg; j++)
                if (array[j] < array[index])
                    index = j;
            swap ( array, index, i );
		}

	}
	
	public static void main(String[] args) {
		
		int size, size2;
		int array[];
		int nToFind[];
		int aOfData = inp.nextInt();
		while ( aOfData > 0 ){
			
			size = inp.nextInt();
			array = new int [ size ];
			for ( int i = 0; i < size; i++ ){
				array [ i ] = inp.nextInt();
			}
			
			size2 = inp.nextInt();
			nToFind = new int [ size2 ];
			for ( int i = 0; i < size2; i++ ){
				nToFind [ i ] = inp.nextInt();
			}

			
			for ( int i = 0; i < size2; i++ ){
				if ( nToFind [ i ] > size || nToFind [ i ] < 1 ){
					System.out.println( nToFind[ i ] + " brak");
				} else {
					int number = lookForNMinNumber ( array, 0, size, nToFind [ i ] );
					System.out.println( nToFind[ i ] + " " + number );
				}
			}
			aOfData--;
		}
		
	}
}

/*
Test:
4
30
1 2 3 4 5 10 9 8 7 6 11 12 13 14 15 20 19 18 17 16 21 22 23 24 25 30 29 28 27 26
7
3 5 10 15 17 11 2
30
26 27 28 29 30 25 24 23 22 21 16 17 18 19 20 15 14 13 12 11 6 7 8 9 10 5 4 3 2 1
7
3 5 10 15 -1 31 30
60
26 27 28 29 30 25 24 23 22 21 16 17 18 19 20 15 14 13 12 11 6 7 8 9 10 5 4 3 2 10 26 27 28 29 30 25 24 23 22 21 16 17 18 19 20 15 14 13 12 11 6 7 8 9 10 5 4 3 2 10 
7
1 60 9 10 11 30 5
60
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 2 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
5
8 0 1 60 57 
Wynik:
3 3
5 5
10 10
15 15
17 17
11 11
2 2
3 3
5 5
10 10
15 15
-1 brak
31 brak
30 30
1 2
60 30
9 6
10 6
11 7
30 15
5 4
8 1
0 brak
1 1
60 2
57 1



 */


