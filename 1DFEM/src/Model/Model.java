package Model;

/**
 * Esta classe descreve um obejeto modelo.
 *
 * @author Victor Emanoel
 * @version 1.0
 * @since 12/05/2022
 */

public class Model {
	
	/**
     * @param list_node
     * lista de nós do modelo.
     * @param list_material
     * lista de materiais do modelo.
     * @param list_croos_section
     * lista de seções tranversais do modelo.
     * @param list_bar
     * lista de elementos.
     */
	private Node [] list_node = null;
	private Material [] list_material = null;
	private CrossSection [] list_cross_section = null;
	private Bar [] list_bar = null;
	private String modelType = null;
	private int ndofPerNode;
	
	//Construtores
	/**
	 * Construtor sem argumento.
	 */
	public Model() {
		this.list_node = new Node[0];
		this.list_material = new Material[0];
		this.list_cross_section = new CrossSection[0];
		this.list_bar = new Bar[0];
		this.modelType = "Sem tipo";
	}
	
	/**
	 * Construtor com argumento.
     * @param n_node
     * Numero de nó do modelo.
     * @param n_mat
     * Numero de materiais do modelo.
     * @param n_sec
     * Numero de seçoes transversais do modelo.
     * @param n_bar
     * Numero de barras do modelo.
     * @param modelType
     * Tipo de barra do modelo.
	 */
	public Model(int n_node, int n_mat, int n_sec, int n_bar, String modelType) {
		this.list_node = new Node[n_node];
		for(int i =0; i < n_node; i++) {
			list_node [i] = new Node();
		}
		
		this.list_material = new Material[n_mat];
		for(int i =0; i < n_mat; i++) {
			list_material [i] = new Material();
		}
		
		this.list_cross_section = new CrossSection[n_sec];
		for(int i =0; i < n_sec; i++) {
			list_cross_section [i] = null;
		}
		
		this.list_bar = new Bar[n_bar];
		for(int i =0; i < n_bar; i++) {
			list_bar [i] = Bar.factory(modelType);
		}
		
		this.modelType = modelType;
	}

	//Getters e setters
	/**
	 * Acessa e retorna o tamanho do vetor de nós.
	 * @return tamanho do vetor list_node.
	 */
	public int get_number_Node() {
		return list_node.length;
	}
	
	/**
	 * Acessa e altera o tamanho da lista de nós.
	 * @param i
	 * Tamanho da lista.
	 */
	public void set_number_Node(int i) {
		this.list_node = new Node [i];
	}
	
	/**
	 * Acessa e retorna o tamanho do vetor de materiais.
	 * @return tamanho do vetor list_material.
	 */
	public int get_number_Material() {
		return list_material.length;
	}
	
	/**
	 * Acessa e altera o tamanho da lista de materiais.
	 * @param i
	 * Tamanho da lista.
	 */
	public void set_number_Material(int i) {
		this.list_material = new Material [i];
	}

	/**
	 * Acessa e retorna o tamanho do vetor de seções transversais.
	 * @return tamanho do vetor list_cross_section.
	 */
	public int get_number_CrossSection() {
		return list_cross_section.length;
	}
	
	/**
	 * Acessa e altera o tamanho da lista de seções
	 * @param i
	 * Tamanho da lista.
	 */
	public void set_number_CrossSection(int i) {
		this.list_cross_section = new CrossSection [i];
	}
	
	/**
	 * Acessa e retorna o tamanho do vetor de barras.
	 * @return tamanho do vetor list_bar.
	 */
	public int get_number_Bar() {
		return list_bar.length;
	}
	
	/**
	 * Acessa e altera o tamanho da lista de barras.
	 * @param i
	 * Tamanho da lista.
	 */
	public void set_number_Bar(int i) {
		this.list_bar = new Bar [i];
	}
	
	/**
	 * Acessa e retorna o vetor de nós.
	 * @return vetor list_bar.
	 */
	public Node [] get_Node() {
		return list_node;
	}
	
