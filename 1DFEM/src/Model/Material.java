package Model;

 /**
 * Esta classe descreve o material de um elemento.
 *
 * @author Victor Emanoel
 * @version 1.0
 * @since 12/05/2022
 */

public class Material {

	/**
     * @param ID
     * Identificador de uma instância da classe.
     * @param E
     * Modulo de elasticidade.
     * @param nu
     * Coeficiente de poisson.
     */
	private String ID;
	private double E, nu;
	
	//Construtores
	/**
	 * Construtor sem argumento.
	 */
	public Material() {
		this.ID = "Material generico";
		this.E = 0;
		this.nu = 0;
	}

	/**
	 * Construtor com argumento.
	 * @param ID
     * Identificador de uma instância da classe.
     * @param E
     * Modulo de elasticidade.
     * @param nu
     * Coeficiente de poisson.
	 */
	public Material(String ID, double E, double nu) {
		this.ID = ID;
		this.E = (E < 0 ? Math.abs(E) : E);
		this.nu = (nu < 0 ? Math.abs(nu) : nu);
	}

	//Getters e setters
	/**
	 * Acessa e retorna o valor da variavel ID.
	 * @return Valor de ID.
	 */
	public String get_ID() {
		return ID;
	}
	
	/**
	 * Acessa e altera o valor da variavel ID.
	 * @param ID
	 * Valor de ID a ser passado.
	 */
	public void set_ID(String ID) {
		this.ID = ID;
	}

	/**
	 * Acessa e retorna o valor da variavel E.
	 * @return Valor de E.
	 */
	public double get_E() {
		return E;
	}
	
	/**
	 * Acessa e altera o valor da variavel E.
	 * @param E
	 * Valor de E a ser passado.
	 */
	public void set_E(double E) {
		this.E = E;
	}
	
	/**
	 * Acessa e retorna o valor da variavel nu.
	 * @return Valor de nu.
	 */
	public double get_nu() {
		return nu;
	}
	
	/**
	 * Acessa e altera o valor da variavel nu.
	 * @param nu
	 * Valor de nu a ser passado.
	 */
	public void set_nu(double nu) {
		this.nu = nu;
	}


	//toString
	/**
	 * Sobrecarga do metodo toString.
	 * @return Uma String representativa do objeto
	 */
	public String toString() {
		String str;
		str = this.get_ID() + "\t" + this.get_E() + "\t" + this.get_nu() + "\n";
		return str;
	}
	
}
