package sAMPLE;

public class Sample {
	int x=1;
	public void  method() {
		System.out.println(x);
		x++;
	}
	public static void main(String a[]) {
		Sample s= new Sample();
		s.method();
		s.method();
		s.method();
	}
}