	/**
	 * Acessa e retorna um nós a partir de um ID.
	 * @return Nó com o ID procurado.
	 */
	public Node get_Node_ID(String ID) {
		Node node = new Node();
		for(int i = 0; i < list_node.length; i++) {
			if(list_node [i].get_ID().equals(ID)) {
				node = list_node [i];
				break;
			}
			else {
				node = null;
			}
		}
		return node;
	}
	
	/**
	 * Acessa e retorna o nó em uma certa posição.
	 * @return nó na posiçao desejada.
	 */
	public Node get_Node(int index) {
		if(index < list_node.length) {
			return list_node [index];
		}
		else {
			System.out.println("Error: Index bigger than the length of list_node");
			return null;
		}
	}

	/**
	 * Acessa e altera a lista de nós.
	 * @param list_node
	 * Vetor de nós
	 */
	public void set_Node(Node [] list_node){
		this.list_node = list_node;
	}
	
	/**
	 * Acessa e altera o nó em uma posiçao.
	 * @param index
	 * posição do nó.
	 * @param node
	 * Objeto nó.
	 */
	public void set_Node(int index, Node node) {
		if(index < this.list_node.length) {
			this.list_node [index] = node;
		}
		else {
			System.out.println("Error: Index bigger than the length of list_node");
		}
	}

	/**
	 * Acessa e retorna a lista de material.
	 * @return vetor list_material
	 */
	public Material [] get_Material() {
		return list_material;
	}
	
	/**
	 * Acessa e retorna um material a partir de um ID.
	 * @return Material com o ID procurado.
	 */
	public Material get_Material_ID(String ID) {
		Material material = new Material();
		for(int i = 0; i < list_material.length; i++) {
			if(list_material [i].get_ID().equals(ID)) {
				material = list_material [i];
				break;
			}
			else {
				material = null;
			}
		}
		return material;
	}
	
	/**
	 * Acessa e retorna o material em uma certa posição.
	 * @return Material na posiçao desejada.
	 */
	public Material get_Material(int index) {
		if(index < list_material.length) {
			return list_material [index];	
		}
		else {
			System.out.println("Error: Index bigger than the length of list_material");
			return null;
		}
	}
	
	/**
	 * Acessa e altera a lista de materiais.
	 * @param list_material
	 * Vetor de materiais
	 */
	public void set_Material(Material [] list_material) {
		this.list_material = list_material;
	}

	/**
	 * Acessa e altera o material em uma posiçao.
	 * @param index
	 * posição do material.
	 * @param material
	 * Objeto Material.
	 */
	public void set_Material(int index, Material material) {
		if(index < this.list_material.length) {
			this.list_material [index] = material;
		}
		else {
			System.out.println("Error: Index bigger than the length of list_material");
		}
	}

	/**
	 * Acessa e retorna a lista de seções transversais.
	 * @return vetor list_cross_section
	 */
	public CrossSection [] get_CrossSection() {
		return list_cross_section;
	}
	
	/**
	 * Acessa e retorna uma seção a partir de um ID.
	 * @return Seção com o ID procurado.
	 */
	public CrossSection get_CrossSection_ID(String ID) {
		CrossSection cross_section = null;
		for(int i = 0; i < list_cross_section.length; i++) {
			if(list_cross_section [i].get_ID().equals(ID)) {
				cross_section = list_cross_section [i];
				break;
			}
			else {
				cross_section = null;
			}
		}
		return cross_section;
	}
	
	/**
	 * Acessa e retorna um material a partir de um ID.
	 * @return Seção com o ID procurado.
	 */
	public CrossSection get_CrossSection(int index) {
		if(index < list_cross_section.length) {
			return list_cross_section [index];	
		}
		else {
			System.out.println("Error: Index bigger than the length of list_crosssection");
			return null;
		}
	}
	
	/**
	 * Acessa e altera a lista de seçoes transversais.
	 * @param list_cross_section
	 * Vetor de Seçoes
	 */
	public void set_CrossSection(CrossSection [] list_cross_section) {
		this.list_cross_section = list_cross_section;
	}

