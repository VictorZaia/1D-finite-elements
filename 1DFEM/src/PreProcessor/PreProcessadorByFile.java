package PreProcessor;

import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import AlgebraLinear.*;
import Model.*;
import StructuralLoad.*;
import Util.*;

public class PreProcessadorByFile {
	
	/**
     * @param model
     * Classe modelo inicializada para armazenar as informaçoes.
     */
	private static Model model = new Model();
	
	/**
	 * Metodo que cria um objeto tipo Node.
	 */
	public static Node Fill_Node(FileReader fr) {
		Node node = new Node();
		try {
			String line = FileUtilities.leLinha(fr);
			StringTokenizer st = new StringTokenizer(line);
			node.set_ID(st.nextToken());
			node.set_Coord_x(Double.parseDouble(st.nextToken()));
			node.set_Coord_y(Double.parseDouble(st.nextToken()));
			node.set_Coord_z(Double.parseDouble(st.nextToken()));
		}
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		return node;
	}
	
	public static void Fill_Nodes(FileReader fr) {
		try {
			FileUtilities.leLinha(fr);
			for (int i = 0; i < model.get_number_Node(); i++) {
				model.set_Node(i,Fill_Node(fr));
			}
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		boolean aux = Check_Nodes(model.get_Node());
		if(!aux) {
			System.exit(0);
		}
	}
	
	/**
	 * Metodo que Verifica a existencia de nós com ID repetidos e nós coincidentes.
	 */
	public static boolean Check_Nodes(Node [] list_node) {
		boolean verif_ID = false, verif_coord = true, verif = false;
		//Verifica a existencia de nós com ID duplicada.
		for (int i = 0; i < list_node.length; i++) {
			for( int j = 0; j < list_node.length; j++) {
				if(j == i) {
					continue;
				}
				else {
					if(list_node [i].get_ID().equals(list_node[j].get_ID())) {
						System.out.println("ERROR: Duplicated ID.");
						System.out.println("ID from position " + i + " equals the ID from position " + j);
						break;
					}
					else 
						verif_ID = true;
				}
			}
		}
		//Verifica a existencia de nó coincidentes.
		for (int i = 0; i < list_node.length; i++) {
			for (int j = 0; j < list_node.length; j++) {
				if(j == i) {
					continue;
				}
				else {
					if (list_node [i].get_Coord_x() == list_node[j].get_Coord_x()) {
						if (list_node [i].get_Coord_y() == list_node[j].get_Coord_y()) {
							if (list_node [i].get_Coord_z() == list_node[j].get_Coord_z()) {
								System.out.println("ERROR: Coincident Node.");
								System.out.println("Coordenaets from position " + i + " equals the Coordinates from position " + j);
								verif_coord = false;
								break;
							}
							else 
								continue;
						}
						else
							continue;
					}
					else 
						continue;
				}
			}
		}
	
	if(verif_ID && verif_coord)
		verif = true;
	
	return verif;
	}
	
	public static Material Fill_Material(FileReader fr) {
		Material material = new Material();
		try {
			String line = FileUtilities.leLinha(fr);
			StringTokenizer st = new StringTokenizer(line);
			material.set_ID(st.nextToken());
			material.set_E(Double.parseDouble(st.nextToken()));
			material.set_nu(Double.parseDouble(st.nextToken()));
		}
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		return material;
	}
	
	public static void Fill_Materials(FileReader fr) {
		try {
			FileUtilities.leLinha(fr);
			for (int i = 0; i < model.get_number_Material(); i++) {
				model.set_Material(i,Fill_Material(fr));
			}
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		Check_Materials(model.get_Material());
	}
	
	/**
	 * Metodo que Verifica a existencia de materiais com ID repetidos.
	 */
	public static void Check_Materials(Material [] list_material) {
		for (int i = 0; i < list_material.length; i++) {
			for (int j = 0; j < list_material.length; j++) {
				if(j == i) {
					continue;
				}
				else {
					if (list_material [i].get_ID().equals(list_material[j].get_ID())) {
						if (list_material [i].get_E() == list_material[j].get_E()) {
							if (list_material [i].get_nu() == list_material[j].get_nu()) {
								System.out.println("WARNING: Coincident Material.");
								System.out.println("Material from position " + i + " equals the Material from position " + j);
								break;
							}
							else 
								continue;
						}
						else
							continue;
					}
					else 
						continue;
				}
			}
		}
	}
	
	public static CrossSection Fill_CrossSection(FileReader fr) {
		CrossSection cross_section = null;
		String ID, secType;
		try {
			String line = FileUtilities.leLinha(fr);
			StringTokenizer st = new StringTokenizer(line);
			ID = st.nextToken();
			secType = st.nextToken();
			cross_section = CrossSection.factory(ID, secType, st);
		}
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		return cross_section;
	}
	
	public static void Fill_CrossSections(FileReader fr) {
		try {
			FileUtilities.leLinha(fr);
			for (int i = 0; i < model.get_number_CrossSection(); i++) {
				model.set_CrossSection(i,Fill_CrossSection(fr));
			}
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		Check_CrossSection(model.get_CrossSection());
	}
	
	/**
	 * Metodo que Verifica a existencia de seçoes com ID repetidos.
	 */
	public static void Check_CrossSection(CrossSection [] list_cross_section) {
		for (int i = 0; i < list_cross_section.length; i++) {
			for (int j = 0; j < list_cross_section.length; j++) {
				if(j == i) {
					continue;
				}
				else {
					if (list_cross_section [i].get_ID().equals(list_cross_section [j].get_ID())) {
						if (list_cross_section [i].get_Area() == list_cross_section [j].get_Area()) {
							if (list_cross_section [i].get_Inertia() == list_cross_section [j].get_Inertia()) {
								System.out.println("WARNING: Coincident Cross Section.");
								System.out.println("Cross Section from position " + i + " equals the Cross Section from position " + j);
								break;
							}
							else 
								continue;
						}
						else
							continue;
					}
					else 
						continue;
				}
			}
		}
	}
	
	public static Bar Fill_Bar(FileReader fr) {
		Bar bar = Bar.factory(model.get_Type());
		int nLP, nLQ;
		try {
			String line = FileUtilities.leLinha(fr);
			StringTokenizer st = new StringTokenizer(line);
			bar.set_ID(st.nextToken());
			bar.set_Node_i(model.get_Node_ID(st.nextToken()));
			bar.set_Node_j(model.get_Node_ID(st.nextToken()));
			bar.set_Material(model.get_Material_ID(st.nextToken()));
			bar.set_CrossSection(model.get_CrossSection_ID(st.nextToken()));
			nLP = Integer.parseInt(st.nextToken());
			bar.set_PointLoad(nLP);
			nLQ = Integer.parseInt(st.nextToken());
			bar.set_DistributedLoad(nLQ);
			
		}
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		return bar;
	}
	
	public static void Fill_Bars(FileReader fr) {
		try {
			FileUtilities.leLinha(fr);
			for (int i = 0; i < model.get_number_Bar(); i++) {
				model.set_Bar(i, Fill_Bar(fr));
			}
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		boolean aux = Check_Bar(model.get_Bar());
		if(!aux) {
			System.exit(0);
		}
	}
	
	/**
	 * Metodo que Verifica a existencia de barras com ID repetidos e barras com nós iguais.
	 */
	public static boolean Check_Bar(Bar [] list_bar) {
		boolean verif_ID = true, verif_nodes_list = true, verif = false;
		
		//Verifica a existencia de barras com ID duplicadas.
		for (int i = 0; i < list_bar.length; i++) {
			for( int j = 0; j < list_bar.length; j++) {
				if(j == i) {
					continue;
				}
				else {
					if(list_bar [i].get_ID().equals(list_bar [j].get_ID())) {
						System.out.println("ERROR: Duplicated ID.");
						System.out.println("ID from  bar " + i + " equals the ID from bar " + j);
						verif_ID = false;
						break;
					}
				}
			}
		}
		
		//Verifica a existencia de barras com nós iguais.
		for (int i = 0; i < list_bar.length; i++) {
			for( int j = 0; j < list_bar.length; j++) {
				if(j == i) {
					continue;
				}
				else {
					if(list_bar [i].get_Node_i() == list_bar [j].get_Node_i()) {
						if(list_bar [i].get_Node_j() == list_bar [j].get_Node_j()) {
							System.out.println("ERROR: Duplicated bar.");
							System.out.println("Bars " + i + " has the same nodes as Bar " + j);
							verif_nodes_list = false;
							break;
						}
						else
							continue;
					}
					else 
						continue;
				}
			}
		}
		
		if(verif_ID && verif_nodes_list)
			verif = true;
		
		return verif;
	}
	
	public static Vector Fill_Force(FileReader fr) {
		Vector loads = new Vector(model.calculateDOFPerNode());
		try {
			String line = FileUtilities.leLinha(fr);
			StringTokenizer st = new StringTokenizer(line);
			if(st.countTokens() == model.calculateDOFPerNode() + 1) {
				st.nextToken();
				for(int i = 0; i < model.calculateDOFPerNode(); i++) {
				
				loads.set_Vector(i, Double.parseDouble(st.nextToken()));
				}
			}
			else {
				System.out.println("ERROR: The number of load per nodes is wrong.");
				System.exit(0);
			}
		}
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		return loads;
	}
	
	public static void Fill_Forces(FileReader fr) {
		try {
			FileUtilities.leLinha(fr);
			FileUtilities.leLinha(fr);
			for (int i = 0; i < model.get_number_Node(); i++) {
				model.get_Node(i).set_NodalLoads(Fill_Force(fr));
			}
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
	}
	
	public static void Fill_PointLoads(FileReader fr) {
		int nLoads;
		try {
			FileUtilities.leLinha(fr);
			String line = FileUtilities.leLinha(fr);
			StringTokenizer st = new StringTokenizer(line);
			nLoads = Integer.parseInt(st.nextToken());
			if(nLoads > 0)
				FileUtilities.leLinha(fr);
			for (int i = 0; i < nLoads; i++) {
				PointLoad pointLoad = new PointLoad();
				line = FileUtilities.leLinha(fr);
				st = new StringTokenizer(line);
			
				pointLoad.set_ID(st.nextToken());
				pointLoad.set_ID_Bar(st.nextToken());
				pointLoad.set_Position(Double.parseDouble(st.nextToken()));
				pointLoad.set_Fx(Double.parseDouble(st.nextToken()));
				pointLoad.set_Fy(Double.parseDouble(st.nextToken()));
				pointLoad.set_Mz(Double.parseDouble(st.nextToken()));
				Bar bar = model.get_Bar_ID(pointLoad.get_ID_Bar());
				bar.AddPointLoad(pointLoad);
			}
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
	}
	
	public static void Fill_DistributedLoads(FileReader fr) {
		int nLoads;
		try {
			FileUtilities.leLinha(fr);
			String line = FileUtilities.leLinha(fr);
			StringTokenizer st = new StringTokenizer(line);
			nLoads = Integer.parseInt(st.nextToken());
			if(nLoads > 0)
				FileUtilities.leLinha(fr);
			for (int i = 0; i < nLoads; i++) {
				DistributedLoad distributedLoad = new DistributedLoad();
				line = FileUtilities.leLinha(fr);
				st = new StringTokenizer(line);
			
				distributedLoad.set_ID(st.nextToken());
				distributedLoad.set_ID_Bar(st.nextToken());
				distributedLoad.set_InitialPosition(Double.parseDouble(st.nextToken()));
				distributedLoad.set_FinalPosition(Double.parseDouble(st.nextToken()));
				distributedLoad.set_QInitial(Double.parseDouble(st.nextToken()));
				distributedLoad.set_QFinal(Double.parseDouble(st.nextToken()));
				Bar bar = model.get_Bar_ID(distributedLoad.get_ID_Bar());
				bar.AddDistributedLoad(distributedLoad);
			}
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
	}
	
	public static boolean[] Fill_Restraint(FileReader fr) {
		boolean [] restraint = new boolean[model.calculateDOFPerNode()];
		try {
			String line = FileUtilities.leLinha(fr);
			StringTokenizer st = new StringTokenizer(line);
			if(st.countTokens() == model.calculateDOFPerNode() + 1) {
				st.nextToken();
				for(int i = 0; i < model.calculateDOFPerNode(); i++) {
					restraint[i] = Boolean.parseBoolean(st.nextToken());
				}
			}
			else {
				System.out.println("ERROR: The number of restraints per nodes is wrong.");
				System.exit(0);
			}
		}
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		return restraint;
	}
	
	public static void Fill_Restraints(FileReader fr) {
		try {
			FileUtilities.leLinha(fr);
			FileUtilities.leLinha(fr);
			for (int i = 0; i < model.get_number_Node(); i++) {
				model.get_Node(i).set_Restraints(Fill_Restraint(fr));
			}
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
	}
	
	public static Vector Fill_Displacement(FileReader fr) {
		Vector displacement = new Vector(model.calculateDOFPerNode());
		try {
			String line = FileUtilities.leLinha(fr);
			StringTokenizer st = new StringTokenizer(line);
			if(st.countTokens() == model.calculateDOFPerNode() + 1) {
				st.nextToken();
				for(int i = 0; i < model.calculateDOFPerNode(); i++) {
				displacement.set_Vector(i, Double.parseDouble(st.nextToken()));
				}
			}
			else {
				System.out.println("ERROR: The number of displacements per nodes is wrong.");
				System.exit(0);
			}
		}
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
		return displacement;
	}
	
	public static void Fill_Displacements(FileReader fr) {
		try {
			FileUtilities.leLinha(fr);
			FileUtilities.leLinha(fr);
			for (int i = 0; i < model.get_number_Node(); i++) {
				model.get_Node(i).set_Displacements(Fill_Displacement(fr));
			}
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
	}
	
	public static void Initialize_model(FileReader fr) {
		int n_nodes, n_sec, n_mat, n_bars;
		String type, line;
		StringTokenizer st;
		
		try {
		FileUtilities.leLinha(fr);
		FileUtilities.leLinha(fr);
		line = FileUtilities.leLinha(fr);
		st = new StringTokenizer(line);
		n_nodes = Integer.parseInt(st.nextToken());
		model.set_number_Node(n_nodes);
		n_mat = Integer.parseInt(st.nextToken());
		model.set_number_Material(n_mat);
		n_sec = Integer.parseInt(st.nextToken());
		model.set_number_CrossSection(n_sec);
		n_bars = Integer.parseInt(st.nextToken());
		model.set_number_Bar(n_bars);
		type = st.nextToken();
		model.set_Type(type);
		}
		catch(Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
		}
	}
	
	public static Model Fill_Model() {
		String path;
		FileReader fr;
		
		System.out.println("Select input file:");
		path = FileUtilities.getFileName();
		fr = FileUtilities.createFileReader(path);
		Initialize_model(fr);
		Fill_Nodes(fr);
		Fill_Materials(fr);
		Fill_CrossSections(fr);
		Fill_Bars(fr);
		Fill_Forces(fr);
		Fill_PointLoads(fr);
		Fill_DistributedLoads(fr);
		Fill_Restraints(fr);
		Fill_Displacements(fr);
		try {
			fr.close();
		}
		catch (IOException e){
			String msg = "ERRO NO FECHAMENTO DO ARQUIVO!!!\n" + e.getMessage();
			System.out.println(msg); 
			e.printStackTrace();
		}
		System.out.println("-----------MODEL READ-----------");
		return model;
	}
	
}
