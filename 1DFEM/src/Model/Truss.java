package Model;

import AlgebraLinear.*;

/**
 * Esta classe descreve uma barra do tipo Truss.
 *
 * @author Victor Emanoel
 * @version 1.0
 * @since 12/05/2022
 */

public class Truss extends Bar {
	
	//Construtores
	/**
	 * Construtor sem argumento.
	 */
	public Truss() {
		super();
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
	public Truss(String ID, Node node_i, Node node_j, Material material, CrossSection crossSection) {
		super(ID, node_i, node_j, material, crossSection);
	}
	
	public Matrix CalculateLocalStiffnessMatrix() {
		Matrix stiffnessMatrix = new Matrix(4, 4);
		double aux = this.get_Material().get_E() * this.get_CrossSection().get_Area() / this.get_Node_i().DistanceTo(this.get_Node_j());
		
		stiffnessMatrix.set_Matrix(0, 0, 1);    stiffnessMatrix.set_Matrix(0, 1, 0);   stiffnessMatrix.set_Matrix(0, 2, -1);    stiffnessMatrix.set_Matrix(0, 3, 0);
		stiffnessMatrix.set_Matrix(1, 0, 0);    stiffnessMatrix.set_Matrix(1, 1, 0);   stiffnessMatrix.set_Matrix(1, 2, 0);     stiffnessMatrix.set_Matrix(1, 3, 0);
		stiffnessMatrix.set_Matrix(2, 0, -1);   stiffnessMatrix.set_Matrix(2, 1, 0);   stiffnessMatrix.set_Matrix(2, 2, 1);     stiffnessMatrix.set_Matrix(2, 3, 0);
		stiffnessMatrix.set_Matrix(3, 0, 0);    stiffnessMatrix.set_Matrix(3, 1, 0);   stiffnessMatrix.set_Matrix(3, 2, 0);     stiffnessMatrix.set_Matrix(3, 3, 0);
		
		
		stiffnessMatrix.Scale(aux);
		
		return stiffnessMatrix;
	}
	
	public Matrix CalculateTransformationMatrix() {
		Matrix transformationMatrix = new Matrix(4, 4);
		double L = this.get_Node_i().DistanceTo(this.get_Node_j());
		double C = (this.get_Node_j().get_Coord_x() - this.get_Node_i().get_Coord_x()) / L;
		double S = (this.get_Node_j().get_Coord_y() - this.get_Node_i().get_Coord_y()) / L;
		
		transformationMatrix.set_Matrix(0, 0, C);    transformationMatrix.set_Matrix(0, 1, S);    transformationMatrix.set_Matrix(0, 2, 0);    transformationMatrix.set_Matrix(0, 3, 0);
		transformationMatrix.set_Matrix(1, 0, -S);   transformationMatrix.set_Matrix(1, 1, C);    transformationMatrix.set_Matrix(1, 2, 0);    transformationMatrix.set_Matrix(1, 3, 0);
		transformationMatrix.set_Matrix(2, 0, 0);    transformationMatrix.set_Matrix(2, 1, 0);    transformationMatrix.set_Matrix(2, 2, C);    transformationMatrix.set_Matrix(2, 3, S);
		transformationMatrix.set_Matrix(3, 0, 0);    transformationMatrix.set_Matrix(3, 1, 0);    transformationMatrix.set_Matrix(3, 2, -S);   transformationMatrix.set_Matrix(3, 3, C);
		
		return transformationMatrix;
	}
	
	public Vector get_Equations() {
		
		Vector vector = new Vector(4);
		
		vector.set_Vector(0, this.get_Node_i().get_Equations()[0]);
		vector.set_Vector(1, this.get_Node_i().get_Equations()[1]);
		vector.set_Vector(2, this.get_Node_j().get_Equations()[0]);
		vector.set_Vector(3, this.get_Node_j().get_Equations()[1]);
		
		return vector;
	}
	
	public Vector getEquivalentLoad() {
		Vector eqLoads = new Vector(4);
		return eqLoads;
	}
	
	public Vector getEndForce() {
		Vector d = new Vector(4);
		d.set_Vector(0, this.get_Node_i().get_Displacements(0));
		d.set_Vector(1, this.get_Node_i().get_Displacements(1));
		d.set_Vector(2, this.get_Node_j().get_Displacements(0));
		d.set_Vector(3, this.get_Node_j().get_Displacements(1));
		
		d = Matrix.Product(this.CalculateTransformationMatrix(), d);
		Vector localForces = this.CalculateLocalStiffnessMatrix().Product(d);
		localForces.Add(this.getEquivalentLoad());
		
		return localForces;
	}
	
}
