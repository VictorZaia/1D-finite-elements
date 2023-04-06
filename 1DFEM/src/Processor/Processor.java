package Processor;

import AlgebraLinear.*;
import Model.*;

public class Processor {
	 
	private Model model;
	
	public Processor() {
		this.model = new Model();
	}
	
	public Processor(Model model) {
		this.model = model;
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public static void SolverModel(Model model) {
		Assembler asb;
		asb = new Assembler(model);
		asb.CalculateEquations();
		Matrix a = asb.CalculateKuu();
		Vector b = new Vector(a.get_Lins());
		b = asb.CalculateKup().Product(asb.CalculateDp());
		b.Scale(-1.0);
		b.Add(asb.CalculateFp());
		LinearEquationSystem les = new LinearEquationSystem();
		les.solver(a,b);
		Vector du = new Vector();
		du = les.getX();
		asb.setDisplacements(du);
	}
	
	
	
}