	/**
	 * Acessa e altera a seção em uma posiçao.
	 * @param index
	 * posição da seção.
	 * @param cross_section
	 * Objeto CrossSection
	 */
	public void set_CrossSection(int index, CrossSection cross_section) {
		if(index < list_cross_section.length) {
			this.list_cross_section [index] = cross_section;
		}
		else {
			System.out.println("Error: Index bigger than the length of list_crosssection");
		}
	}
	
	/**
	 * Acessa e retorna a lista de elementos.
	 * @return vetor list_bar
	 */
	public Bar [] get_Bar() {
		return list_bar;
	}
	
	/**
	 * Acessa e retorna o elemento em uma certa posição.
	 * @return Barra na posiçao desejada.
	 */
	public Bar get_Bar(int index) {
		if(index < list_bar.length) {
			return list_bar [index];	
		}
		else {
			System.out.println("Error: Index bigger than the length of list_bar");
			return null;
		}
	}
	
	public Bar get_Bar_ID(String ID) {
		Bar bar = null;
		for(int i = 0; i < list_bar.length; i++) {
			if(list_bar [i].get_ID().equals(ID)) {
				bar = list_bar [i];
				break;
			}
			else {
				bar = null;
			}
		}
		return bar;
	}
	
	/**
	 * Acessa e altera a lista de elementos.
	 * @param list_bar
	 * Vetor de barras
	 */
	public void set_Bar(Bar [] list_bar) {
		this.list_bar = list_bar;
	}
	
	/**
	 * Acessa e altera a barra em uma posiçao.
	 * @param index
	 * posição da barra.
	 * @param bar
	 * Objeto Bar.
	 */
	public void set_Bar(int index, Bar bar) {
		if(index < list_bar.length) {
			this.list_bar [index] = bar;
		}
		else {
			System.out.println("Error: Index bigger than the length of list_bar");
		}
	}
	
	/**
	 * Acessa e retorna o tipo de elemento.
	 * @return Tipo de elemento do modelo.
	 */
	public String get_Type() {
		return modelType;
	}

	/**
	 * Acessa e altera o tipo de elemento do modelo.
	 * @param type
	 * Tipo de elemento da barra.
	 */
	public void set_Type(String modelType) {
		this.modelType = modelType;
	}
	
	/**
	 * Acessa e retorna o numero de graus de liberdade por nó.
	 * @return Numero de graus de liberdade.
	 */
	public String get_NDOF() {
		return modelType;
	}
	
	/**
	 * Acessa e altera o numero de graus de liberdade por nó.
	 * @param type
	 * Tipo de elemento da barra.
	 */
	public void set_NDOF(int ndofPerNode) {
		this.ndofPerNode = ndofPerNode;
	}
	
	/**
	 * Acessa e retorna se existe um nó com um certo ID.
	 * @return boolean search
	 */
	public boolean search_Node(String ID) {
		boolean search = false;
		for(int i = 0; i < list_node.length; i++) {
			if(list_node [i].get_ID().equals(ID)) {
				//System.out.println("Searched Node found");
				search = true;
				break;
			}
		}
		if(!search) 
			System.out.println("Searched Node does not exist");
		return search;
	}
	
	/**
	 * Acessa e retorna se existe um material com um certo ID.
	 * @return boolean search
	 */
	public boolean search_Material(String ID) {
		boolean search = false;
		for(int i = 0; i < list_material.length; i++) {
			if(list_cross_section [i] == null) {
				System.out.println("Searched Material was not yet created");
				break;
			}
			if(list_material [i].get_ID().equals(ID)) {
				search = true;
				break;
			}
			else {
				//System.out.println("Searched Material found");
				continue;
			}
		}
		if(!search) 
			System.out.println("Searched Material does not exist");
		return search;
	}
	
	/**
	 * Acessa e retorna se existe uma seção transversal com um certo ID.
	 * @return boolean search
	 */
	public boolean search_CrossSection(String ID) {
		boolean search = false;
		for(int i = 0; i < list_cross_section.length; i++) {
			if(list_cross_section [i] == null) {
				System.out.println("Searched CrossSection was not yet created");
				break;
			}
			if(list_cross_section [i].get_ID().equals(ID)) {
				search =true;
				break;
			}
			else {
				//System.out.println("Searched CrossSection found");
				continue;
			}
		}
		if(!search) 
			System.out.println("Searched CrossSection does not exist");
		return search;
	}
	
