
//Christian Garcia
//ID 1312181
//cruzID: chalgarc
//April 8, 2015
// pa1- Extrema

class Extrema {
   static int max;
   static int min;
   // maxArray()
   // returns the largest value in int array A
   static int maxArray(int[] A, int p, int r){
      int q;

      if(p < r) {
         q = (p+r)/2;
	 maxArray(A, p, q);
         maxArray(A, q+1, r);
	 max(A, p, q, r);
	}
	return max;
   }
   static void max(int[] A, int p, int q, int r){
		
		if (p+1==r&&(A[p]>max)||(A[r]>max)){
			if(A[p]>A[r]&&A[p]>max){
				max = A[p];
				}
			else max=A[r];
		}
		}
   static void min(int[] A, int p, int q, int r){
		
		if (p+1==r&&(A[p]<min)||(A[r]<min)){
			if(A[p]<A[r]&&A[p]<min){
				min = A[p];
				}
			else min=A[r];
		}
		}
	

		
   // minArray()
   // returns the smallest value in int array A
   static int minArray(int[] A, int p, int r){
      int q;

      if(p < r) {
         q = (p+r)/2;
	 minArray(A, p, q);
         minArray(A, q+1, r);
	 min(A, p, q, r);
	}
	return min;
   }
   
   // main()
   public static void main(String[] args){
      int[] B = {-1, 2, 6, 3, 9, 2, -3, -2, 11, 5, 7};
      
      System.out.println( "max = " + maxArray(B, 0, B.length-1));  // output: max = 11
      System.out.println( "min = " + minArray(B, 0, B.length-1));  // output: min = -3
   }
   
}
