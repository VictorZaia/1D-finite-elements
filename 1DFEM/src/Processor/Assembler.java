package Processor;

import AlgebraLinear.*;
import Model.*;
import StructuralLoad.*;

public class Assembler {
	
	private Model model;
	private int numberOfDof;
	private int numberOfReactions;
	
	public Assembler() {
		this.model = new Model();
	}
	
	public Assembler(Model model) {
		this.model = model;
	}
	
	public void CalculateEquations() {
		for(int i = 0; i < model.get_number_Node(); i++) {
			Node node = model.get_Node(i);
			int[] intList = new int[model.calculateDOFPerNode()];
			for (int j = 0; j < model.calculateDOFPerNode(); j++) {
				if (node.get_Restraints(j)) {
					numberOfReactions -= 1;
					intList[j] = numberOfReactions;
				} else {
					numberOfDof += 1;
					intList[j] = numberOfDof;
				}
			}
			model.get_Node(i).set_Equations(intList);
		}
	}
	
	
	public Matrix CalculateKuu() {
		Matrix Kuu = new Matrix(this.numberOfDof, this.numberOfDof);
		for(int i = 0; i < model.get_number_Bar(); i++) {
			Matrix kg = model.get_Bar(i).CalculateGlobalStiffnessMatrix();
			Vector equations = model.get_Bar(i).get_Equations();
			for(int j = 0; j < kg.get_Lins(); j++) {
				int eqline = (int) equations.get_Vector(j);
				for(int m = 0; m < kg.get_Cols(); m++) {
					int eqcol = (int) equations.get_Vector(m);
					if (eqline > 0 && eqcol > 0) {
						double aux = Kuu.get_Matrix((eqline - 1), (eqcol - 1));
						aux += kg.get_Matrix(j, m);
						Kuu.set_Matrix((eqline - 1), (eqcol - 1), aux);;
					}
				}
			}
		}
		return Kuu;
	}
	
	public Matrix CalculateKpu() {
		Matrix Kpu = new Matrix(-1 * this.numberOfReactions, this.numberOfDof);
		for(int i = 0; i < model.get_number_Bar(); i++) {
			Matrix kg = model.get_Bar(i).CalculateGlobalStiffnessMatrix();
			Vector equations = model.get_Bar(i).get_Equations();
			for(int j = 0; j < kg.get_Lins(); j++) {
				int eqline = (int) equations.get_Vector(j);
				for(int m = 0; m < kg.get_Cols(); m++) {
					int eqcol = (int) equations.get_Vector(m);
					if (eqline < 0 && eqcol > 0) {
						double aux = Kpu.get_Matrix(((-1) * eqline - 1), (eqcol - 1));
						aux += kg.get_Matrix(j, m);
						Kpu.set_Matrix(((-1) * eqline - 1), (eqcol - 1), aux);;
					}
				}
			}
		}
		return Kpu;
	}
	
	public Matrix CalculateKup() {
		Matrix Kup = new Matrix(this.numberOfDof, -1 * this.numberOfReactions);
		for(int i = 0; i < model.get_number_Bar(); i++) {
			Matrix kg = model.get_Bar(i).CalculateGlobalStiffnessMatrix();
			Vector equations = model.get_Bar(i).get_Equations();
			for(int j = 0; j < kg.get_Lins(); j++) {
				int eqline = (int) equations.get_Vector(j);
				for(int m = 0; m < kg.get_Cols(); m++) {
					int eqcol = (int) equations.get_Vector(m);
					if (eqline > 0 && eqcol < 0) {
						double aux = Kup.get_Matrix((eqline - 1), ((-1) * eqcol - 1));
						aux += kg.get_Matrix(j, m);
						Kup.set_Matrix((eqline - 1), ((-1) * eqcol - 1), aux);;
					}
				}
			}
		}
		return Kup;
	}
	
	public Matrix CalculateKpp() {
		Matrix Kpp = new Matrix((-1) * this.numberOfReactions, (-1) * this.numberOfReactions);
		for(int i = 0; i < model.get_number_Bar(); i++) {
			Matrix kg = model.get_Bar(i).CalculateGlobalStiffnessMatrix();
			Vector equations = model.get_Bar(i).get_Equations();
			for(int j = 0; j < kg.get_Lins(); j++) {
				int eqline = (int) equations.get_Vector(j);
				for(int m = 0; m < kg.get_Cols(); m++) {
					int eqcol = (int) equations.get_Vector(m);
					if (eqline < 0 && eqcol < 0) {
						double aux = Kpp.get_Matrix(((-1) * eqline - 1), ((-1) * eqcol - 1));
						aux += kg.get_Matrix(j, m);
						Kpp.set_Matrix(((-1) * eqline - 1), ((-1) * eqcol - 1), aux);
					}
				}
			}
		}
		return Kpp;
	}
	
