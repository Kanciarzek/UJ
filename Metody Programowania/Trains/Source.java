// Paweł Goliszewski - gr. nr 4

import java.util.Scanner;

class Car {
	String name;
	Car next;
	Car prev;
	
	Car ( String name ){
		this.name = name;
	}
	
}

class Train {
	String name;
	Train next;
	Car first;
	Car last;
	
	Train ( String name, String carName ){
		this.name = name;
		this.first = new Car ( carName );
		this.last = this.first;
	}
	
	void delFirstCar (){
		
		Car tempCar;
		
		if ( first.prev == null ){
			tempCar = first.next;
		} else {
			tempCar = first.prev;
		}
		
		if (tempCar.prev == first){
		    tempCar.prev = null;
		} else {
			tempCar.next = null;
		}
		
		first = tempCar;
    }
	
	void delLastCar (){
		
		Car tempCar;	
		if ( last.prev == null ){
			tempCar = last.next;
		} else {
			tempCar = last.prev;
		}

		if ( tempCar.prev == last ){
            tempCar.prev = null;
        } else {
            tempCar.next = null;
        }
        last = tempCar;
	}
	
	void trainUnion ( Train trainTwo ){
		if ( last.next == null )
			last.next = trainTwo.first;
		else
			last.prev = trainTwo.first;
		
		if ( trainTwo.first.prev == null )
			trainTwo.first.prev = last;
		else
			trainTwo.first.next = last;
		
		last = trainTwo.last;
	}

	void reverseTrain (){
		Car tempCar = first;
		first = last;
		last = tempCar;
	}

	void displayTrain (){
		
		//String list = new String();
		Car curCar = first;
		Car lastCar = null;

		System.out.print( name + ':' );
		while ( curCar != null ){
			//list += " " + curCar.name;
			System.out.print( ' ' + curCar.name );
			if ( curCar.next == lastCar ){
				lastCar = curCar;
				curCar = curCar.prev;
			} else {
				lastCar = curCar;
				curCar = curCar.next;
			}
			
		}
		
		System.out.println();
	}
		
}

public class Source {

	static Scanner inp = new Scanner( System.in );
	
	static Train newTrain ( Train first, String trainName, String carName ){
		Train curTrain = new Train( trainName, carName );
		curTrain.next = first;
		curTrain.last = curTrain.first;
		return curTrain;
	}
	
	static void showList ( Train curTrain ){
		 //String list = new String();
		 System.out.print("Trains:");
		 while ( curTrain != null ){
			 //list += " " + curTrain.name;
			 System.out.print( ' ' + curTrain.name );
			 curTrain = curTrain.next;
		 }
		 System.out.println();
		 //System.out.println( "Trains:" + list );
	}
	
	static Train lookForTrain ( Train curTrain, String name ){
		while ( !curTrain.name.equals(name) ){
			curTrain = curTrain.next;
		}
		return curTrain;
	}
	
	static Train lookForPrevTrain ( Train curTrain, String name ){
		while ( !curTrain.next.name.equals(name) ){
			curTrain = curTrain.next;
		}
		return curTrain;
	}
	
