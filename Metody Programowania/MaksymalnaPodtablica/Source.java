// Pawe³ Goliszewski - nr 4
import java.util.Scanner;

class Source { 
    public static Scanner in = new Scanner(System.in); 
    public static void main( String [] args ) {
    	
    	int aOfTables, m, n;
    	int curUp, curDown, curLeft, curRigth, curTempLeft;
    	int maxSum, curSum;
    	int maxUp, maxDown, maxLeft, maxRigth;
    	int tab[][];
    	int subSumColumn[]; 
    	int maxColumnSum;
    	aOfTables = in.nextInt();
    	while ( aOfTables > 0 ){
			m = in.nextInt();
			n = in.nextInt();
			tab = new int [ m ][ n ];

			for ( int i = 0; i < m; i++ ){
				for ( int j = 0; j < n; j++ )
					tab [ i ][ j ] = in.nextInt();
			}
			
			subSumColumn = new int [ n ];
			maxColumnSum = -1;
			maxSum = -1;
			curSum = 0;
	    	maxUp = 0; maxDown = 0; maxLeft = 0; maxRigth = 0;
	    	curUp = 0; curDown = 0; curLeft = 0; curRigth = 0;
	    	curTempLeft = 0;
			for ( int j = 0; j < m; j++ ){
				curUp = j;
				for ( int k = 0; k < n; k++ ){
					subSumColumn [ k ] = 0;
				}

				for ( int i = j; i < m; i++ ){
					
					curDown = i;
					
					for ( int k = 0; k < n; k++ ){
						subSumColumn [ k ] += tab [ curDown ][ k ];
					}	
					
					maxColumnSum = -1;

					
					for ( int l = 0; l < n; l++ ){
						curSum = 0;
						curTempLeft = l;
						for ( int k = l; k < n; k++ ){
							curSum += subSumColumn [ k ];
							
							if ( curSum < 0 ){
								curSum = 0;
								curTempLeft = k + 1;
							} else if ( curSum > maxColumnSum || ( curSum == maxColumnSum && ( k - curTempLeft < curRigth - curLeft ) ) ){
								maxColumnSum = curSum;
								curLeft = curTempLeft;
								curRigth = k;
							}
						}
					}
					
					
					if ( maxColumnSum > maxSum || ( maxColumnSum == maxSum && ( maxDown - maxUp + 1 ) * ( maxRigth - maxLeft + 1 ) > ( curDown - curUp + 1 ) * ( curRigth - curLeft + 1 ) ) ){
						maxSum = maxColumnSum;
						maxUp = curUp;
						maxDown = curDown;
						maxLeft = curLeft;
						maxRigth = curRigth;
					}

				}
			}
			
			if ( maxSum > -1 ){
				System.out.println( "max_sum=" + maxSum );
				System.out.println( "[" + maxUp + ".." + maxDown + ", " + maxLeft + ".." + maxRigth + "]" );
			} else
				System.out.println( 0 );
			
			aOfTables--;
    	}
    }
}
/*
Dla wejœcia:
8 

2 5 
1  1 -1 -1  0  
1  1 -1 -1  4

2 5 
0  -1 -1  1  1 
4  -2 -2  1  1 

2 5 
0  -1 -1  4  0 
4  -2 -2  0  0 

2 5 
-1 -2 -3 -1 -2  
-1 -1 -1 -1 -5 
 
2 5 
0  0  0  0  0  
0  0  0  0  0 
 
3 6 
0  0  0 -2  1  1
0  1  1 -2  1  1
0  1  1 -2  0  0 

4 8 
-1  10  -3  5  -4 -8  3 -3 
8  -2  -6  -8  2 -5  4  1 
3  -2  9  -9  -1 10 -5  2 
1  -3  5  -7  8  -2  2 -6
 
2 2 
-1  0
0 -1 

Wynik:
max_sum=4
[1..1, 4..4]
max_sum=4
[1..1, 0..0]
max_sum=4
[0..0, 3..3]
0
max_sum=0
[0..0, 0..0]
max_sum=4
[0..1, 4..5]
max_sum=19
[0..3, 0..2]
max_sum=0
[0..0, 1..1]

Dla wejœcia:
9 

3 3 
0 0 0
0 1 -1
0 0 1

2 3 
-1 0 -1
-1 0 -2

1 2
0 7

3 3 
2 2 0
0 0 0
0 -1 5

2 5 
-1  -1  0  5  0  
0  0  1  -1  0  

2 2 
6 6
6 -6

4 5 
2 1 -3 -4 5
0 6 3 4 1 
2 -2 -1 4 -5
-3 3 1 0 3

2 3 
-1  1 -1
1 -1 1

7 1
0
0
4
-4
2
2
0

Wynik:
max_sum=1
[1..1, 1..1]
max_sum=0
[0..0, 1..1]
max_sum=7
[0..0, 1..1]
max_sum=8
[0..2, 0..2]
max_sum=5
[0..0, 3..3]
max_sum=12
[0..0, 0..1]
max_sum=18
[1..3, 1..3]
max_sum=1
[0..0, 1..1]
max_sum=4
[2..2, 0..0]


 */


