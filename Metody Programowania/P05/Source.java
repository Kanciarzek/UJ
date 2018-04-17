//Pawe³ Goliszewski - gr. nr 4

import java.util.Scanner;

public class Source {
	
	static Scanner inp = new Scanner( System.in );
	
	static long numInver( long array[], int first, int last ){
		
		if ( last - first > 1 ){
			long a = numInver ( array, first, (last + first) / 2 ) + numInver ( array, (last + first) / 2 + 1, last );
			long tempArray[] = new long [ last - first + 1 ];
			int i = first;
			while ( i <= last ){
				tempArray [ i - first ] = array [ i ];
				i++;
			}
			i = 0;
			int j = (last - first) / 2 + 1;
			int k = first;
			while ( i <= (last - first) / 2 && j <= last - first ){
				if ( tempArray [ i ] <= tempArray [ j ] ){
					array [ k ] = tempArray [ i ];
					i++;
				} else {
					array [ k ] = tempArray [ j ];
					a += (last - first) / 2 - i + 1;
					j++;
				}
				k++;
			}
			
			while ( i <= (last - first) / 2 ){
				array [ k ] = tempArray [ i ];
				i++;
				k++;
			}
			
			while ( j <= last - first ){
				array [ k ] = tempArray [ j ];
				j++;
				k++;
			}
			
			return a;
		} else {
			if ( array [ first ] > array [ last ] ){
				long temp = array [ first ];
				array [ first ] = array [ last ];
				array [ last ] = temp;
				return 1;
			}
			else
				return 0;
		}
	}
	
	public static void main(String[] args) {
		int aOfArrays;
		int size;
		long array[];
		
		aOfArrays = inp.nextInt();
		while ( aOfArrays > 0 ){
			
			size = inp.nextInt();
			array = new long [ size ];
			int i = 0;
			
			while ( i < size ){
				array[ i ] = inp.nextInt();
				i++;
			}
			System.out.println ( numInver ( array, 0, size - 1 ) );
			aOfArrays--;
		}
	}

}

/*
Test:
5
5
0 0 0 0 0
10
10 9 8 7 6 5 4 3 2 1
6
8 9 0 1 1 7
4
1 2 0 4
5
1 2 3 4 5
Wynik:
0
45
8
4
0


*
*/
