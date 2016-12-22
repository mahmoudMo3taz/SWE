
public class MCQ_Games extends Question {

	public MCQ_Games(String question,String tanswer,String answer1,String answer2,String answer3,String answer4) {
		this.question=question;
		this.tanswer=tanswer;
		answers=new String[4];
		answers[0]=answer1;
		answers[1]=answer2;
		answers[2]=answer3;
		answers[3]=answer4;
		this.Type="MCQ";
		
}
}
