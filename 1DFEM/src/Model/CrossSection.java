package Model;

import java.util.*;

public interface CrossSection {
	
	public String get_ID();
	
	public void set_ID(String ID);
	
	public double get_Area();
	
	public void set_Area(double area);
	
	public double get_Inertia();
	
	public void set_Inertia(double inertia);
	
	//VERIFICAR O NUMERO DE TOKENS PARA VER SE AS INFORMACOES FORAM ESCRITAS DE FORMA CORRETAS.
	public static CrossSection factory(String ID, String secType, StringTokenizer st) {
		CrossSection cross_section = null;
		switch(secType) {
		case "Prescribed":
			cross_section = new PrescribedCrossSection(ID, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			break;
		case "Rectangular":
			cross_section = new RectangularCrossSection(ID, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			break;
		case "Circular":
			cross_section = new CircularCrossSection(ID, Double.parseDouble(st.nextToken()));
			break;
		default:
			System.out.println("ERROR: Section Type does not exist.");
			System.exit(0);
		}
		return cross_section;
	}
	
	
}
