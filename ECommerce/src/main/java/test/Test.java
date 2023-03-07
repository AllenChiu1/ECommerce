package test;

import java.util.Scanner;

public class Test {

	   public static void main(String[] args) {
	        
		   Scanner sc = new Scanner(System.in);
		   int input = sc.nextInt();
		   int i = 1;
		   int j = 9;
		   int k;
		   
		   for(k = 1; k <= 9; k++) {
			   if(k % 2 != 0) {
				   System.out.println(input + " * " + i + " = " + input * i);
				   i++;
			   }else {
				   System.out.println(input + " * " + j + " = " + input * j);
				   j--;
			   };
		   };
		   
	   }
}
