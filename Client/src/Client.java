import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		//localhost
		String hostName = "127.0.0.1";
		int portNum = 44444;
		Socket clientSocket;
		PrintWriter out;
		BufferedReader in;
		BufferedReader stdIn;
		String menuChoice;
		
		try {
			clientSocket = new Socket(hostName, portNum);
			//create I/O streams
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Client Program");
			//outer menu
			do {
				System.out.println("Enter 1 to Register, 2 to Log In or -1 to End: ");
				menuChoice = stdIn.readLine();
				out.println(menuChoice);
				if(menuChoice.equals("1")) {
					//register
					System.out.println("Please enter your name: ");
					out.println(stdIn.readLine());
					System.out.println("Please enter your password: ");
					out.println(stdIn.readLine());
					System.out.println("Please enter your address: ");
					out.println(stdIn.readLine());
					System.out.println("Please enter your PPS No.: ");
					out.println(stdIn.readLine());
					System.out.println("Please enter your age: ");
					out.println(stdIn.readLine());
					System.out.println("Please enter your weight: ");
					out.println(stdIn.readLine());
					System.out.println("Please enter your height: ");
					out.println(stdIn.readLine());
					System.out.println(in.readLine());
				}
				else if (menuChoice.equals("2")) {
					System.out.println("Please enter name: ");
					out.println(stdIn.readLine());
					System.out.println("Please enter password: ");
					out.println(stdIn.readLine());
					String okay = in.readLine();
					if(okay.equals("ok")) {
						System.out.println("Name: " + in.readLine());
						System.out.println("Address: " + in.readLine());
						System.out.println("PPS No.: " + in.readLine());
						System.out.println("Age: " + in.readLine());
						System.out.println("Weight: " +in.readLine());
						System.out.println("Height: " + in.readLine());
						
						String menu2Choice;
						do{
							System.out.println("==========MENU============");
							System.out.println("======Please Enter:=======");
							System.out.println("1) to add a fitness record");
							System.out.println("2) to add a meal record");
							System.out.println("3) to view your previous records");
							System.out.println("4) to view your previous fitness records");
							System.out.println("5) to delete a record using record number");
							System.out.println("-1) to log out");
							
							menu2Choice= stdIn.readLine();
							out.println(menu2Choice);
							switch(menu2Choice) {
							 case "1" :
								//add fitness record code
								 System.out.println("Enter Record Number:");
								 String fRecNum = stdIn.readLine();
								 out.println(fRecNum);
								 System.out.println("Enter Fitness Type(Walking, Running or Cycling):");
								 String fitType = stdIn.readLine();
								 out.println(fitType);
								 System.out.println("Enter Duration(minutes):");
								 String duration = stdIn.readLine();
								 out.println(duration);
							     break; 
							   
							   case "2" :
							      //add meal record code
								   System.out.println("Enter Record Number:");
								   String mRecNum = stdIn.readLine();
								   out.println(mRecNum);
								   System.out.println("Enter Meal Type(Carbs, Protein or Sugars):");
								   String mealType = stdIn.readLine();
								   out.println(mealType);
								   System.out.println("Enter Meal Description:");
								   String fullMealDesc = stdIn.readLine();
								   String mealDesc = fullMealDesc.replaceAll("\\s+", "");
								   out.println(mealDesc);
							      break; 
							   case "3" :	
								      //view last 10 records
								   String mOrF = "";
								   while(mOrF.compareTo("eof")!=0) {
									   	   mOrF = in.readLine();
									   	   if(mOrF.equals("f")==true) {
									   		   System.out.println("Record No.: " + in.readLine());
									   		   System.out.println("Fitness Type: " + in.readLine());
											   System.out.println("Duration : " + in.readLine());
									   	   }
									   	   else if(mOrF.equals("m")==true){
									   		   System.out.println("Record No.: " + in.readLine());
									   		   System.out.println("Meal Type: " + in.readLine());
											   System.out.println("Description : " + in.readLine());
									   	   }
										   
								   }
								   break;
							   case "4" :
								      //view last 10 fitness records
								   String isEnd = "";
								   while(isEnd.compareTo("eof")!=0) {
									   	   isEnd = in.readLine();
									   	   if(isEnd.equals("f")==true) {
									   		   System.out.println("Record No.: " + in.readLine());
									   		   System.out.println("Fitness Type: " + in.readLine());
											   System.out.println("Duration : " + in.readLine());
									   	   }
										   
								   }
								      break; 
							   case "5" :
								      //delete record using record number code
									   System.out.println("Enter Record No. to delete: ");
									   String recNum = stdIn.readLine();
									   out.println(recNum);
									   System.out.println("Enter Record type to delete(FT for fitness type record/MT for meal type record): ");
									   String type = stdIn.readLine();
									   out.println(type);
								      break;
							   default : 
							      // Statements
							}
							
						}while(menu2Choice.compareTo("-1")!=0);
					} else {
						continue;
					}
				}
			}while(menuChoice.compareTo("-1")!=0);
		}
		//System.out.println("Server msg says: " + in.readLine());
		catch(UnknownHostException e) {
			System.exit(1);
		} catch(IOException e) {
			System.exit(1);
		}
			
	}

}
