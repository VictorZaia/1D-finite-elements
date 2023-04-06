package Model;

import AlgebraLinear.Matrix;
import AlgebraLinear.Vector;
import StructuralLoad.DistributedEquivalentLoad;
import StructuralLoad.PointEquivalentLoad;

/**
 * Esta classe descreve uma barra do tipo Beam.
 *
 * @author Victor Emanoel
 * @version 1.0
 * @since 12/05/2022
 */

public class Beam extends Bar implements PointEquivalentLoad, DistributedEquivalentLoad {
	
	//Construtores
	/**
	 * Construtor sem argumento.
	 */
	public Beam() {
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
	public Beam(String ID, Node node_i, Node node_j, Material material, CrossSection crossSection) {
		super(ID, node_i, node_j, material, crossSection);
	}
	
	public Matrix CalculateLocalStiffnessMatrix() {
		Matrix stiffnessMatrix = new Matrix(4,4);
		double L = this.get_Node_i().DistanceTo(this.get_Node_j());
		double aux = this.get_Material().get_E() * this.get_CrossSection().get_Inertia() / Math.pow(L,3);
		
		stiffnessMatrix.set_Matrix(0, 0, 12);       stiffnessMatrix.set_Matrix(0, 1, 6 * L);                 stiffnessMatrix.set_Matrix(0, 2, -12);      stiffnessMatrix.set_Matrix(0, 3, 6 * L);
		stiffnessMatrix.set_Matrix(1, 0, 6 * L);    stiffnessMatrix.set_Matrix(1, 1, 4 * Math.pow(L, 2));    stiffnessMatrix.set_Matrix(1, 2, -6 * L);   stiffnessMatrix.set_Matrix(1, 3, 2 * Math.pow(L, 2));
		stiffnessMatrix.set_Matrix(2, 0, -12 * L);  stiffnessMatrix.set_Matrix(2, 1, -6 * L);                stiffnessMatrix.set_Matrix(2, 2, 12);       stiffnessMatrix.set_Matrix(2, 3, -6 * L);
		stiffnessMatrix.set_Matrix(3, 0, 6 * L);   	stiffnessMatrix.set_Matrix(3, 1, 2 * Math.pow(L, 2));   stiffnessMatrix.set_Matrix(3, 2, -6 * L);    stiffnessMatrix.set_Matrix(3, 3, 4 * Math.pow(L, 2));
		
		stiffnessMatrix.Scale(aux);
		
		return stiffnessMatrix;
	}
	
	public Matrix CalculateTransformationMatrix() {
		Matrix transformationMatrix = new Matrix(4, 4);
		
		transformationMatrix.set_Matrix(0, 0, 1);    transformationMatrix.set_Matrix(0, 1, 0);    transformationMatrix.set_Matrix(0, 2, 0);    transformationMatrix.set_Matrix(0, 3, 0);
		transformationMatrix.set_Matrix(1, 0, 0);    transformationMatrix.set_Matrix(1, 1, 1);    transformationMatrix.set_Matrix(1, 2, 0);    transformationMatrix.set_Matrix(1, 3, 0);
		transformationMatrix.set_Matrix(2, 0, 0);    transformationMatrix.set_Matrix(2, 1, 0);    transformationMatrix.set_Matrix(2, 2, 1);    transformationMatrix.set_Matrix(2, 3, 0);
		transformationMatrix.set_Matrix(3, 0, 0);    transformationMatrix.set_Matrix(3, 1, 0);    transformationMatrix.set_Matrix(3, 2, 0);    transformationMatrix.set_Matrix(3, 3, 1);
		
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
	
	public Vector getEquivalentLoadFromP() {
		Vector eqPointLoad = null;
		if (this.get_PointLoad() != null) {
			for(int i = 0; i < this.get_PointLoad().length; i++) {
				double Q = this.get_PointLoad(i).get_Fx();
				double P = this.get_PointLoad(i).get_Fy();
				double M = this.get_PointLoad(i).get_Mz();
				double l = this.get_Node_i().DistanceTo(get_Node_j());
				double a = this.get_PointLoad(i).get_Position();
				double b = (l - a);
				
				double[] aux = new double[6];
				aux[2] = P * a * b * b / (l * l) + (-M * b / (l * l) * (2 * a - b));
				aux[5] = P * a * a * b / (l * l) + (-M * a / (l * l) * (2 * b - a));
				
				double delta = (aux[2] - aux[5]) / l;
				
				aux[0] = Q * b / l;
				aux[1] = P * b / l + (-M / l) + delta;
				aux[3] = Q * a / l;
				aux[4] = (P - aux[1]);
				
				
				eqPointLoad = new Vector(aux);
			}
		}
		return eqPointLoad;
	}
	
	public Vector getEquivalentLoadFromQ() {
		Vector eqDistributedLoad = new Vector(6);
		if (this.get_DistributedLoad() != null) {
			for(int i = 0; i < this.get_DistributedLoad().length; i++) {
				double qInitial = this.get_DistributedLoad(i).get_QInitial();
				double qFinal = this.get_DistributedLoad(i).get_QFinal();
				double qm = qFinal - qInitial; //usar no calculo da carga tringular.
				double l = this.get_Node_j().DistanceTo(get_Node_i());
				
				if (qInitial != qFinal) {
					double[] aux1 = new double[6];
					double a = this.get_DistributedLoad(i).get_InitialPosition();
					double c = this.get_DistributedLoad(i).get_FinalPosition() - this.get_DistributedLoad(i).get_InitialPosition();
					double b = (l - this.get_DistributedLoad(i).get_FinalPosition());
					
					//Caso C
					aux1[0] = 0;
					aux1[3] = 0;
					aux1[2] = (qm * c / (60 * l * l)) * (10 * b * b * (3 * a + 2 * c) + c * c * (10 * b + 5 * a + 2 * c) + 20 * a * b * c);
					aux1[5] = (-qm * c / (60 * l * l)) * (10 * a * a * (3 * b + c) + c * c * (15 * b + 10 * a + 3 * c) + 40 * a * b * c);
					
					double delta1 = (aux1[2] + aux1[5]) / l;
					aux1[1] = qm * c / (2 * l) * (b + c/3) + delta1;
					aux1[4] = qm * c / 2 - aux1[1];
					
					//Caso D
					double[] aux2 = new double[6];
					
					c = this.get_DistributedLoad(i).get_FinalPosition() - this.get_DistributedLoad(i).get_InitialPosition();
					a = this.get_DistributedLoad(i).get_InitialPosition() + c/2.0;
					b = l - a;
					
					aux2[0] = 0;
					aux2[3] = 0;
					aux2[2] = qInitial * c / (12 * l * l) * (12 * a * b * b + c * c *(l - 3 * b));
					aux2[5] = -qInitial * c / (12 * l * l) * (12 * a * a * b + c * c *(l - 3 * a));
					
					double delta2 = (aux2[2] + aux2[5]) / l;
					aux2[1] = qInitial * c * b / l + delta2;
					aux2[4] = qInitial * c - aux2[1];
					
					
					double[] aux3 = new double[6];
					for(int j = 0; j < aux3.length; j++) {
						aux3[j] = aux1[j] + aux2[j];
					}
					
					Vector auxfpeq = new Vector(aux3);
					eqDistributedLoad.Add(auxfpeq);
				}
				else {
					double[] aux = new double[6];
					
					double c = this.get_DistributedLoad(i).get_FinalPosition() - this.get_DistributedLoad(i).get_InitialPosition();
					double a = this.get_DistributedLoad(i).get_InitialPosition() + c/2.0;
					double b = l - a;
					
					aux[0] = 0;
					aux[3] = 0;
					aux[2] = qInitial * c / (12 * l * l) * (12 * a * b * b + c * c *(l - 3 * b));
					aux[5] = -qInitial * c / (12 * l * l) * (12 * a * a * b + c * c *(l - 3 * a));
					
					double delta = (aux[2] + aux[5]) / l;
					aux[1] = qInitial * c * b / l + delta;
					aux[4] = qInitial * c - aux[1];
					
					Vector auxfpeq = new Vector(aux);
					eqDistributedLoad.Add(auxfpeq);
				}
			}
		}
		return eqDistributedLoad;
	}
	
	public Vector getEquivalentLoad() {
		Vector pointLoad = this.getEquivalentLoadFromP();
		Vector distLoad = this.getEquivalentLoadFromQ();
		pointLoad.Add(distLoad);
		Vector eqLoad = pointLoad;

		return eqLoad;
	}
	
	public Vector getEndForce() {
		Vector d = new Vector(6);
		d.set_Vector(0,this.get_Node_i().get_Displacements(0));
		d.set_Vector(1,this.get_Node_i().get_Displacements(1));
		d.set_Vector(2,this.get_Node_i().get_Displacements(2));
		d.set_Vector(3,this.get_Node_j().get_Displacements(0));
		d.set_Vector(4,this.get_Node_j().get_Displacements(1));
		d.set_Vector(5,this.get_Node_j().get_Displacements(2));
		
		Matrix Klocal = this.CalculateLocalStiffnessMatrix();
		Klocal = Klocal.Product(this.CalculateTransformationMatrix());
		Vector localForces = Klocal.Product(d);
		Vector fe = this.getEquivalentLoad();
		fe.Scale(-1.0);
		localForces.Add(fe);
		
		return localForces;
	}
	
}
