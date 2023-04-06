package AlgebraLinear;

/**
 * Esta classe descreve um obejeto Vetor epossui funçoes uteis para a manipulação de vetores.
 *
 * @author Victor Emanoel
 * @version 1.0
 * @since 12/05/2022
 */

public class Vector {
	
	/**
     * @param dim
     * dimensão do vetor.
     * @param vector
     * Variavel para criar o vetor.
     */
	private int dim;
	private double [] vector;
	
	//Construtores
		/**
		 * Construtor sem argumento.
		 */
	public Vector() {
		this.dim = 0;
		this.vector = null;
	}
	
	//Construtores
	/**
	 * Construtor com argumento.
	 * @param dim
     * Dimensão do vetor.
	 */
	public Vector(int dim) {
		this.dim = dim;
		this.vector = new double [dim];
	}

	/**
	 * Construtor com argumento.
	 * @param vector
     * Vector de variaveis do tipo double
	 */
	public Vector(double [] vector) {
		this.dim = vector.length;
		this.vector = new double [dim];
		for (int i=0; i < this.dim; i++) {
			this.vector [i] = vector [i];
		}
	}
	
	//Getters e Setters
	/**
	 * Acessa e retorna o valor da variavel dim.
	 * @return dimensão do vetor.
	 */
	public int get_Dim() {
		return dim;
	}
	
	/**
	 * Acessa e altera o valor da variavel dim.
	 * @param dim
	 * Valor final da dimensão do vetor.
	 */
	public void set_Dim(int dim) {
		this.dim = dim;
	}
	
	/**
	 * Acessa e retorna o vetor.
	 * @return vetor.
	 */
	public double [] get_Vector() {
		return vector;
	}

	/**
	 * Acessa e retorna o valor da variavel em uma determinada posição.
	 * @return valor de uma variavel dentro de um vetor.
	 */
	public double get_Vector(int index) {
		if (index < this.vector.length) {
			return this.vector [index];
		}
		else {
			System.out.println("Error: Position not found");
			return 0.0;
		}
	}
	
	/**
	 * Acessa e altera o valor da variavel dim.
	 * @param vector
	 * Vetor a ser modificado.
	 */
	public void set_Vector(double [] vector) {
		for(int i = 0; i < vector.length; i++) {
			this.vector [i] = vector [i];	
		}
	}
	/**
	 * Acessa e altera o valor da variavel dim.
	 * @param index
	 * posiçao do valor a ser alterado. 
	 * @param vector
	 * Valor de interesse.
	 */
	public void set_Vector(int index, double value) {
		if (index < this.vector.length && index >= 0) {
			this.vector [index] = value;
		}	
		else {
			System.out.println("Error: Position not found");
		}		
	}
	
	/**
	 * Metodo que realiza a adiçao de um vetor em outro.
	 * @param vector
	 * Vector a ser somado.
	 */
	public void Add(Vector vector) {
		if (this.dim != vector.dim) {
			System.out.println("Error in Vetor.add");
		}
		else {
			for (int i = 0; i < dim; i++) {
				this.vector [i] += vector.vector [i];
			}
		}
	}

	/**
	 * Metodo que realiza a adiçao de dois vetores em um terceiro vetor.
	 * @param vector_1
	 * Vector a ser somado.
	 * @param vector_2
	 * Vector a ser somado.
	 */
	public void Add(Vector vector_1, Vector vector_2) {
		if (this.dim == vector_1.dim && vector_1.dim == vector_2.dim) {
			for (int i = 0; i<dim; i++) {
				this.vector [i] = vector_1.vector [i] + vector_2.vector [i];
			}
		}
		else {
			System.out.println("Error: Vectors can not be added");
		}		
	}
	
	/**
	 * Metodo que multiplica todas as variaveis do vetor por um numero
	 * @param numv
	 * Multiplicador.
	 */
	public void Scale(double num) {
		for (int i=0; i < dim; i++)
			this.vector [i] = this.vector [i] * num;
	}

	//toString
	/**
	 * Sobrecarga do metodo toString.
	 * @return Uma String representativa do objeto
	 */	
	public String toString() {
		String str = "[";
		str += String.format("%6.2e",vector [0]);
		for (int i = 1; i < dim; i++) {
			str = str + "\t " + String.format("%6.2e", vector [i]);
		}
		str = str + "]";
		return str;
	}
	
}
