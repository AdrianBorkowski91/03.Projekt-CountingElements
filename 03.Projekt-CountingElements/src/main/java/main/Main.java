package main;

import javax.swing.JOptionPane;

import factory.FactoryMolecule;

public class Main {

	public static void main(String[] args) {
		String formula= JOptionPane.showInputDialog("Enter a chemical formula");
		
		FactoryMolecule fm= new FactoryMolecule(formula);
		fm.divideElements();
		fm.showMolecules();
	}


}
