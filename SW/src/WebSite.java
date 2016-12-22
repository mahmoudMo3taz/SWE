import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class WebSite {
	static String Privilage="S";
	
	static void addStudent(){
		Scanner scan=new Scanner(System.in);
		System.out.println("enter yor first name");
		String fname=scan.nextLine();
		System.out.println("enter yor last name");
		String lname=scan.nextLine();
		System.out.println("enter yor user name");
		String username=scan.nextLine();
		System.out.println("enter yor password ");
		String password=scan.nextLine();
		System.out.println("enter your school name");
		String edu=scan.nextLine();
		DB.us.add(new Student(fname, lname, username, password, edu,"S"));
//		scan.close();
	}
	
	static void addTecher(){
		Scanner scan=new Scanner(System.in);
		System.out.println("enter yor first name");
		String fname=scan.nextLine();
		System.out.println("enter yor last name");
		String lname=scan.nextLine();
		System.out.println("enter yor user name");
		String username=scan.nextLine();
		System.out.println("enter yor password ");
		String password=scan.nextLine();
		System.out.println("enter your school name");
		String edu=scan.nextLine();
		DB.us.add(new Teacher(fname, lname, username, password, edu,"T"));
//		scan.close();
		
		
	}
	static boolean login(){
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the UserName");
		String username=scan.nextLine();
		System.out.println("Enter the Password");
		String password=scan.nextLine();
//		scan.close();
		for (User U : DB.us) {
			if(U.username.equals(username)){
				if(U.password.equals(password)){
					Privilage=U.type;
					return true;
				}
					
			}
		}
		return false;
	}
	static void addGame(){
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the  game Name");
		String gameName=scan.nextLine();
		System.out.println("Enter the  game Type");
		String gameType=scan.nextLine();
		int numofQues;
		Games game=new Games();
		game.gameName=gameName;
		String Ques;
		String []answers=new String[4];
		if(gameType.equalsIgnoreCase("MCQ")){
			System.out.println("Enter the num of Questions");
			numofQues=Integer.parseInt(scan.nextLine());
			for( int i =0; i<numofQues;i++){
				System.out.println("Enter the Question");
				Ques=scan.nextLine();
				System.out.println("Enter the option a)");
				answers[0]=scan.nextLine();
				System.out.println("Enter the option b)");
				answers[1]=scan.nextLine();
				System.out.println("Enter the option c)");
				answers[2]=scan.nextLine();
				System.out.println("Enter the option d)");
				answers[3]=scan.nextLine();
				System.out.println("Enter the  write option)");
				gameName=scan.nextLine();
				
				
				game.ques.add(new MCQ_Games(Ques, gameName, answers[0], answers[1], answers[2], answers[3]));
			}
		}else{
			System.out.println("Enter the num of Questions");
			numofQues=Integer.parseInt(scan.nextLine());
			for (int i = 0; i < numofQues; i++) {
				System.out.println("Enter the Question");
				Ques=scan.nextLine();
				System.out.println("Enter the  write answer T | F");
				gameName=scan.nextLine();
				if(gameName.equalsIgnoreCase("T")){
					game.ques.add(new Tf_Games(Ques, "T"));
				}else{
					game.ques.add(new Tf_Games(Ques, "F"));
				}
			}
		}
		DB.gs.add(game);
//		scan.close();
	}
	static void playGame(){
		if(DB.gs.size()<1){
			System.out.println("No games");
			return;
		}
		int Score=0;
		Games Game;
		char ch='a';
		Scanner scan=new Scanner(System.in);
		String A;
		System.out.println("Chose tha game");
		for (Games game : DB.gs) {
			System.out.println((ch++)+")l"+game.gameName);	
		}
		char choice=scan.nextLine().charAt(0);
		Game=DB.gs.get((choice-'a'));
			for(Question quos:Game.ques){
				if(quos.Type.equals("MCQ")){
					System.out.println(quos.question);
					ch='a';
					for(String ans:quos.answers){
						System.out.println((ch++)+ans);
					}
					choice=scan.nextLine().charAt(0);
					if(quos.tanswer.equals(quos.answers[choice-'a'])){
						System.out.println("Correct Answer");
						Score++;
					}else{
						System.out.println("Wrong Answer");
					}
					
				}else{
					System.out.println(quos.question+"   "+"T | F");
					A=scan.nextLine();
					if(quos.tanswer.equalsIgnoreCase(A)){
						System.out.println("Correct Answer");
						Score++;
					}else{
						System.out.println("Wrong Answer");
					}
					
				}
			}
		System.out.println(Score);
//		scan.close();
	}
	static void  WriteDB(){
		PrintWriter out=null;
		try {
			out=new PrintWriter(new File("/home/mo3taz/workspace/SW/output"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numofUSers=DB.us.size(),numofGames=DB.gs.size();
		out.println(numofUSers);
			for (User user : DB.us) {
				out.println(user.fname);
				out.println(user.lname);
				out.println(user.username);
				out.println(user.password);
				out.println(user.edu);
				out.println(user.type);
			}
			
			
		out.println(numofGames);
		for(int i=0;i<numofGames;i++){
			for (Games games : DB.gs) {
				out.println(games.gameName);
				out.println(games.ques.size());
				for (Question q : games.ques) {
					out.println(q.question);
					out.println(q.tanswer);
					out.println(q.Type);
					out.println(q.answers.length);
					for(String answer:q.answers){
						out.println(answer);
					}
					
				}
			}
			
		}
		out.close();
		
	}	
	static void readDB(){
		
		String fname,lname,userName,password,edu,Type;
		int numofUsers;
		Scanner scan =null;
		try {
			 scan = new Scanner(new File("/home/mo3taz/workspace/SW/output"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!scan.hasNextLine()){
			return;
		}
		numofUsers=Integer.parseInt(scan.nextLine());
		for( int i =0; i<numofUsers;i++){
			fname=scan.nextLine();
			lname=scan.nextLine();
			userName=scan.nextLine();
			password=scan.nextLine();
			edu=scan.nextLine();
			Type=scan.nextLine();
			if(Type.equals("T")){
				DB.us.add(new Teacher(fname, lname, userName, password, edu, Type));
				
			}else{
				DB.us.add(new Student(fname, lname, userName, password, edu, Type));
			}
		}
		int numofGames=Integer.parseInt(scan.nextLine()),numofAns,numofQs;
		String gameName,Question,tanswer,type;
		String []answer;
		Vector<Question> vec;
		for (int i = 0; i < numofGames; i++) {
			gameName=scan.nextLine();
			numofQs=Integer.parseInt(scan.nextLine());
			vec=new Vector<Question>();
			for (int j = 0; j < numofQs; j++) {
				Question=scan.nextLine();
				tanswer=scan.nextLine();
				type=scan.nextLine();
				numofAns=Integer.parseInt(scan.nextLine());
				answer=new String[numofAns];
				for (int j2 = 0; j2 < answer.length; j2++) {
					answer[j2]=scan.nextLine();
				}
				if(type.equals("TF")){
					vec.add(new Tf_Games(Question, tanswer));
				}else{
					vec.add(new MCQ_Games(Question, tanswer, answer[0], answer[1], answer[2], answer[3]));
				}
				
			}
			DB.gs.add(new Games(gameName, vec));
			
		}
	}
	public static void main(String[] args) {
		boolean logged=false;
		Scanner scan=new Scanner(System.in);
		String choice="";
	    readDB();
		while(true){
			if(!logged){
				System.out.println("Enter l for login or S for SignUp");
				choice=scan.nextLine();
				if(choice.equalsIgnoreCase("S")){
					System.out.println("Enter S for Student or T for Teacher");
					choice=scan.nextLine();	
					if(choice.equalsIgnoreCase("S")){
						addStudent();
						Privilage="S";
					}else{
						addTecher();
						Privilage="T";
					}
					logged=true;
				}else{
					if(login()){
						logged=true;
					}else{
						System.out.println("Wrong UserName or Password\nPlease Try Again");
					}
					
				}
			}else{
				if(Privilage.equals("S")){
					System.out.println("1)play a game\n2)logout");
					choice=scan.nextLine();
					if(choice.equalsIgnoreCase("1")){
						playGame();
					}else{
						logged=false;
					}
				}else{
					System.out.println("1)play a game\n2)Creat a game\n3)logout");
					choice=scan.nextLine();
					if(choice.equalsIgnoreCase("1")){
						playGame();
					}else if(choice.equalsIgnoreCase("2")){
						addGame();
					}else{
						logged=false;
					}
				}
			}
			System.out.println("Enter E for Exit Or C to Continue");
			choice=scan.nextLine();
			
			if(choice.equalsIgnoreCase("E")){
				break;
			}
		}
		WriteDB();
		scan.close();
		
	}
	
}
