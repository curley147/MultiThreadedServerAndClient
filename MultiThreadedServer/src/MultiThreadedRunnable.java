import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiThreadedRunnable implements Runnable {
	
	Socket clientSocket = null;
	//constructors
	public MultiThreadedRunnable() {

	}
	public MultiThreadedRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	public void run(){
		//thread logic
		try {
			//creating buffered reader and print writer to communicate messages to client
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			//register or login menu
			String choice;
			do{
				choice = in.readLine();
				//register
				if(choice.equals("1")) {
					Person p = new Person();
					String fullName = in.readLine();
					p.setName(fullName);
					String name = fullName.replaceAll("\\s+","");
					p.setPassword(in.readLine());
					String fullAdd = in.readLine();
					p.setAddress(fullAdd);
					String address = fullAdd.replaceAll("\\s+","");
					p.setPpsNum(in.readLine());
					int age = Integer.parseInt(in.readLine());
					p.setAge(age);
					double weight = Double.parseDouble(in.readLine());
					p.setWeight(weight);
					double height = Double.parseDouble(in.readLine());
					p.setHeight(height);
					
					try {
						//creating file and calling it users name
						String file = p.getName().toString();
						BufferedWriter bw = new BufferedWriter(new FileWriter(file + ".txt", true));
						//variable to write to file
						PrintWriter outfile = new PrintWriter(bw);
						//writing persons details to file to register
						outfile.println(name + " " + p.getPassword() + " " + address + " " + p.getPpsNum() + " " + p.getAge() + " " + p.getWeight() + " " + p.getHeight());
						out.println("person added");
						bw.close();
						outfile.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//login
				else if (choice.equals("2")) {
					String name = in.readLine();
					String password = in.readLine();
					//checking if users file exists
					boolean check = new File(name+".txt").exists();
					//if file exists
					if(check == true) {
						 System.out.println("check returns true");
						 String file = name+".txt";
						 //creating reader to read the file
						 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
						 String line = null;
						 while((line = br.readLine())!= null ){
							 //debugging sys out
							 	System.out.println("Line = " + line);
							 	 // \\s+ means any number of white spaces between words
								 String[] arrayLine = line.split("\\s+");
								 if(arrayLine.length <= 1) {
									 System.out.println("arrayLine length < 1");
									 break;
								 }
								 else {
									 //checking password given is equal to password saved in file
									 if(password.equals(arrayLine[1])){
										System.out.println("test: password equal");
										out.println("ok"); 
										//creating instance of person class with attribute values taken from file
										Person p = new Person(arrayLine[0], arrayLine[1], arrayLine[2], arrayLine[3], Integer.parseInt(arrayLine[4]), Double.parseDouble(arrayLine[5]),Double.parseDouble(arrayLine[6]));
										//sending persons details to client
										out.println(p.getName());
										out.println(p.getAddress());
										out.println(p.getPpsNum());
										out.println(p.getAge());
										out.println(p.getWeight());
										out.println(p.getHeight());
								 }	
							}
						}
						br.close();
						//inner menu
						String menu2Choice;
						do {
							//creating writer to write records to file
							BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
							PrintWriter outfile = new PrintWriter(bw);
							menu2Choice = in.readLine();
							switch(menu2Choice) {
							   case "1" :
								   //write fitness record to file
								  String fRecNum = in.readLine();
								  String fitType = in.readLine();
								  String duration = in.readLine();
								  outfile.println(fRecNum + " FT "+ fitType + " FD "+ duration);
								  bw.close();
								  outfile.close();
							      break; 
							   
							   case "2" :
							      //add meal record to file
								   String mRecNum = in.readLine();
								   String mealType = in.readLine();
								   String mealDesc = in.readLine();
								   outfile.println(mRecNum + " MT "+ mealType + " MD "+ mealDesc );
								   bw.close();
								   outfile.close();
							      break; 
							   case "3" :
								      //view last 10 records
								   BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
								   String line2 = null;
								   while((line2 = br2.readLine()) != null) {
									   //debugging sys out
									   System.out.println("Line2 = " + line2);
									   //splitting line into array to access individual values
									   String[] arrayLine2 = line2.split("\\s+");
									   //using FT and MT as indicators in file as to whether record is fitness or meal
									   if(line2.contains("FT")==true) {
										 System.out.println("f");
										 out.println("f");
										 out.println(arrayLine2[0]);
										 out.println(arrayLine2[2]);
										 out.println(arrayLine2[4]);
									   }
									   if(line2.contains("MT")==true) {
										 out.println("m");
										 out.println(arrayLine2[0]);
										 out.println(arrayLine2[2]);
										 out.println(arrayLine2[4]);
											 
									   } 
								   }
								   out.println("eof");
								   br2.close();
								  break; 
							   case "4" :
								      //view last 10 fitness records
									   BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
									   String line3 = null;
									   while((line3 = br3.readLine()) != null) {
										   System.out.println("Line3 = " + line3);
										   if(line3.contains("FT")==true) {
											   out.println("f");
											   String[] arrayLine3 = line3.split("\\s+");
											   //sending only values the client needs to print out to user
											   out.println(arrayLine3[0]);
											   out.println(arrayLine3[2]);
											   out.println(arrayLine3[4]);
										   }
									   }
									   out.println("eof");
									   br3.close();
							      break; 
							   case "5" : 
								   		String recNum = in.readLine();
								   		String type = in.readLine();
								   		String uType = type.toUpperCase();
								   		
							            File inFile = new File(file);
							            if (!inFile.isFile()) {
							                System.out.println("Parameter is not an existing file");
							                return;
							            }
							            //Construct the new file that will later be renamed to the original filename. 
							            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
							            
							            try(PrintWriter pw = new PrintWriter(new FileWriter(tempFile)))
							               {
							                   try(BufferedReader br4 = new BufferedReader(new FileReader(file)))                
							                   {
									            String line4 = null;
									            //Read from the original file and write to the new 
									            //unless content matches data to be removed.
									            while ((line4 = br4.readLine()) != null) {
									            	System.out.println("line 4 =" + line4);
									                if (!line4.contains(recNum) || !line4.contains(uType)) {
									                	System.out.println("line doesnt contain recNUm an type");
									                    pw.println(line4);
									                    pw.flush();
									                }
									            }
									            br4.close();
							                   } catch(IOException e) {
									            	System.out.println(e.getMessage());
									           }
							                   pw.close();
							                } catch(IOException e) {
								            	System.out.println(e.getMessage());
							                }
							            	//resources associated with file must be closed to delete file
								            bw.close();
											outfile.close();
							            	//Delete the original file
								            if (!inFile.delete()) {
								                System.out.println("Could not delete file");
								            }
								            //Rename the new file to the filename the original file had.
								            if (!tempFile.renameTo(inFile)) {
								                System.out.println("Could not rename file");
								            }
								   break; 
							   default : 
							      System.out.println("Invalid input");
							}
							bw.close();
							outfile.close();
						}while(menu2Choice.compareTo("-1")!=0);
						
					} else {
						out.println("User not found. Please register. ");
					}
					
				} else {
					out.println("Error input");
				}
				
			}while(choice.compareTo("-1")!=0);
			in.close();
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