	public Matrix CalculateK() {
		Matrix k = new Matrix((this.numberOfDof + (-1) * this.numberOfReactions), (this.numberOfDof + (-1) * this.numberOfReactions));
		for (int i = 0; i < model.get_number_Bar(); i++) {
			Matrix kg = model.get_Bar(i).CalculateGlobalStiffnessMatrix();
			Vector equations = model.get_Bar(i).get_Equations();
			for (int j = 0; j < kg.get_Lins(); j++) {
				int eqLine = (int) equations.get_Vector(j);
				for (int m = 0; m < kg.get_Cols(); m++) {
					int eqCol = (int) equations.get_Vector(m);
					if (eqLine > 0 && eqCol > 0) {
						double aux = kg.get_Matrix(j, m);
						aux += k.get_Matrix((eqLine - 1), (eqCol - 1));
						k.set_Matrix((eqLine - 1), (eqCol - 1), aux);
					} 
					else if (eqLine < 0 && eqCol > 0) {
						double aux = kg.get_Matrix(j, m);
						aux += k.get_Matrix(((-1) * eqLine - 1 + this.numberOfDof), (eqCol - 1));
						k.set_Matrix(((-1) * eqLine - 1 + this.numberOfDof), (eqCol - 1), aux);
					} 
					else if (eqLine > 0 && eqCol < 0) {
						double aux = kg.get_Matrix(j, m);
						aux += k.get_Matrix((eqLine - 1), ((-1) * eqCol - 1 + this.numberOfDof));
						k.set_Matrix((eqLine - 1), ((-1) * eqCol - 1 +  + this.numberOfDof), aux);
					} 
					else if (eqLine < 0 && eqCol < 0) {
						double aux = kg.get_Matrix(j, m);
						aux += k.get_Matrix(((-1) * eqLine - 1 + this.numberOfDof), ((-1) * eqCol - 1 + this.numberOfDof));
						k.set_Matrix(((-1) * eqLine - 1 + + this.numberOfDof), ((-1) * eqCol - 1 +  + this.numberOfDof), aux);
					}
				}
			}
		}
		return k;
	}
	
	public Vector CalculateFpNodal() {
		double[] auxDouble = new double[this.numberOfDof];
		for (int i = 0; i < model.get_number_Node(); i++) {
			Node node = model.get_Node(i);
			for (int j = 0; j < model.calculateDOFPerNode(); j++) {
				int auxEq = node.get_Equations(j);
				if (auxEq > 0) {
					auxDouble[(auxEq - 1)] = node.get_NodalLoads(j);
				}
			}
		}
		Vector nodalForces = new Vector(auxDouble);
		return nodalForces;
	}
	
	public Vector CalculateFuNodal() {
		double[] auxDouble = new double[-1 * this.numberOfReactions];
		for (int i = 0; i < model.get_number_Node(); i++) {
			Node node = model.get_Node(i);
			for (int j = 0; j < model.calculateDOFPerNode(); j++) {
				int auxEq = node.get_Equations(j);
				if (auxEq < 0) {
					auxDouble[(-1 * auxEq - 1)] = node.get_NodalLoads(j);
				}
			}
		}
		Vector nodalForces = new Vector(auxDouble);
		
		return nodalForces;
	}
	
	public Vector CalculateFpEq(){
		double[] auxDouble = new double[this.numberOfDof];
		for(int i = 0; i < model.get_number_Bar(); i++) {
			Bar bar = model.get_Bar(i);
			Vector barEqForces = bar.getEquivalentLoad();
			Matrix Tt = bar.CalculateTransformationMatrix();
			Tt.Transpose();
			barEqForces = Tt.Product(barEqForces);
			Vector equations = bar.get_Equations();
			for (int j = 0; j < equations.get_Dim(); j++) {
				if (equations.get_Vector(j) > 0) {
					auxDouble[(int) (equations.get_Vector(j) - 1)] += barEqForces.get_Vector(j);
				}
			}
		}
		Vector nodalEqForces = new Vector(auxDouble);
		return nodalEqForces;
	}
	
	public Vector CalculateFuEq(){
		double[] auxDouble = new double[-1 * this.numberOfReactions];
		for(int i = 0; i < model.get_number_Bar(); i++) {
			Bar bar = model.get_Bar(i);
			Vector barEqForces = bar.getEquivalentLoad();
			Matrix Tt = bar.CalculateTransformationMatrix();
			Tt.Transpose();
			barEqForces = Tt.Product(barEqForces);
			Vector equations = bar.get_Equations();
			for (int j = 0; j < equations.get_Dim(); j++) {
				if (equations.get_Vector(j) < 0) {
					auxDouble[-1 * (int) (equations.get_Vector(j) - 1)] += barEqForces.get_Vector(j);
				}
			}
		}
		Vector nodalEqForces = new Vector(auxDouble);
		return nodalEqForces;
	}
	
	public Vector CalculateFp() {
		Vector fpNodal = this.CalculateFpNodal();
		Vector fpNodalEq = this.CalculateFpEq();
		fpNodal.Add(fpNodalEq);
		return fpNodal;
	}
	
	public Vector CalculateDp() {
		double[] auxDouble = new double[(-1) * this.numberOfReactions];
		Vector dp;
		for (int i = 0; i < model.get_number_Node(); i++) {
			Node n = new Node();
			n = model.get_Node(i);
			for (int j = 0; j < model.calculateDOFPerNode(); j++) {
				int auxEq = n.get_Equations(j);
				if (auxEq < 0) {
					auxDouble[((-1) * (auxEq) - 1)] = n.get_Displacements(j);
				}
			}
		}
		dp = new Vector(auxDouble);
		return dp;
	}
	
	public void setDisplacements(Vector du) {
		int cont = 0;
		for (int i = 0; i < model.get_number_Node(); i++) {
			Node node = model.get_Node(i);
			for (int j = 0; j < model.calculateDOFPerNode(); j++) {
				int eq = node.get_Equations(j);
				if (eq > 0) {
					double aux = du.get_Vector(cont);
					aux += node.get_Displacements(j);
					node.get_Displacements().set_Vector(j, aux);
					cont++;
				}
			}
		}
	}
	
}
