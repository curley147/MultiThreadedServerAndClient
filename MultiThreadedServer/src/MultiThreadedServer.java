
public class MultiThreadedServer {
	//server main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Server running");
		//creating new instance of SocketServer class
		SocketServer s = new SocketServer();
		//calling runServer method in SocketServer class on instance "s"
		s.runServer();
	}

}
