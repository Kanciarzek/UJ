import graphics.Picture;

import java.util.Random;

import graphics.Color;


public class Board {
	private Cell cell[][];
	private int boardHeigth;
	private int boardWidth;
	
	
	Board (int x, int y){
		cell = new Cell[ x ][ y ];
		boardWidth = x;
		boardHeigth = y;
		for ( int i = 0; i < boardWidth; i++ ){
			for ( int j = 0; j < boardHeigth; j++ ){
				cell[ i ][ j ] = new Cell( false );
			}
		} 

	}
	
	public void drawCells( Picture picture ){
		for ( int i = 0; i < boardWidth; i++ ){
			for ( int j = 0; j < boardHeigth; j++ ){
				if (!( cell[ i ][ j ].isLiving() ) ){
					picture.setColorAt(2*i, 2*j, Color.BLACK);
					picture.setColorAt(2*i+1, 2*j, Color.BLACK);
					picture.setColorAt(2*i, 2*j+1, Color.BLACK);
					picture.setColorAt(2*i+1, 2*j+1, Color.BLACK);
					
				}else{
					picture.setColorAt(2*i, 2*j, Color.GREEN);
					picture.setColorAt(2*i+1, 2*j, Color.GREEN);
					picture.setColorAt(2*i, 2*j+1, Color.GREEN);
					picture.setColorAt(2*i+1, 2*j+1, Color.GREEN);
					
				}
			}
		}
		picture.draw();
	}

	public void generateRandom() {

		Random random = new Random();
		for ( int i = 0; i < boardWidth; i++ ){
			for ( int j = 0; j < boardHeigth; j++ ){
				if ( random.nextInt( 50 ) > 1 )
					cell [ i ][ j ].kill();
				else
					cell [ i ][ j ].makeLiving();
			}
		}		
	}

	public void run(Picture picture) {
		int neighbours;
		for ( int i = 0; i < boardWidth; i++ ){
			for ( int j = 0; j < boardHeigth; j++ ){
				
				neighbours = 0;
				
				if ( i != 0 ) {
					if ( cell [ i - 1 ][ j ].isLiving() )
						neighbours++;
				}
				
				if ( i != 0 && j != 0 ){ 
					if ( cell [ i - 1 ][ j - 1 ].isLiving() )
						neighbours++;
				}
					
				if ( i < boardWidth - 1 ){ 
					if ( cell [ i + 1 ][ j ].isLiving() )
						neighbours++;
				}
					
				if ( i < boardWidth - 1 && j != 0 ){ 
					if ( cell [ i + 1 ][ j - 1 ].isLiving() )
						neighbours++;
				}
					
				if ( j != 0 ){
					if ( cell [ i ][ j - 1 ].isLiving() )
						neighbours++;
				}
					
				if ( j < boardHeigth - 1 ){
					if ( cell [ i ][ j + 1 ].isLiving() )
						neighbours++;
				}
				
				if ( i != 0 && j < boardHeigth - 1 ){
					if ( cell [ i - 1 ][ j + 1 ].isLiving() )
						neighbours++;
				}
					
				if ( i < boardWidth - 1 && j < boardHeigth - 1 ){
					if ( cell [ i + 1 ][ j + 1 ].isLiving() )
						neighbours++;
				}
				
				
				
				if ( cell [ i ][ j ].isLiving() ){
					if ( neighbours < 2 || neighbours > 3 )
						cell [ i ][ j ].kill();
				} else if ( neighbours == 3 )
					cell [ i ][ j ].makeLiving();
			}
		}	
		
		drawCells(picture);
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run( picture );
	}
	
}
