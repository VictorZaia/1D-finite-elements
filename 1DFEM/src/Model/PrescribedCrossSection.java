package Model;

 /**
 * Esta classe descreve a Seção transversal de um elemento.
 *
 * @author Victor Emanoel
 * @version 1.0
 * @since 12/05/2022
 */

public class PrescribedCrossSection implements CrossSection {

	/**
	 * @param ID
	 * Identificador de uma instância da classe.
	 * @param Area
	 * Area da seção.
	 * @param Inertia
	 * Momento de Inercia da seção.
	 */
	private String ID;
	private double area, inertia;
	
	//Construtores
	/**
	 * Construtor sem argumento.
	 */
	public PrescribedCrossSection() {
		this.ID = "Seção Generica";
		this.area = 0;
		this.inertia = 0;
	}
	
	/**
	 * Construtor com argumento.
	 * @param ID
	 * Identificador de uma instância da classe.
	 * @param Area
	 * Area da seção.
	 * @param Inertia
	 * Momento de Inercia da seção.
	 */
	public PrescribedCrossSection(String ID, double area, double inertia) {
		this.ID = ID;
		this.area = (area < 0 ? Math.abs(area) : area);
		this.inertia = (inertia < 0 ? Math.abs(inertia) : inertia);
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
	 * Acessa e retorna o valor da variavel Area.
	 * @return Valor da area.
	 */
	public double get_Area() {
		return area;
	}
	
	/**
	 * Acessa e altera o valor da variavel area.
	 * @param area
	 * Valor da area a ser passado.
	 */
	public void set_Area(double area) {
		this.area = area;
	}
	
	/**
	 * Acessa e retorna o valor da variavel inertia.
	 * @return Valor do momento de inercia.
	 */
	public double get_Inertia() {
		return inertia;
	}

	/**
	 * Acessa e altera o valor da variavel inertia.
	 * @param inertia
	 * Valor do momento de inercia a ser passado.
	 */
	public void set_Inertia(double inertia) {
		this.inertia = inertia;
	}
	
	//toString
	/**
	 * Sobrecarga do metodo toString.
	 * @return Uma String representativa do objeto
	 */
	public String toString() {
		String str;
		str = this.get_ID() + "\t" + "PrescribedCrossSection" + "\t" + this.get_Area() + "\t" + this.get_Inertia() + "\n";
		return str;
	}
	
}