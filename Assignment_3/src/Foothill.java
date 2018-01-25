/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 3 - Timing Matrix Multiplication
 	This program is initializes a 10 x 10 matrix
 	and performs a matrix multiplication. It will
 	then output a new matrix based on the result
 	of the multiplication. It will also calculate 
 	the time of the multiplication.
 */
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

//------------------------------------------------------
public class Foothill {
	final static int MAT_SIZE = 4000;

	// ------- proof of correctness --------------
	public static void main(String[] args) throws Exception {
		int r, randRow, randCol;
		long startTime, stopTime;
		double randFrac;
		double smallPercent;
		NumberFormat tidy = NumberFormat.getInstance(Locale.US);
		tidy.setMaximumFractionDigits(4);

		// non-sparse matrices
		double[][] mat, matAns;

		// allocate matrices
		mat = new double[MAT_SIZE][MAT_SIZE];
		matAns = new double[MAT_SIZE][MAT_SIZE];

		// generate small% of non-default values bet 0 and 1
		smallPercent = MAT_SIZE / 10. * MAT_SIZE;

		Random rand = new Random();
		for (r = 0; r < smallPercent; r++) {
			randRow = rand.nextInt(MAT_SIZE);
			randCol = rand.nextInt(MAT_SIZE);
			randFrac = rand.nextDouble();
			mat[randRow][randCol] = randFrac;
		}

		// 10x10 submatrix in lower right
		matShow(mat, MAT_SIZE - 10, 10);

		System.out.println();

		startTime = System.nanoTime();
		matMult(mat, mat, matAns);
		stopTime = System.nanoTime();

		matShow(matAns, MAT_SIZE - 10, 10);

		System.out.println("\nSize = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
				+ tidy.format((stopTime - startTime) / 1e9) + " seconds.");
	}

	// this method loops through every row and column of the matrix
	// and displays it. shows a square sub-matrix of size = size
	// anchored at start position
	public static void matShow(double[][] matA, int start, int size) {
		for (int i = start; i < start + size; i++) {
			for (int k = start; k < start + size; k++) {
				System.out.printf("%5.2f", matA[i][k]);
			}
			System.out.print('\n');
		}
	}

	// method that takes the formula of matrix multiplication
	public static void matMult(double[][] matA, double[][] matB, double[][] matC) {
		int rowA = matA.length;
		int colA = matA[0].length;
		int rowB = matB.length;
		int colB = matB[0].length;

		// if the size of the rows do not match, it will throw an exception
		if (rowA != rowB)
			throw new IndexOutOfBoundsException();

		// formula for matrix multiplication
		for (int rowAMat = 0; rowAMat < rowA; rowAMat++) {
			for (int colBMat = 0; colBMat < colB; colBMat++) {
				for (int colAMat = 0; colAMat < colA; colAMat++) {
					matC[rowAMat][colBMat] += matA[rowAMat][colAMat] * matB[colAMat][colBMat];
				}
			}
		}
	}
}

