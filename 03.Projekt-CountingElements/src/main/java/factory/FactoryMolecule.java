package factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FactoryMolecule {

	private Map<String, Integer> mapElements= new HashMap<String, Integer>();
	private List<String> brackets= new ArrayList<String>();
	private String formula;
	
	public FactoryMolecule(String formula){
		this.formula=formula;
	}
	
	public Map<String, Integer> divideElements(){
		while(isBrackets());

		divideFormulaWithoutBrackets(null, 1);
		divideFormulaWithBrackets();
		
		return mapElements;
	}


	public void showMolecules(){
		for(Map.Entry<String, Integer> entry : mapElements.entrySet()) {
		    String key = entry.getKey();
		    int value = entry.getValue();
		    
		    System.out.println(key+" : "+value);
		}
	}
	
	private void divideFormulaWithoutBrackets(String form, int multipler){
		int start = 0, end=0;
		
		if(form==null)
			form=formula;
		
		for(int i=0; i<form.length(); i++){
			if(Character.isUpperCase(form.charAt(i))){
				start=i;
				try {
					if(Character.isUpperCase(form.charAt(i+1))){
						end=i;
						String element= form.substring(start, end+1);
						valueMolecules(element, multipler);
					}
				} catch (Exception e) {
					end=i;
					String element= form.substring(start, end+1);
					valueMolecules(element, multipler);			
				}
			}
			else if(Character.isLowerCase(form.charAt(i))){
				try {
					if(Character.isUpperCase(form.charAt(i+1))){
						end=i;
						String element= form.substring(start, end+1);
						valueMolecules(element, multipler);
					}
				} catch (Exception e) {
					end=i;
					String element= form.substring(start, end+1);
					valueMolecules(element, multipler);	
				}
			}
			else if(Character.isDigit(form.charAt(i))){
				try {
					if(!Character.isDigit(form.charAt(i+1))){
						end=i;
						String element= form.substring(start, end+1);
						valueMolecules(element, multipler);
					}
				} catch (Exception e) {
					end=i;
					String element= form.substring(start, end+1);
					valueMolecules(element, multipler);	
				}
				
			}}
		
	}
	
	private void divideFormulaWithBrackets() {
		for (String formula : brackets) {

			Pattern p = Pattern.compile("[)]\\d+");
			Matcher m = p.matcher(formula);
			String multiplerS;
			
			if(m.find()){
				multiplerS=formula.substring(m.start()+1, m.end());
				int multiplerInt= Integer.parseInt(multiplerS);
				
				String [] a=formula.split("[(]");
				String [] b=a[1].split("[)]");
				
				divideFormulaWithoutBrackets(b[0], multiplerInt);
				
			}
		}
	}
	
	private boolean isBrackets(){

		Pattern p = Pattern.compile("[(]");
		Matcher m = p.matcher(formula);
		Pattern p2 = Pattern.compile("[)]+\\d*");
		Matcher m2 = p2.matcher(formula);
		
		if(m.find()){
			if(m2.find()){				
				brackets.add(formula.substring(m.start(), m2.end()));

				int a=m.start();
				int b=m2.end();
				String newFormula="";
				
				if(a!=0){
					newFormula+=formula.substring(0, a);
				}
				
				newFormula+=formula.substring(b, formula.length());
				formula=newFormula;
				return true;
			}
		}
		
		return false;
	}
	
	private void valueMolecules(String form, int multipler){
		String nameS, valueS;
		int valueInt=0;
	
		nameS=form.replaceAll("[1-9]", "");
		valueS=form.replaceAll("[a-zA-Z]","");
		
		try{
			valueInt= Integer.parseInt(valueS);
		}catch(NumberFormatException e){
			valueInt=1;
		}
		
		if(mapElements.get(nameS)==null){
			mapElements.put(nameS, valueInt*multipler);
		}
		else{
			for(Map.Entry<String, Integer> entry : mapElements.entrySet()) {
			    String key = entry.getKey();
			    
			    if(key.equals(nameS)){
			    	 int value = entry.getValue();
			    	 valueInt*=multipler;
			    	 value+=valueInt;
			    	 entry.setValue(value);
			    }
			}
		}
		
	}
	
}
