package Model;

import AlgebraLinear.*;

/**
	 * Esta classe descreve um nó de um elemento.
	 * 
	 * @author Victor Emanoel
	 * @version 1.0
	 * @since 12/05/2022
	 */

public class Node {
	
	/**
     * @param ID
     * Identificador de uma instância da classe.
     * @param Coord_x
     * Coordenada X do nó.
     * @param Coord_y
     * Cordenada Y do nó.
     * @param Coord_z
     * Cordenada Z do nó.
     * @param nodalLoads
     * Forças nodais.
     * @param constraints
     * Restriçoes nodais do modelo.
     * @param equations
     * Lista de equaçoes.
     */
	private String ID;
	private double Coord_x, Coord_y, Coord_z;
	private Vector nodalLoads;
	private boolean[] restraints;
	private Vector displacement;
	private int[] equations;

	//Construtores
	/**
	 * Construtor sem argumento.
	 */
	public Node() {
		this.ID = "N0";
		this.Coord_x = 0;
		this.Coord_y = 0;
		this.Coord_z = 0;
	}
	
	/**
	 * Construtor com argumento.
	 * @param ID
	 * Identificador de uma instância da classe.
     * @param Coord_x
     * Coordenada X do nó.
     * @param Coord_y
     * Cordenada Y do nó.
	 */
	public Node(String ID, double Coord_x, double Coord_y) {
		this.ID = ID;
		this.Coord_x = Coord_x;
		this.Coord_y = Coord_y;
		this.Coord_z = 0;
	}
	
	/**
	 * Construtor com argumento.
	 * @param ID
	 * Identificador de uma instância da classe.
     * @param Coord_x
     * Coordenada X do nó.
     * @param Coord_y
     * Cordenada Y do nó.
     * @param Coord_z
     * Cordenada Z do nó.
	 */
	public Node(String ID, double Coord_x, double Coord_y, double Coord_z) {
		this.ID = ID;
		this.Coord_x = Coord_x;
		this.Coord_y = Coord_y;
		this.Coord_z = Coord_z;
	}
	
	// getters e setters
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
	 * Acessa e retorna o valor da variavel Coord_x.
	 * @return Valor da coordenada x.
	 */
	public double get_Coord_x() {
		return Coord_x;
	}
	
	/**
	 * Acessa e altera o valor da variavel Coord_x.
	 * @param Coord_x
	 * Valor da coordenada x a ser passada.
	 */
	public void set_Coord_x(double Coord_x) {
		this.Coord_x = Coord_x;
	}
	
		/**
	 * Acessa e retorna o valor da variavel Coord_y.
	 * @return Valor da coordenada y.
	 */
	public double get_Coord_y() {
		return Coord_y;
	}
	
	/**
	 * Acessa e altera o valor da variavel Coord_y.
	 * @param Coord_y
	 * Valor da coordenada y a ser passada.
	 */
	public void set_Coord_y(double Coord_y) {
		this.Coord_y = Coord_y;
	}	
	
	/**
	 * Acessa e retorna o valor da variavel Coord_z.
	 * @return Valor da coordenada z.
	 */
	public double get_Coord_z() {
		return Coord_z;
	}

	/**
	 * Acessa e altera o valor da variavel Coord_z.
	 * @param Coord_z
	 * Valor da coordenada z a ser passada.
	 */
	public void set_Coord_z(double Coord_z) {
		this.Coord_z = Coord_z;
	}
	
	/**
	 * Acessa e retorna o valor das cargas nodais.
	 * @return Vetor de cargas nodais
	 */
	public Vector get_NodalLoads() {
		return nodalLoads;
	}
	
	/**
	 * Acessa e retorna o valor de uma cargas nodal em uma determinada posição
	 * @return Valor da carga nodal na posiçao i.
	 */
	public double get_NodalLoads(int i) {
		return nodalLoads.get_Vector(i);
	}
	
	/**
	 * Acessa e altera o vaor das restriçoes nodais..
	 * @param nodalLoads
	 * Vector de cargas nodais.
	 */
	public void set_NodalLoads(Vector nodalLoads) {
		this.nodalLoads = nodalLoads;
	}
	
	/**
	 * Acessa e altera o valor da variavel Coord_z.
	 * @param nodalLoads
	 * Vector de cargas nodais.
	 */
	public void set_NodalLoads(int i, double nodalLoads) {
		this.nodalLoads.set_Vector(i, nodalLoads);
	}
	
	/**
	 * Acessa e retorna as restriçoes dos nós.
	 * @return Restriçoes nodais
	 */
	public boolean[] get_Restraints() {
		return restraints;
	}
	
	/**
	 * Acessa e retorna as restriçoes de um nó.
	 * @return Restriçoes de um nó
	 */
	public boolean get_Restraints(int i) {
		return restraints[i];
	}
	
	/**
	 * Acessa e altera as restriçoes nodais
	 * @param constraints
	 * Restriçoes nodais.
	 */
	public void set_Restraints(boolean[] restraints) {
		this.restraints = restraints;
	}
	
	/**
	 * Acessa e altera as restriçoes de um nó
	 * @param constraints
	 * Restriçoes nodais.
	 */
	public void set_Restraints(int i, boolean restraints) {
		this.restraints[i] = restraints;
	}
	
	public Vector get_Displacements() {
		return displacement;
	}
	
	/**
	 * Acessa e retorna as restriçoes de um nó.
	 * @return Restriçoes de um nó
	 */
	public double get_Displacements(int i) {
		return displacement.get_Vector(i);
	}
	
	/**
	 * Acessa e altera as restriçoes nodais
	 * @param constraints
	 * Restriçoes nodais.
	 */
	public void set_Displacements(Vector displacement) {
		this.displacement = displacement;
	}
	
	/**
	 * Acessa e altera as restriçoes de um nó
	 * @param constraints
	 * Restriçoes nodais.
	 */
	public void set_Displacements(int i,  double displacement) {
		this.displacement.set_Vector(i, displacement);
	}
	
	/**
	 * Acessa e retorna o numero das equaçoes dos nos.
	 * @return numero das equaçoes
	 */
	public int[] get_Equations() {
		return equations;
	}
	
	/**
	 * Acessa e retorna o numero da equaçoes de um no.
	 * @return numero da equaçao
	 */
	public int get_Equations(int i) {
		return equations[i];
	}
	
	/**
	 * Acessa e altera os numeros das equaçoes.
	 * @param equations
	 * Numero das equaçoes.
	 */
	public void set_Equations(int[] equations) {
		this.equations = equations;
	}
	
	/**
	 * Acessa e altera o numeros da equaçao de um nó.
	 * @param equations
	 * Numero da equaçao.
	 */
	public void set_Equations(int i, int equations) {
		this.equations[i] = equations;
	}
	
	/**
	 * Calcula a distancia de um ponto à outro.
	 * @return Distancia entre nós
	 */
	public double DistanceTo(Node node) {
		double distance;
		distance = Math.sqrt(Math.pow(node.Coord_x - this.Coord_x, 2) + Math.pow(node.Coord_y - this.Coord_y, 2)  + Math.pow(node.Coord_z -this.Coord_z, 2));
		return distance;
	}
	
	//toString
	/**
	 * Sobrecarga do metodo toString.
	 * @return Uma String representativa do objeto
	 */
	public String toString() {
		String str;
		str = this.get_ID() + "\t" + this.get_Coord_x() + "\t" + this.get_Coord_y() + "\t" + this.get_Coord_z() + "\n";
		return str;
	}

}