package Model;

public class RectangularCrossSection implements CrossSection {
	
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
	public RectangularCrossSection() {
		this.ID = "Seção Retangular Generica";
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
	public RectangularCrossSection(String ID, double lado_a, double lado_b) {
		this.ID = ID;
		this.area = lado_a * lado_b;
		this.inertia = (lado_a * Math.pow(lado_b, 3)) / 12;
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
		str = this.get_ID() + "\t" + "RectangularCrossSection" + "\t" + this.get_Area() + "\t" + this.get_Inertia() + "\n";
		return str;
	}
	
	
}
