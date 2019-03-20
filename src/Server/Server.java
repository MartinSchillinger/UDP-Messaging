package Server;

import java.io.IOException;
import java.net.*;

public class Server{

	private StatisticData stats;
	private DatagramSocket ds;

	public Server() {
		this.stats = new StatisticData();
		try {
			this.ds = new DatagramSocket(59999);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		try {
			while(true) {

				byte[] buf = new byte[1024];

				DatagramPacket dp = new DatagramPacket(buf, 1024);

				this.ds.receive(dp);
				
				stats.update(dp);

				String str = new String(dp.getData(), 0, dp.getLength());

				System.out.println("Received Package: " + str + " from: " + dp.getAddress() + " Port: " + dp.getPort());
				
				//Generate response message
				String responseMsg = "Length = " + str.length() + " (min: " + stats.getMinLength() + ", max: " +
					stats.getMaxLength() + ") Wortanzahl: " + stats.getLastWordCount() + " (min: " + stats.getMinWords() + ", max: " + 
					stats.getMaxWords() + ")";

				//Send response message
				try {				 
					DatagramPacket response = new DatagramPacket(responseMsg.getBytes(), responseMsg.length(), dp.getAddress(), dp.getPort());  
					this.ds.send(response);
				} catch (SocketException e) {
					System.out.println("Socketexception");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}			
		} catch (IOException e) {
			System.out.println("Server crashed");
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		System.out.println("Server started");
		Server server = new Server();
		server.init();		
	}

}
