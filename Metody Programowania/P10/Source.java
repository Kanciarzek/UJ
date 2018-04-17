//Pawe³ Goliszewski - gr. nr 4

import java.util.Scanner;


public class Source {

	static Scanner inp = new Scanner( System.in );
	
	static class Heap {
		int id;
		int tab[];
		int size;
		
		int val (){
			return tab[id];
		}
		
		int pop (){	
			return tab[id++];
		}
		
		boolean isEmpty(){
			return id == size;
		}
	}
	
	
	private static void mergeAndShowTables(int[][] data, int aOfTables, int[] tablesSize ) {
		Heap heap[] = new Heap [ aOfTables ];
		int entireSize = 0;
		for ( int i = 0; i < aOfTables; i++ ){
			heap[ i ] = new Heap();
			heap[ i ].id = 0;
			heap[ i ].tab = data[ i ];
			heap[ i ].size = tablesSize[ i ];
			entireSize += tablesSize [ i ];
			heapUp ( heap, i );
		}
		int result [] = new int [ entireSize ];
		for ( int i = 0; aOfTables > 0; i++ ){
			result [ i ] = heap [ 0 ].pop();
			if ( !heap[ 0 ].isEmpty() ){
				heapDown ( heap, 0, aOfTables );
			} else {
				swapHeap ( heap, 0, aOfTables - 1 );
				aOfTables--;
				heapDown ( heap, 0, aOfTables );
			}
		}
		for ( int i = 0; i < entireSize; i++ ){
			System.out.print( result [ i ] + " " );
		}
		System.out.print('\n');
	}
	
	private static void heapDown(Heap[] heap, int index, int aOfTables) {
		int i = index;
		while ( 2 * (i + 1) <= aOfTables ){
			if ( 2*( i + 1 ) == aOfTables ){
				if ( heap[ i ].val() > heap [ 2*( i + 1 ) - 1 ].val() ){
					swapHeap ( heap, 2*( i + 1 ) - 1, i );
				}
				return;
			}
			if ( heap[ 2*( i + 1 ) ].val() > heap [ 2*( i + 1 ) - 1 ].val() ){
				if ( heap[ i ].val() > heap [ 2*( i + 1 ) - 1 ].val() ){
					swapHeap ( heap, 2*( i + 1 ) - 1, i );
					i = 2*( i + 1 ) - 1;
				} else return;
			} else {
				if ( heap[ i ].val() > heap [ 2*( i + 1 ) ].val() ){
					swapHeap ( heap, 2*( i + 1 ), i );
					i = 2*( i + 1 );
				} else return;
			}
		}
	}

	private static void heapUp(Heap[] heap, int index) {
		int i = index;
		while ( true ){
			if ( heap[ ( i - 1 ) / 2 ].val() > heap[ i ].val() ){
				swapHeap ( heap, (i - 1) / 2, i );
				i = ( i - 1 ) / 2;
			} else return;
		}
	}

	private static void swapHeap(Heap[] heap, int i1, int i2) {
		int tempId = heap [ i1 ].id;
		int tempTab[] = heap [ i1 ].tab;
		int tempSize = heap [ i1 ].size;
		heap [ i1 ].id = heap [ i2 ].id;
		heap [ i1 ].tab = heap [ i2 ].tab;
		heap [ i1 ].size = heap [ i2 ].size;
		heap [ i2 ].id = tempId;
		heap [ i2 ].tab = tempTab;
		heap [ i2 ].size = tempSize;
	}

	public static void main(String[] args) {
		int aOfData = inp.nextInt();
		int aOfTables;
		int tablesSize[];
		
		while ( aOfData > 0 ){

			aOfTables = inp.nextInt();
			tablesSize = new int [ aOfTables ];
			int data[][] = new int[ aOfTables ][];
			for ( int i = 0; i < aOfTables; i++ ){
				tablesSize [ i ] = inp.nextInt();
				data[ i ] = new int [ tablesSize [ i ] ];

			}
			
			for ( int i = 0; i < aOfTables; i++ ){
				for ( int j = 0; j < tablesSize[ i ]; j++ ){
					data [ i ][ j ] = inp.nextInt();
				}
			}
			
			mergeAndShowTables ( data, aOfTables, tablesSize );
			
			aOfData--;
		}
	}
}

/*Testy:
4
3
6 4 10
1 3 7 9 9 90
0 2 7 10
3 3 3 3 3 3 3 3 3 9

3 
7 8 10 
4 4 4 4 4 4 4 
1 3 5 7 9 11 13 15 
1 2 3 4 5 6 7 8 9 10

1
1
5

4
5 5 5 1
1 2 3 4 5
2 4 7 7 7
0 1 1 7 9
-1
Wynik:
0 1 2 3 3 3 3 3 3 3 3 3 3 7 7 9 9 9 10 90 
1 1 2 3 3 4 4 4 4 4 4 4 4 5 5 6 7 7 8 9 9 10 11 13 15 
5 
-1 0 1 1 1 2 2 3 4 4 5 7 7 7 7 9 


 */
