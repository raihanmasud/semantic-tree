package strpack;

import java.util.HashMap;

public class Concept {

	String conceptName ="";
	String conceptParent="";
	HashMap<String,String> attVal = new HashMap<String, String>();
	
	public Concept(String cName/*, String cParent*/){
		
		conceptName = cName;
		//conceptParent = cParent;
		
	}
	
public boolean equals(Object o){
		
		
		return ((Concept)o).conceptName.equals(conceptName);

		
		
	}
	
	public String getParent(){
		
		return conceptParent;
		
	}
}
