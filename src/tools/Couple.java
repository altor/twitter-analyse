package tools;

public class Couple<T1, T2> {

	private T1 one;
	private T2 two;
	
	public Couple(T1 one, T2 two) {
		this.one = one;
		this.two = two;
	}
	
	public T1 getOne(){
		return one;
	}
	
	public T2 getTwo(){
		return two;
	}

}
