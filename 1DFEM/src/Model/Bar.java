package Model;

import AlgebraLinear.*;
import StructuralLoad.*;

/**
 * Esta classe descreve uma barra de uma estrutura.
 *
 * @author Victor Emanoel
 * @version 1.0
 * @since 12/05/2022
 */

public abstract class Bar implements EquivalentLoad {

	/**
     * @param ID
     * Identificador de uma instância da classe.
     * @param node_i
     * Nó inicial da barra.
     * @param node_j
     * Nó final da barra.
     * @param material
     * Material da barra.
     * @param crossSection
     * seção trasnversal da barra.
     */
	private String ID;
	private Node node_i, node_j;
	private Material material;
	private CrossSection crossSection;
	private PointLoad[] pointLoads;
	private DistributedLoad[] distributedLoads;
	private int i_nLP;
	private int i_nLD;
	
	//Construtores
	/**
	 * Construtor sem argumento.
	 */
	public Bar() {
		this.ID = "B0";
		this.node_i = new Node();
		this.node_j = new Node();
		this.material = new Material();
		this.crossSection = new PrescribedCrossSection();
	}
	
	/**
	 * Construtor com argumento.
     * @param ID
     * Identificador de uma instância da classe.
     * @param node_i
     * Nó inicial da barra.
     * @param node_j
     * Nó final da barra.
     * @param material
     * Material da barra.
     * @param crossSection
     * seção trasnversal da barra.
	 */
	public Bar(String ID, Node node_i, Node node_j, Material material, CrossSection crossSection) {
		this.ID = ID;
		this.node_i = node_i;
		this.node_j = node_j;
		this.material = material;
		this.crossSection = crossSection;
	}

	//Getters e Setters
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
	 * Acessa e retorna o nó inicial.
	 * @return nó inicial.
	 */
	public Node get_Node_i() {
		return node_i;
	}

	/**
	 * Acessa e altera o nó inicial.
	 * @param node_i
	 * Nó inicial da barra.
	 */
	public void set_Node_i(Node node_i) {
		this.node_i = node_i;
	}

	/**
	 * Acessa e retorna o nó final.
	 * @return nó final.
	 */
	public Node get_Node_j() {
		return node_j;
	}

	/**
	 * Acessa e altera o nó final.
	 * @param node_j
	 * Nó final da barra.
	 */
	public void set_Node_j(Node node_j) {
		this.node_j = node_j;
	}
	
	/**
	 * Acessa e retorna o material.
	 * @return Material da barra.
	 */
	public Material get_Material() {
		return material;
	}
	
	/**
	 * Acessa e altera o material.
	 * @param material
	 * Material da barra.
	 */
	public void set_Material(Material material) {
		this.material = material;
	}

	/**
	 * Acessa e retorna a seção transversal.
	 * @return Seção transversal da barra.
	 */
	public CrossSection get_CrossSection() {
		return crossSection;
	}

	/**
	 * Acessa e altera a seção transversal.
	 * @param crossSection
	 * seção trasnversal da barra.
	 */
	public void set_CrossSection(CrossSection crossSection) {
		this.crossSection = crossSection;
	}
	
	public PointLoad[] get_PointLoad() {
		return pointLoads;
	}
	
	public PointLoad get_PointLoad(int i) {
		return pointLoads[i];
	}
	
	public void set_PointLoad(PointLoad[] pointLoad) {
		this.pointLoads = pointLoad;
	}
	
	public void set_PointLoad(int i, PointLoad pointLoad) {
		this.pointLoads[i] = pointLoad;
	}
	
	public void set_PointLoad(int nLP) {
		this.pointLoads = new PointLoad[nLP];
	}
	
	public DistributedLoad[] get_DistributedLoad() {
		return distributedLoads;
	}
	
	public DistributedLoad get_DistributedLoad(int i) {
		return distributedLoads[i];
	}
	
	public void set_DistributedLoad(DistributedLoad[] distributedLoad) {
		this.distributedLoads = distributedLoad;
	}
	
	public void set_DistributedLoad(int i, DistributedLoad distributedLoad) {
		this.distributedLoads[i] = distributedLoad;
	}
	
	public void set_DistributedLoad(int nLQ) {
		this.distributedLoads = new DistributedLoad[nLQ];
	}
	
	public int get_i_nLP() {
		return i_nLP;
	}
	
	public int get_i_nLD() {
		return i_nLD;
	}
	
	public abstract Vector get_Equations();
	
	public abstract Matrix CalculateLocalStiffnessMatrix();
	
	public abstract Matrix CalculateTransformationMatrix();
	
	public Matrix CalculateGlobalStiffnessMatrix() {
		Matrix globalStiffnessMatrix, aux;
		aux = Matrix.Product(this.CalculateLocalStiffnessMatrix(), this.CalculateTransformationMatrix());
		globalStiffnessMatrix = Matrix.Product(Matrix.Transpose(CalculateTransformationMatrix()), aux);
		return globalStiffnessMatrix;
	}
	
	public static Bar factory(String ID) {
		Bar bar = null;
		switch(ID) {
		case "Truss":
			bar = new Truss();
			break;
		case "Beam":
			bar = new Beam();
			break;
		case "Frame":
			bar = new Frame();
			break;
		default:
			System.out.println("ERROR: Bar Type does not exist.");
			System.exit(0);
		}
		return bar;
	}
	
	public void AddPointLoad(PointLoad pointLoad) {
		this.pointLoads[i_nLP] = pointLoad;
		++i_nLP;
	}
	
	public void AddDistributedLoad(DistributedLoad distributedLoad) {
		this.distributedLoads[i_nLD] = distributedLoad;
		++i_nLD;
	}
	
	public abstract Vector getEndForce();
	
	//toString
	/**
	 * Sobrecarga do metodo toString.
	 * @return Uma String representativa do objeto
	 */
	public String toString() {
		String str;
		str = this.get_ID() + "\t" + this.node_i.get_ID() + "\t" + this.node_j.get_ID() + "\t" + this.material.get_ID() + "\t" + this.crossSection.get_ID() + "\n";
		return str;
	}
}