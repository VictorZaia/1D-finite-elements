package Util;

import java.awt.Font; // Abstract Window Toolkit - núcleo da JFC (Java Foundation Classes)
import java.io.*;
import javax.swing.JFileChooser;

public class FileUtilities {
	
	public static String getFileName(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFont(new Font("Arial", Font.PLAIN, 12));
		String path = "";
		int retVal = fileChooser.showDialog(null, "Choose File");
		if (retVal == JFileChooser.APPROVE_OPTION) {
			// Getting the file name
			String s = fileChooser.getSelectedFile().getName();
		}
		if (fileChooser.getSelectedFile() != null) {
			path = fileChooser.getSelectedFile().getAbsolutePath();
		}
		fileChooser.setVisible(false);
		return path;
		}
	
	public static FileReader createFileReader(String fileName){
		FileReader fi = null;
		try {
			fi = new FileReader(fileName);		// Construtor da instância a um arquivo de leitura
		} 
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
			System.out.println(msg);
		}
		return fi;
	}
	
	public static FileWriter openFileWriter(String fileName){
		FileWriter fout = null;
		try {
			fout = new FileWriter(fileName);		// Construtor da instância a um arquivo de escrita
		} 
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
			System.out.println(msg);
		}
		return fout;
	}
	
	public static String findLineStartingWithString(FileReader fi, String s) {
		String temp = "";
		do {
			try {
				temp = leLinha(fi);
			} catch (Exception e) {
				return null;
			}
		} while (!temp.startsWith(s));
		return temp;
	}
	
	public static String leLinha(FileReader fi) throws Exception {
		try {
			String temp = "";
			int ret = 0;
			char tc;
			ret = fi.read();
			do {
				tc = (char) ret;
				temp += tc;
				ret = fi.read();			
			} 
			while ((ret != '\n') && (ret != -1)); 
			if (ret!=-1)
				return temp;
			else {
				System.out.println("Fim do arquivo");
				return temp;
			}
		}
		catch (Exception e) {
			String msg = "ERRO DE LEITURA DE DADOS!!!\n" + e.getMessage();
			throw new Exception(msg);
		}
	}
	
}