	/**
	 * Acessa e retorna se existe um elemento com um certo ID.
	 * @return boolean search
	 */
	public boolean search_Bar(String ID) {
		boolean search = false;
		for(int i = 0; i < list_bar.length; i++) {
			if(list_cross_section [i] == null) {
				System.out.println("Searched Bar was not yet created");
				break;
			}
			if(list_bar [i].get_ID().equals(ID)) {
				search = true;
				break;
			}
			else {
				//System.out.println("Searched Bar found");
				continue;
			}
		}
		if(!search) 
			System.out.println("Searched Bar does not exist");
		return search;
	}
	
	public int calculateDOFPerNode() {
		switch(this.modelType) {
		case "Truss":
			this.ndofPerNode = 2;
			break;
		case "Beam":
			this.ndofPerNode = 2;
			break;
		case "Frame":
			this.ndofPerNode = 3;
			break;
		default:
			System.out.println("ERROR: Element type does not exist");
			System.exit(0);
		}
		return ndofPerNode;
	}
	
	//toString
		/**
		 * Sobrecarga do metodo toString.
		 * @return Uma String representativa do objeto
		 */
	public String toString () {
		String str = "===========================================================\n";
		
		str += "Model type: " + this.modelType;
		
		str += "\nNodes:\n";
		str += "=========================================\n";
		for (int i = 0; i < list_node.length; i++) {
			str += list_node [i];
		}
		str += "=========================================\n";
	
		str += "\n\nMaterials:\n";
		str += "=========================================\n";
		for (int i = 0; i < list_material.length; i++) {
			str += list_material [i];
		}
		str += "=========================================\n";
	
		str += "\n\nCross Sections:\n";
		str += "=========================================\n";
		for (int i = 0; i < list_cross_section.length; i++) {
			str += list_cross_section [i];
		}
		str += "=========================================\n";
		
		str += "\n\nBars:\n";
		str += "=========================================\n";
		for (int i = 0; i < list_bar.length; i++) {
			str += list_bar [i];
		}
		str += "=========================================\n";
		
		str += "\nNodal Forces:\n";
		str += "=========================================\n";
		for (int i = 0; i < list_node.length; i++) {
			str += this.get_Node(i).get_ID() + "\t" + this.get_Node(i).get_NodalLoads() + "\n";
		}
		str += "=========================================\n";
		
		str += "\nPoint Load:\n";
		str += "=========================================\n";
		for (int i = 0; i < this.get_number_Bar(); i++) {
			if(this.get_Bar(i).get_PointLoad() != null) {
				for(int j = 0; j < this.get_Bar(i).get_PointLoad().length; j++) {
					str += this.get_Bar(i).get_PointLoad(j) + "\n";
				}
			}
		}
		str += "=========================================\n";
		
		str += "\nDistributed Load:\n";
		str += "=========================================\n";
		for (int i = 0; i < this.get_number_Bar(); i++) {
			if(this.get_Bar(i).get_DistributedLoad() != null) {
				for(int j = 0; j < this.get_Bar(i).get_DistributedLoad().length; j++) {
					str += this.get_Bar(i).get_DistributedLoad(j) + "\n";
				}
			}
		}
		str += "=========================================\n";
		
		str += "\nRestraints:\n";
		str += "=========================================\n";
		for (int i = 0; i < list_node.length; i++) {
			str += this.get_Node(i).get_ID() + "\t";
			for(int j = 0; j < this.get_Node(i).get_Restraints().length; j++)
				str += this.get_Node(i).get_Restraints(j) + "\t";
			str += "\n";
		}
		str += "=========================================\n";
		
		str += "\nNodal Displacement:\n";
		str += "=========================================\n";
		for (int i = 0; i < list_node.length; i++) {
			str += this.get_Node(i).get_ID() + "\t" + this.get_Node(i).get_Displacements() + "\n";
		}
		str += "=========================================\n";
				
		str += "===========================================================\n";
		return str;
	}
	
}