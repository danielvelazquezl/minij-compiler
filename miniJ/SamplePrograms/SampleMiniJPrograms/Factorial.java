//comentario 
public class Factorial{
    public static void main(String[] a){
    	Fac f = new Fac();
    	System.out.println(f.ComputeFac(10));
			System.out.println(10);
    }
}
/* 
Tambien comentarios
*/
public class Fac {

		public int zero() {
			return 2-3;
		}

    public int ComputeFac(int num) {
			int num_aux ;
			int a = num_aux.length;
			int b = 2;
			int [] arr;
			if (num < 1)
					num_aux = 1 ;
			else 
					num_aux = num * (this.ComputeFac(num-1)) ;
					num_aux = num * (2) ;
			return num_aux ;
    }

		public int mult(int mult, int m) {
			return 2 + 3;
		}

		public int div(int mult, int m, int d) {
			return 2 + 3;
		}

}
