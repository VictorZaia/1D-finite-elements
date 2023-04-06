package Model;

public class CircularCrossSection implements CrossSection {
	
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
	public CircularCrossSection() {
		this.ID = "Seção Circular Generica";
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
	public CircularCrossSection(String ID, double raio) {
		this.ID = ID;
		this.area = Math.PI * Math.pow(raio, 2);
		this.inertia = (Math.PI * Math.pow(raio, 4)) / 4;
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
		str = this.get_ID() + "\t" + "CircularCrossSection" + "\t" + this.get_Area() + "\t" + this.get_Inertia() + "\n";
		return str;
	}
	
	
	
}
