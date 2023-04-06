package Aplicativo;

import java.io.*;
import AlgebraLinear.*;
import Model.*;
import PostProcessor.*;
import PreProcessor.*;
import Processor.*;
import Util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model model = null;
		
		System.out.println("-----------Welcome-----------");
		
		model = PreProcessadorByFile.Fill_Model();
		
		Processor.SolverModel(model);
		
		PostProcessor.printResults(model);
		
	}
}
