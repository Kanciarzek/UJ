
public class Cell {
	private boolean isLiving;
	
	public Cell(){
		isLiving = true;
	}
	
	public Cell ( boolean isLiving ){
		this.isLiving = isLiving;
	}
	
	public void kill(){
		isLiving = false;
	}
	
	public void makeLiving(){
		isLiving = true;
	}
	
	public boolean isLiving(){
		return isLiving;
	}
	
}
