package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import factory.FactoryMolecule;

public class Test {

	@org.junit.Test
	public void testA() {
		Map<String, Integer> mapElementsPattern= new HashMap<String, Integer>();
		mapElementsPattern.put("C", 1);
		mapElementsPattern.put("Cl", 2);
		mapElementsPattern.put("F", 2);
		
		FactoryMolecule fm= new FactoryMolecule("CCl2F2");
		Map<String, Integer> mapElements=fm.divideElements();

		Assert.assertTrue(testMap(mapElements, mapElementsPattern));
	}

	@org.junit.Test
	public void testB() {
		Map<String, Integer> mapElementsPattern= new HashMap<String, Integer>();
		mapElementsPattern.put("Na", 1);
		mapElementsPattern.put("H", 1);
		mapElementsPattern.put("C", 1);
		mapElementsPattern.put("O", 3);
		
		FactoryMolecule fm= new FactoryMolecule("NaHCO3");
		Map<String, Integer> mapElements=fm.divideElements();

		Assert.assertTrue(testMap(mapElements, mapElementsPattern));
	}
	
	@org.junit.Test
	public void testC() {
		Map<String, Integer> mapElementsPattern= new HashMap<String, Integer>();
		mapElementsPattern.put("C", 4);
		mapElementsPattern.put("H", 10);
		mapElementsPattern.put("O", 2);
		
		FactoryMolecule fm= new FactoryMolecule("C4H8(OH)2");
		Map<String, Integer> mapElements=fm.divideElements();

		Assert.assertTrue(testMap(mapElements, mapElementsPattern));
	}
	
	@org.junit.Test
	public void testD() {
		Map<String, Integer> mapElementsPattern= new HashMap<String, Integer>();
		mapElementsPattern.put("Pb", 1);
		mapElementsPattern.put("Cl", 1);
		mapElementsPattern.put("N", 2);
		mapElementsPattern.put("H", 8);
		mapElementsPattern.put("C", 2);
		mapElementsPattern.put("O", 4);

		FactoryMolecule fm= new FactoryMolecule("PbCl(NH3)2(COOH)2");
		Map<String, Integer> mapElements=fm.divideElements();

		Assert.assertTrue(testMap(mapElements, mapElementsPattern));
	}
	
	private boolean testMap(Map<String, Integer> mapElements, Map<String, Integer> mapElementsPattern){
		
		for(Map.Entry<String, Integer> entry : mapElements.entrySet()) {
		    String key = entry.getKey();
		    int value = entry.getValue();
		    
		    if(mapElementsPattern.get(key)!=value){
		    	return false;
		    }
		}
		
		return true;
	}
}
