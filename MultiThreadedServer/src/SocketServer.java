import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	int portNum = 44444;
	ServerSocket serverSocket = null;
	
	public void runServer() {
		try {
			//listening on port 44444 for connection
			serverSocket = new ServerSocket(portNum);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		while(true) {
			try {
				//connection to client made
				Socket clientSocket = serverSocket.accept();
				//creating new thread and starting it
				MultiThreadedRunnable m = new MultiThreadedRunnable(clientSocket);
				new Thread(m).start();
			} catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}

		
	}

}
