import java.util.regex.Pattern;


public class LongParameter {

	String file;
	int count = 0;
	int methodcount = 0;
	boolean longPL = false;
	String remaining;
	
	String pattern = "public|private|protected";
	Pattern p = Pattern.compile(pattern);

	public LongParameter(String s){
		file = s;
		testFile(file);
	}

	public void testFile(String s){
		if (s.contains("public void") || s.contains("public") ){
			testString(s);
		}
		else{
			if(methodcount == 0){
				System.out.println("File doesn't contain methods/constructors");
			}
		}
		
	}

	public void testString(String s){
		int c = s.indexOf("public");
		int p = s.indexOf("private");
		//if(c<p){
		remaining = s.substring(c);
		//}System.out.println(p);
		int ob = remaining.indexOf("(");
		int cb = remaining.indexOf(")");
		String para = remaining.substring(ob, cb);
		for(int i = 0;i<para.length();i++){
			if(para.charAt(i)==','){
				count++;
			}
		}
		
		if(count == 0){
			if(para.length()>2){
			System.out.println("Method " + (methodcount+1) + " contains 1 parameter");
			methodcount++;
			}
			else{
				System.out.println("Method " + (methodcount+1) + " contains 0 parameters");
				methodcount++;
			}
		}
		else{
			System.out.println("Method " + (methodcount+1) + " contains " + (count+1) + " parameters");
			methodcount++;
		}
		if(count>=6){
			longPL = true;
		}
		count = 0;
		remaining = remaining.substring(cb);
		testFile(remaining);
	}
	
	public boolean getResult(){
		return longPL;
	}
}
