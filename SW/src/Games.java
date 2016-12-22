import java.util.Vector;

public class Games {
	public String gameName;
	public Vector<Question> ques=new Vector<Question> () ;
	Games(){
		
	}
	Games(String name,Vector<Question> vec){
		gameName=name;
		ques=vec;
	}
	
}
