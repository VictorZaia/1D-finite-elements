package AlgebraLinear;

/**
 * Esta classe descreve um obejeto Matrix e possui funçoes uteis para a manipulação de matrizes.
 *
 * @author Victor Emanoel
 * @version 1.0
 * @since 12/05/2022
 */

public class Matrix {

	/**
     * @param ncol
     * Numero de linhas da matriz.
     * @param nlin
     * Numero de colunas da matriz.
     * @parammatrix
     * Variavel para criar a matriz.
     */
	 public int nlin, ncol;
	 public double [][] matrix;
	
	//Construtores
	/**
	 * Construtor sem argumento.
	 */
	public Matrix() {
		this.nlin = 0;
		this.ncol = 0;
		this.matrix = null;
	}
	
	/**
	 * Construtor com argumento.
	 * @param nlin
	 * Numero de linhas da matrix.
	 * @param ncol
	 * Numero de linhas da matrix.
	 */
	public Matrix(int nlin, int ncol) {
		this.nlin = nlin;
		this.ncol = ncol;
		this.matrix = new double [nlin][ncol];
	}
	
	/**
	 * Construtor com argumento.
	 * @param matrix
	 * Matriz desejada.
	 */
	public Matrix(double [][] matrix) {
		this.nlin = matrix.length;
		this.ncol = matrix [0].length;
		this.matrix = matrix;
	}
	
	/**
	 * Construtor com argumento.
	 * @param matrix
	 * Matriz desejada.
	 */
	public Matrix(Matrix matrix) {
		this.nlin = matrix.nlin;
		this.ncol = matrix.ncol;
		this.matrix = matrix.matrix;
	}
	
	//Getters e Setters
	/**
	 * Acessa e retorna o valor da variavel nlin.
	 * @return Numero de linhas da matriz.
	 */
	public int get_Lins() {
		return nlin;
	}
	
	/**
	 * Acessa e altera o valor da variavel nlin.
	 * @param nlin
	 * Novo valor de linhas desejado.
	 */
	public void set_Lins(int nlin) {
		this.nlin = nlin;
	}
	
	/**
	 * Acessa e retorna o valor da variavel ncol.
	 * @return Numero de colunas da matriz.
	 */
	public int get_Cols() {
		return ncol;
	}
	
	/**
	 * Acessa e altera o valor da variavel ncol.
	 * @param ncol
	 * Novo valor de colunas desejado.
	 */
	public void set_Cols(int ncol) {
		this.ncol = ncol;
	}
	
	/**
	 * Acessa e retorna a matriz.
	 * @return Matrix.
	 */
	public double [][] get_Matrix() {
		return matrix;
	}
	
	/**
	 * Acessa e retorna uma linha da matriz.
	 * @return linha da matriz.
	 */
	public double [] get_Matrix(int lin) {
		if (lin < this.matrix.length) {
			return this.matrix [lin];
		}
		else {
			System.out.println("Error: Position not found");
			return null;
		}
	}
	
	/**
	 * Acessa e retorna o valor em uma certa posiçao.
	 * @return Valor especifico de uma posição
	 */
	public double get_Matrix(int lin, int col) {
		if (lin < this.nlin && col < this.ncol) {
			return this.matrix [lin][col];
		}
		else {
			System.out.println("Error: Position not found");
			return 0.0;
		}
	}
	
	/**
	 * Acessa e altera a matriz.
	 * @param matrix
	 * Nova matrix.
	 */
	public void set_Matrix(double [][] matrix) {
		this.matrix = matrix;
	}
	
	/**
	 * Acessa e altera uma linha da matriz.
	 * @param lin
	 * Numero da linha a ser alterada.
	 * @param vector
	 * Vetor de interesse.
	 */
	public void set_Matrix_line(int lin, double [] vector) {
		if(lin < this.nlin)
			this.matrix [lin] = vector;
		else 
			System.out.println("The line can not be added to the Matrix");
	}
	
	/**
	 * Acessa e altera uma coluna da matriz.
	 * @param col
	 * Numero da coluna a ser alterada.
	 * @param vector
	 * Vetor de interesse.
	 */
	public void set_Matrix_column(int col, double [] vector) {
		if(col < this.ncol) {
			for(int i = 0; i < nlin; i++) {
				this.matrix [i][col] = vector [i];
			}
		}
		else 
			System.out.println("The Column can not be added to the Matrix");
	}
	
	/**
	 * Acessa e altera um valor especifico da matriz.
	 * @param lin
	 * Numero da linha a ser alterada.
	 * @param col
	 * Numero da coluna a ser alterada.
	 * @param num
	 * Numero de interesse.
	 */
	public void set_Matrix(int lin, int col, double num) {
		if(lin < this.nlin && col < this.ncol) {
			this.matrix [lin][col] = num;
		}
		else 
			System.out.println("The value can not be added to the Matrix");
	}
	
	/**
	 * Metodo que realiza a adiçao de uma matriz em outro.
	 * @param matrix
	 * Matriz a ser somada.
	 */
	public void Add(Matrix matrix) {
		if(this.nlin == matrix.nlin && this.ncol == matrix.ncol) {
			for (int i = 0; i < this.nlin; i++) {
				for (int j = 0; j < this.ncol; j++) {
					this.matrix [i][j] += matrix.matrix [i][j];
				}
			}
		}
		else {
			System.out.println("Error: Matrices can not be added");
		}
	}
	
