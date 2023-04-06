package AlgebraLinear;

public class LinearEquationSystem {
	
	private Matrix lower;
	private Matrix upper;
	private Vector y;
	private Vector x;
	
	public LinearEquationSystem() {
		super();
	}
	
	public Matrix getLower() {
		return lower;
	}
	
	public void setLower(Matrix lower) {
		this.lower = lower;
	}
	
	public Matrix getUpper() {
		return upper;
	}
	
	public Vector getY() {
		return y;
	}
	
	public void setY(Vector y) {
		this.y = y;
	}
	
	public Vector getX() {
		return x;
	}
	
	public void setX(Vector x) {
		this.x = x;
	}
	
	public void setUpper(Matrix upper) {
		this.upper = upper;
	}
	
	public Vector solver( Matrix a, Vector b ) {
		// fatora a Matrix a em L e U
		this.croutFactor(a);
		
		// calcula o Vector intermediario y
		this.forwardReplace(b);
		
		//calcula o Vector de incognitas x
		this.backwardReplace();
		
		return this.getX();
	}
	
	private void forwardReplace( Vector b ) {
		int nOrder = this.getLower().get_Lins();
		this.y = new Vector(nOrder);
		this.getY().set_Vector(0, (b.get_Vector(0)/this.getLower().get_Matrix(0, 0)));
		for (int k = 1; k < nOrder; k++) {
			double value = 0;
			for (int i = 0; i < nOrder-1; i++) {
				value += (this.getLower().get_Matrix(k, i)*this.getY().get_Vector(i));
			}
			this.getY().set_Vector(k, (b.get_Vector(k)-value)/this.getLower().get_Matrix(k, k));
		}
	}
	
	private void backwardReplace() {
		int nOrder = this.getUpper().get_Lins();
		this.x = new Vector(nOrder);
		
		this.getX().set_Vector(nOrder-1, (this.getY().get_Vector(nOrder-1)/this.getUpper().get_Matrix(nOrder-1, nOrder-1)));
		
		for (int k = nOrder-2; k >= 0; k--) {
			double value = 0;
			
			for (int i = k+1; i < nOrder; i++) {
				value += (this.getUpper().get_Matrix(k, i)*this.getX().get_Vector(i));
			}
			
			this.getX().set_Vector(k, (this.getY().get_Vector(k)-value)/this.getUpper().get_Matrix(k, k));
		}
	}
	
	private void croutFactor( Matrix a ) {
		// pega o tamanho da Matrix A
		int nOrder = a.get_Lins();
		
		// aloca as Matrixes lower e upper com o tamanho de a
		this.lower = new Matrix(nOrder, nOrder);
		this.upper = new Matrix(nOrder, nOrder);
		
		// seta uii como 1
		for (int i = 0; i < nOrder; i++) {
			for (int j = 0; j < nOrder; j++) {
				if( i == j )
					this.getUpper().set_Matrix(i, j, 1);
			}
		}
		
		// etapa 1 (li1 = ai1)
		for (int i = 0; i < nOrder; i++) {
			this.getLower().set_Matrix(i, 0, a.get_Matrix(i, 0));
		}
		
		// etapa 2 (u1j = a1j/l11)
		for (int j = 1; j < nOrder; j++) {
			double value = a.get_Matrix(0, j)/this.getLower().get_Matrix(0, 0);
			this.getUpper().set_Matrix(0, j, value);
		}
		
		// repete as etapas 3 e 4
		for (int k = 1; k < nOrder; k++) {
			
			// etapa 3
			for (int i = k; i < nOrder; i++) {
				double aik = a.get_Matrix(i, k);
				double value = 0;
				
				for (int m = 0; m < nOrder-1; m++) {
					value += (this.getLower().get_Matrix(i, m)*this.getUpper().get_Matrix(m, k));
				}
				
				this.getLower().set_Matrix(i, k, aik-value);
			}
			
			// etapa 4
			for (int j = k+1; j < nOrder; j++) {
				double akj = a.get_Matrix(k, j);
				double value = 0;
				
				for (int m = 0; m < nOrder-1; m++) {
					value += this.getLower().get_Matrix(k, m)*this.getUpper().get_Matrix(m, j);
				}
				
				this.getUpper().set_Matrix(k, j, (akj-value)/this.getLower().get_Matrix(k, k));
			}
		}
	}
	
}