/****	OUTPUT 1

 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.13 0.51 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.36 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.33 0.00 0.94 0.00 0.00 0.15 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00

 0.99 0.52 0.36 0.12 0.00 0.29 0.02 0.00 0.31 0.51
 0.00 0.00 0.00 0.00 0.00 0.13 0.00 0.05 0.00 0.36
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.78 0.44 0.24 0.00 0.44 0.00 0.14 0.19 0.00 1.17
 0.00 0.00 0.16 0.00 1.04 0.63 0.01 0.00 0.00 0.00
 0.00 0.55 0.72 0.00 0.09 0.48 0.85 0.88 0.00 0.33
 0.00 0.00 0.00 0.00 0.46 0.00 0.00 0.00 0.00 0.29
 0.74 0.00 0.31 0.00 0.23 0.00 0.00 0.00 0.10 0.25
 0.00 0.31 0.15 0.00 0.00 0.53 0.02 0.03 0.20 0.14
 0.00 0.05 0.63 0.00 0.13 0.00 0.07 0.10 0.04 0.00

Size = 100 Mat. Mult. Elapsed Time: 0.0066 seconds.

**** Output 2 ****

 0.00 0.91 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.99 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.92 0.00 0.00 0.00 0.00 0.00 0.00 0.66 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.69 0.00 0.00 0.00 0.16 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.97 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.17
 0.00 0.00 0.27 0.00 0.00 0.00 0.00 0.00 0.00 0.00

 0.90 0.00 0.55 0.48 0.27 0.13 0.05 1.23 0.40 0.00
 0.00 0.94 0.00 1.11 0.17 0.64 0.13 0.00 0.30 0.86
 0.00 0.06 0.31 0.11 0.00 0.04 0.07 0.00 0.35 0.00
 0.00 0.39 0.63 0.17 0.00 0.22 0.00 0.12 0.01 0.59
 1.56 0.05 0.25 0.02 0.00 0.41 0.14 0.14 0.29 0.11
 0.34 0.87 1.40 0.00 0.00 0.84 0.87 0.22 0.81 0.00
 0.00 0.99 0.38 1.23 0.86 0.87 0.05 0.79 0.90 0.55
 0.00 0.47 0.22 1.15 0.43 0.27 0.00 0.00 0.27 0.00
 0.24 1.78 0.77 0.00 0.00 0.00 0.00 0.00 0.24 0.58
 1.01 0.00 1.85 0.00 0.46 1.07 0.31 0.19 0.22 0.84

Size = 200 Mat. Mult. Elapsed Time: 0.0446 seconds.

**** OUTPUT 3 ****

 0.53 0.00 0.00 0.00 0.00 0.78 0.00 0.00 0.88 0.00
 0.00 0.00 0.00 0.12 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.38 0.00 0.99 0.00 0.70 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.14 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
 0.00 0.00 0.00 0.00 0.00 0.00 0.56 0.00 0.00 0.17

 1.97 2.35 2.41 2.74 0.97 2.43 1.67 2.67 1.00 2.65
 2.11 2.53 0.57 0.15 3.16 0.18 1.85 1.22 1.50 1.82
 1.60 5.07 1.32 3.17 1.98 1.48 1.62 1.02 1.01 1.82
 3.01 1.63 1.91 1.54 1.07 1.10 3.08 1.60 3.05 3.48
 4.03 3.05 0.85 4.03 2.65 2.18 4.32 2.31 0.72 1.16
 1.93 4.28 2.73 1.57 4.19 1.96 3.41 2.49 2.33 3.53
 3.74 3.04 2.02 1.91 1.09 0.87 3.76 1.91 2.29 3.53
 3.05 3.22 0.87 2.28 1.86 1.13 2.23 3.25 2.35 1.72
 3.24 2.75 1.60 0.79 1.34 0.74 3.39 3.04 1.43 1.36
 2.53 4.15 2.00 0.83 3.14 1.64 0.93 2.13 2.01 3.62

Size = 1000 Mat. Mult. Elapsed Time: 5.4057 seconds.

---------
QUESTIONS
---------
1) What was the smallest M that gave you a non-zero time?
   The smallest M that gave me a non-zero time was 12. 

2) What happened when you doubled M, tripled it, quadrupled it, etc?  Give several M values and their times in a table.
   - As M got bigger, the time it would take to run the program was much longer. 
   Size = 300 Mat. Mult. Elapsed Time: 0.067 seconds.
   Size = 600 Mat. Mult. Elapsed Time: 0.7694 seconds.
   Size = 1200 Mat. Mult. Elapsed Time: 13.4247 seconds.
   Size = 1800 Mat. Mult. Elapsed Time: 61.1805 seconds.

3) How large an M can you use before the program refuses to run (exception or run-time error due to memory overload) or it takes so long you can't wait for the run?
   - At M = 4000, it took so long I could not wait for the run. 

4) How did the data agree or disagree with your original time complexity estimate?
   - The data is inaccurate to my original time complexity estimates, there it it 
   disagrees with the data. Many of them increased at a rate that was much faster
   than n^3.
*/