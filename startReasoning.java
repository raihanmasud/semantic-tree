package strpack;

public class startReasoning {
	
	public static void main(String []args)
	{
		SemanticTree st = new SemanticTree();
		st.readTree("./semantictree.in");
		
		
		//System.out.println(st.attributeValues("thing"));
				
		System.out.println(st.attributeValues("plant"));
		
		System.out.println(st.hasAttributeValue("plant", "breathes", "yes"));
		
		System.out.println(st.hasAttributeValue("plant", "heart", "no"));
		
		System.out.println(st.hasAttributeValue("plant", "heart", "yes"));
		
		System.out.println(st.attributeValues("cat"));
		
		System.out.println(st.hasAttributeValue("cat", "skin", "yes"));
		
		System.out.println(st.attributeValues("livingthing"));
		
		System.out.println(st.hasAttributeValue("livingthing", "skin", "yes"));
		
		System.out.println(st.attributeValues("nonlivingthing"));
		
		System.out.println(st.hasAttributeValue("nonlivingthing", "skin", "yes"));
		
		System.out.println(st.attributeValues("chair"));
		
		System.out.println(st.hasAttributeValue("chair", "legs", "4"));
	
		System.out.println(st.attributeValues("orchid"));
		
		System.out.println(st.hasAttributeValue("orchid", "breathes", "yes"));
		
		
		
		
	}
}
