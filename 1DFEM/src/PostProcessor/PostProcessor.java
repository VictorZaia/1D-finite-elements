package PostProcessor;

import java.io.FileWriter;
import java.io.IOException;
import Model.*;
import Processor.Assembler;
import Util.FileUtilities;

public class PostProcessor {
	
	public static void printResults(Model model) {
		FileWriter f_out;
		Assembler assembler;
		assembler = new Assembler(model);
		
		//Criaçao do .txt de saida
		try {
			f_out = FileUtilities.openFileWriter("C:\\Users\\Victo\\Documents\\output.txt");
			f_out.write("TRABALHO DE COMPUTAÇÃO APLICADA" + "\n");
			assembler.CalculateEquations();
			f_out.write(model.toString() + "\n");
			f_out.write("Matriz de Rigidez Reduzida\n");
			f_out.write(assembler.CalculateKuu().toString() + "\n\n");
			f_out.write("Matriz de Rigidez Kup\n");
			f_out.write(assembler.CalculateKup().toString() + "\n\n");
			f_out.write("Matriz de Rigidez kpu\n");
			f_out.write(assembler.CalculateKpu().toString() + "\n\n");
			f_out.write("Matriz de Rigidez Kpp\n");
			f_out.write(assembler.CalculateKpp().toString() + "\n\n");
			f_out.write("Matriz Fp\n");
			f_out.write(assembler.CalculateFp().toString() + "\n\n");
			f_out.write("Matriz Dp\n");
			f_out.write(assembler.CalculateDp().toString() + "\n\n");
			f_out.write("Deslocamentos nodais\n");
			for(int i = 0; i < model.get_number_Node(); i++) {
				f_out.write(model.get_Node(i).get_Displacements() + "\n");
			}
			f_out.write("\n");
			f_out.write("Forças de extremidade nodais\n");
			for(int i = 0; i < model.get_number_Bar(); i++) {
				f_out.write(model.get_Bar(i).getEndForce() + "\n");
			}
			f_out.close();
		}
			catch (IOException e){
				String msg = "ERRO NA ESCRITA E FECHAMENTO DO ARQUIVO!!!\n" + e.getMessage();
				System.out.println(msg); 
				e.printStackTrace();
		}
		System.out.println("-----------MODEL SOLVED-----------");
	}
	
}