	/**
	 * Metodo que realiza a adiçao de duas matrizes em uma terceiro matriz.
	 * @param matrix_1
	 * matrix a ser somada.
	 * @param Matrix_2
	 * Vector a ser somada.
	 */
	public void Add(Matrix matrix_1, Matrix matrix_2) {
		if(this.nlin == matrix_1.nlin && this.ncol == matrix_1.ncol && matrix_1.nlin == matrix_2.nlin && matrix_1.ncol == matrix_2.ncol) {
			for (int i = 0; i < this.nlin; i++) {
				for (int j = 0; j < this.ncol; j++) {
					this.matrix [i][j] = matrix_1.matrix [i][j] + matrix_2.matrix [i][j];
				}
			}
		}
		else {
			System.out.println("Error: Matrices can not be added");
		}
	}
	
	/**
	 * Metodo que multiplica todas as variaveis da matriz por um numero.
	 * @param num
	 * Multiplicador.
	 */
	public void Scale(double num) {
		for (int i = 0; i < this.nlin; i++) {
			for (int j = 0; j < this.ncol; j++) {
				this.matrix [i][j] = this.matrix [i][j] * num;
			}
		}
	}
	
	/**
	 * Metodo que multiplica uma matriz por um vetor.
	 * @param vector
	 * Vetor a ser multiplicado.
	 */
	public Vector Product(Vector vector) {
		double [] aux = new double [this.nlin];
		if (this.ncol == vector.get_Dim()) {
			for (int i = 0; i < this.nlin; i++) {
				for (int j = 0; j < this.ncol; j++) {
					aux[i] += this.matrix[i][j] * vector.get_Vector(j);
				}
			}
			Vector vec = new Vector(aux);
			return vec;
		}
		else {
			System.out.println("ERROR: The number of columns of the matrix must be equal to the dimension of the vector.");
			return null;
		}
	}
	
	/**
	 * Metodo que multiplica uma matriz por um vetor.
	 * @param matrix
	 * Matriz a ser multiplicada.
	 * @param vector
	 * Vetor a ser multiplicado.
	 */
	public static Vector Product(Matrix matrix, Vector vector) {
		if (matrix.ncol == vector.get_Dim()) {
			double [] aux = new double [matrix.ncol];
			for (int i = 0; i < matrix.ncol; i++) {
				for (int j = 0; j < matrix.nlin; j++) {
					aux [i] = matrix.matrix [i][j] + vector.get_Vector(j);
				}
			}
			Vector vec = new Vector(aux);
			return vec;
		}
		else {
			System.out.println("ERROR: The number of columns of the matrix must be equal to the dimension of the vector.");
			return null;
		}
	}
	
	/**
	 * Metodo que multiplica uma matriz por outra matriz.
	 * @param Matrix
	 * Matriz a ser multiplicado.
	 */
	public Matrix Product(Matrix matrix) {
		if (this.ncol == matrix.nlin) {
		Matrix matrix_r = new Matrix(this.nlin, matrix.ncol);
		for(int i = 0; i < matrix_r.nlin; i++) {
			for (int j = 0; j < matrix_r.ncol; j++) {
				for (int m = 0; m < this.ncol; m++) {
					matrix_r.matrix [i][j] += this.matrix [i][m] * matrix.matrix [m][j];
				}
			}
		}
		return matrix_r;
		}
		else {
			System.out.println("ERROR: The number of columns of the matrix must be equal to the number of lines of the multiplied matrix.");
			return null;
		}
	}
	
	/**
	 * Metodo que retorna a multiplicação de uma matriz por outra matriz.
	 * @param Matrix_1
	 * Matriz a ser multiplicada.
	 * @param Matrix_2
	 * Matriz a ser multiplicada.
	 */
	public static Matrix Product(Matrix matrix_1, Matrix matrix_2) {
		if (matrix_1.ncol == matrix_2.nlin) {
		Matrix matrix_r = new Matrix(matrix_1.nlin, matrix_2.ncol);
		for(int i = 0; i < matrix_r.nlin; i++) {
			for (int j = 0; j < matrix_r.ncol; j++) {
				for (int m = 0; m < matrix_1.ncol; m++) {
					matrix_r.matrix [i][j] += matrix_1.matrix [i][m] * matrix_2.matrix [m][j];
				}
			}
		}
		return matrix_r;
		}
		else {
			System.out.println("ERROR: The number of columns of the matrix must be equal to the number of lines of the multiplied matrix.");
			return null;
		}
	}
	
	/**
	 * Metodo que substitui uma matriz por sua transposta.
	 */
	public void Transpose() {
		Matrix aux = new Matrix(this.ncol, this.nlin);
		for (int i = 0; i < aux.nlin; i++) {
			for (int j = 0; j < aux.ncol; j++) {
				aux.matrix [i][j] = this.matrix [j][i];
			}
		}
		this.nlin = aux.nlin;
		this.ncol = aux.ncol;
		this.matrix = aux.matrix;
	}
	
	/**
	 * Metodo que retorna uma matriz transposta.
	 * @param matrix
	 * Matriz a ser transposta.
	 */
	public static Matrix Transpose(Matrix matrix) {
		Matrix matrix_t = new Matrix(matrix.ncol, matrix.nlin);
		double elem;
		for (int i = 0; i < matrix_t.nlin; i++) {
			for (int j = 0; j < matrix_t.ncol; j++) {
				elem = matrix.get_Matrix(i, j);
				matrix_t.set_Matrix(j, i, elem);
			}
		}
		return matrix_t;
	}
	
	//toString
	/**
	 * Sobrecarga do metodo toString.
	 * @return Uma String representativa do objeto
	 * */
	public String toString() {
		String str = "[";
		for (int i = 0; i < this.nlin; i++) {
			for (int j = 0; j < this.ncol; j++) {
				if(i == (nlin - 1) && j == (ncol - 1))
					str += String.format("%6.2e",this.matrix [i][j]);
				else
					str+= String.format("%6.2e",this.matrix [i][j]) + "\t";
			}
			if(i != this.nlin-1)
			str += "\n";
		}
		str += "]";
		return str;
	}
	
}