	public static void main(String[] args) {
		int aOfData = inp.nextInt();
		int aOfOperations;
		inp.nextLine();
		//String operation;
		Train firstTrain = null;
		Train curTrain1 = null;
		Train curTrain2 = null;
		String name1, name2;
		Car curCar = null;
		
		while ( aOfData > 0 ){
			
			aOfOperations = inp.nextInt();
			inp.nextLine();

			while ( aOfOperations > 0 ){
				String operation = inp.next();

				switch ( operation ){
					
					case "New":
						firstTrain = newTrain ( firstTrain, inp.next(), inp.next() );
						break;
						
					case "InsertFirst":
						curTrain1 = lookForTrain ( firstTrain, inp.next() );
						curCar = new Car( inp.next() );
						curCar.next = curTrain1.first;
						curCar.prev = null;
						if ( curTrain1.first.next == null )
							curTrain1.first.next = curCar;
						else
							curTrain1.first.prev = curCar;
						curTrain1.first = curCar;
						break;
						
					case "InsertLast":
						curTrain1 = lookForTrain ( firstTrain, inp.next() );
						curCar = new Car( inp.next() );
						curCar.prev = curTrain1.last;
						curCar.next = null;
						if ( curTrain1.last.next == null )
							curTrain1.last.next = curCar;
						else
							curTrain1.last.prev = curCar;
						curTrain1.last = curCar;
						break;
						
					case "Display":
						curTrain1 = lookForTrain ( firstTrain, inp.next() );
						curTrain1.displayTrain();
						break;
					
					case "Reverse":
						curTrain1 = lookForTrain ( firstTrain, inp.next() );
						curTrain1.reverseTrain();
						break;
						
					case "TrainsList":
						showList ( firstTrain );
						break;
						
					case "Union":
						name1 = inp.next();
						name2 = inp.next();
						curTrain1 = firstTrain;
						curTrain2 = firstTrain;
						
						if ( !name2.equals( firstTrain.name ) ){
							/*while ( !curTrain1.name.equals(name1) || !curTrain2.next.name.equals(name2) ){
								if ( !curTrain1.name.equals(name1) )
									curTrain1 = curTrain1.next;
								if ( !curTrain2.next.name.equals(name2) )
									curTrain2 = curTrain2.next;
							}*/
							curTrain1 = lookForTrain ( firstTrain, name1 );
							curTrain2 = lookForPrevTrain ( firstTrain, name2 );
							curTrain1.trainUnion ( curTrain2.next );
							curTrain2.next = curTrain2.next.next;
						} else {
							curTrain1 = lookForTrain ( firstTrain, name1 );
							 curTrain1.trainUnion ( curTrain2 );
							firstTrain = firstTrain.next;
						}
						break;
						
					case "DelFirst":
						name1 = inp.next();
						if ( !firstTrain.name.equals( name1 ) ){
							curTrain1 = lookForPrevTrain ( firstTrain, name1 );
							if ( curTrain1.next.first == curTrain1.next.last ){
								firstTrain = newTrain ( firstTrain, inp.next(), curTrain1.next.first.name );
								curTrain1.next = curTrain1.next.next;
							}else{
								firstTrain = newTrain ( firstTrain, inp.next(), curTrain1.next.first.name );
								curTrain1.next.delFirstCar();
							}
						} else {
							//curTrain1 = firstTrain;
							if ( firstTrain.first == firstTrain.last ){
								firstTrain.name = inp.next();
							}else{
								name2 = firstTrain.first.name;
								firstTrain.delFirstCar ();
								firstTrain = newTrain ( firstTrain, inp.next(), name2 );
							}
							
						}
						break;
						
					case "DelLast":
						name1 = inp.next();
						if ( !firstTrain.name.equals( name1 ) ){
							curTrain1 = lookForPrevTrain ( firstTrain, name1 );
							if ( curTrain1.next.first == curTrain1.next.last ){
								firstTrain = newTrain ( firstTrain, inp.next(), curTrain1.next.last.name );
								curTrain1.next = curTrain1.next.next;
							}else{
								firstTrain = newTrain ( firstTrain, inp.next(), curTrain1.next.last.name );
								curTrain1.next.delLastCar();
							}
						} else {
							//curTrain1 = firstTrain;
							if ( firstTrain.first == firstTrain.last ){
								firstTrain.name = inp.next();
							}else{
								name2 = firstTrain.last.name;
								firstTrain.delLastCar();
								firstTrain = newTrain ( firstTrain, inp.next(), name2 );
							}
						}
						break;
						
					default:
						System.out.println("Nieznana operacja!");
				}

				aOfOperations--;
			}
			
			//System.out.print("\n");			
			firstTrain = null;
			curTrain1 = null;
			curTrain2 = null;
			curCar = null;
			
			aOfData--;
		}

	}

}
/* 
Test1:
1 
24 
New T1 W1 
InsertLast T1 W2 
Display T1 
InsertFirst T1 W0
Display T1 
DelFirst T1 T2 
Display T1 
Display T2 
DelLast T1 T3 
Display T1 
Display T3 
TrainsList 
New T4 Z1 
InsertLast T4 Z2 
Display T4
Reverse T4 
Display T4 
Union T3 T4 
Display T3 
TrainsList 
Union T3 T2 
Display T3 
Reverse T3 
Display T3

Wynik:
T1: W1 W2
T1: W0 W1 W2
T1: W1 W2
T2: W0
T1: W1
T3: W2
Trains: T3 T2 T1
T4: Z1 Z2
T4: Z2 Z1
T3: W2 Z2 Z1
Trains: T3 T2 T1
T3: W2 Z2 Z1 W0
T3: W0 Z1 Z2 W2

Test2:
1
26
New T1 W1
InsertFirst T1 W9
InsertFirst T1 W6
InsertLast T1 W999
Display T1
New T2 W0
InsertLast T2 W666
InsertLast T2 W777
Display T2
Reverse T2
InsertLast T2 W888
Display T2
New T3 W99
TrainsList
Reverse T3
Display T3
Union T1 T3
Display T2
DelLast T2 T4
Display T2
Display T1
Union T2 T1
Display T2
Reverse T2
Display T2
TrainsList

Wynik:
T1: W6 W9 W1 W999
T2: W0 W666 W777
T2: W777 W666 W0 W888
Trains: T3 T2 T1
T3: W99
T2: W777 W666 W0 W888
T2: W777 W666 W0
T1: W6 W9 W1 W999 W99
T2: W777 W666 W0 W6 W9 W1 W999 W99
T2: W99 W999 W1 W9 W6 W0 W666 W777
Trains: T4 T2
*/

