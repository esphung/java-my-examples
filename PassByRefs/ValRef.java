// ValRef.java
// demonstrate passing by value or by reference;
// demonstrate 'boxing' ints into Integer types
public class ValRef {
	public static void main(String[] args) {
		new ValRef();
	} // end main

	public ValRef(){
		int a = 5;
		System.out.println("This is alpha (value) : \t" + a); // will print out global value (passed not by reference)
		addOne(a);
		addRef(new Integer(a));
	} // end constructor

//Converting an int to Integer is called boxing.
//Converting an Integer to int is called unboxing
	public void addRef(Integer alpha){
		int beta = alpha.intValue();
		beta++;
		System.out.println("This is Integer type : \t\t" + beta);
	} // end fun to convert int into Integer object

// alter alpha by reference
	public void addOne(int alpha){
		alpha++;
		System.out.println("This is alpha (by reference) : \t" + alpha);
	} // end function to pass by ref

} // end