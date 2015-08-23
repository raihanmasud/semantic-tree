package strpack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SemanticTree {
	
	List<Concept> conceptNodes = new LinkedList<Concept>();
	
	public SemanticTree(){
		
		Concept cp = new Concept("thing");
		cp.conceptParent = "nil";
		
		conceptNodes.add(cp);
		
	}
	
	
	public void readTree(String fileName) {
		
		//File file = new File(fileName);
		
		try{
		FileInputStream fis = new FileInputStream(fileName);
	    BufferedReader bR = new BufferedReader(new InputStreamReader(fis));   
		
	    String cline ="";
	    	
	    cline = bR.readLine();   // use String.split(" ");
	   
	    String cpline = "";
	    cpline = bR.readLine();
	    
	    while(cline!=null)
	    
	    {
	    	
	    	
	    	//System.out.println(cline);
	    	//System.out.println(cpline);
		    
	    	Concept cp = new Concept(cline);
	    	cp.conceptParent = cpline;
	    	String cavline = "";
	    	
	    	cavline = bR.readLine();
	    	
	    	String [] tokens = cavline.split(" ");
	    	int i=0;
	    	while(tokens.length ==2)
	    	{
		
	    		cp.attVal.put(tokens[0], tokens[1]); 
			
			
	    		//System.out.println(cavline);
	    		cavline = bR.readLine();
			
	    		if(cavline != null)
	    		{
	    		tokens = cavline.split(" ");
	    		i++;
	    		}
	    	
	    		else
	    			break;
	    	
	    	}
	    	
	    	//System.out.println(i);
	    	
	    	conceptNodes.add(cp);
	    	
	    	//cline = bR.readLine();
	    	cline = cavline; //last bR.read /cavline is the next concept 
	    	cpline = bR.readLine();
	    	
	    }
	    
	    } 
		catch(Exception e)
		{
			
			System.err.println(e.toString());
			e.printStackTrace();
			
			
			
		}
	    
	    
	}
	
	
	
	public String attributeValues(String conName){
		
		String attValue ="";
		
		Iterator i = conceptNodes.iterator();
		
		//System.out.println(conName);
		if(conName!="thing")
		while(i.hasNext())
		{
			
			Concept currentConcept = (Concept)i.next();
			
			//System.out.println(currentConcept.conceptName+ " "+ conceptName);
			
			if(currentConcept.conceptName.contains(conName))
			{
			//System.out.print("Inside");
				attValue  = attValue + "("+ conName +"(isA "+ currentConcept.getParent()+ ") ";
				
				Set attributeKeys = currentConcept.attVal.keySet();
		        Iterator it = attributeKeys.iterator();
				
		        String attribKey ="";
		        
		        while(it.hasNext())
		        {
		        	
		        	attribKey = (String)it.next();
		        	
		        	attValue += "("+attribKey+" "+currentConcept.attVal.get(attribKey)+") ";
		        	
		        }
				
		        //check for inherited attributes
		        
		        
		        	Concept parConcept = null;
		        	parConcept = new Concept(currentConcept.getParent());
		        
		        while(conceptNodes.contains(parConcept))	
		        //if(conceptNodes.contains(parConcept))
		        {
		        
		        	int indx = conceptNodes.indexOf(parConcept);
		        	Concept parent = conceptNodes.get(indx);
		        	
		        	//System.out.println("Yes parent is there "+indx+" "+parent.conceptName);	
		        	
		        	Set pAttributeKeys = parent.attVal.keySet();
			        Iterator pIt = pAttributeKeys.iterator();
					
			        String pAttribKey ="";
			        
			        while(pIt.hasNext())
			        {
			        	
			        	pAttribKey = (String)pIt.next();
			        	
			        	//System.out.println(pAttribKey);
			        	if(!currentConcept.attVal.containsKey(pAttribKey))
			        	attValue += "("+pAttribKey+" "+parent.attVal.get(pAttribKey)+") ";
			        	
			        }
		        	
		        	currentConcept = parent;
			        parConcept = new Concept(currentConcept.getParent());
		        	
		        }
		        
		        
		        
			}
			
		}
		
		if(conName == "thing")attValue =conName+" is the root of the SemanticTree";
		
		else
		attValue +=")";
		
		return attValue;
	}
	
	public boolean hasAttributeValue(String conName, String attribute, String value)
	
	{
		
		
		Iterator i = conceptNodes.iterator();
		
		//System.out.println(conName);
		
		while(i.hasNext())
		{
			
			
			
			Concept currentConcept = (Concept)i.next();
			
			//System.out.println(currentConcept.conceptName+ " "+ conceptName);
			
			//check wheter the concept has the attribute 
			if(currentConcept.conceptName.contains(conName))
			{
			//System.out.print("Inside");
				
				
				//attValue  = attValue + "("+ conName +"(isA "+ currentConcept.getParent()+ ") ";
				
				Set attributeKeys = currentConcept.attVal.keySet();
		        Iterator it = attributeKeys.iterator();
				
		        String attribKey ="";
		        
		        while(it.hasNext())
		        {
		        	
		        	attribKey = (String)it.next();
		        	
		        	if(attribKey.equals((String)attribute) && currentConcept.attVal.get(attribKey).equals(value))
		        		return true;
		        	
		        	//attValue += "("+attribKey+" "+currentConcept.attVal.get(attribKey)+") ";
		        	
		        }
				
		        //check for inherited attributes
		        
		        
		        Concept parConcept = new Concept(currentConcept.getParent());
		        while(conceptNodes.contains(parConcept))		
		        //if(conceptNodes.contains(parConcept))
		        {
		        
		        	int indx = conceptNodes.indexOf(parConcept);
		        	Concept parent = conceptNodes.get(indx);
		        	
		        	//System.out.println("Yes parent is there "+indx+" "+parent.conceptName);	
		        	
		        	Set pAttributeKeys = parent.attVal.keySet();
			        Iterator pIt = pAttributeKeys.iterator();
					
			        String pAttribKey ="";
			        
			        while(pIt.hasNext())
			        {
			        	
			        	pAttribKey = (String)pIt.next();
			        	
			        	
			        	
			        	//System.out.println(pAttribKey);
			        	if(!currentConcept.attVal.containsKey(pAttribKey))
			        		if(pAttribKey.equals(attribute) && parent.attVal.get(pAttribKey).equals(value))
				        		return true;
			        	
			        }
		        	
			        currentConcept = parent;
			        parConcept = new Concept(currentConcept.getParent());
		        	
		        	
		        }
		        
		        
		        
			}
			
		}
		
		
		
		
		return false;
		
	}//end of hasAttributeValue

}//end of class
