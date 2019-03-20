package Client;
import java.util.Scanner;
import java.io.IOException;
import java.net.*;

public class Client{
	
	private Scanner sc;
	private DatagramSocket ds;
	
	public Client()
	{
		this.sc = new Scanner(System.in);
		
		//Setup DatagramSocket
		try {
			this.ds = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Client client = new Client();
		client.init();
	}
	
	public void init()
	{
		System.out.println("Client started");
		while(true) {
			
			System.out.println("Eingabe: ");
			String input = this.sc.nextLine();
			
			if(input.equals("exit")) {
				break;
			}			
			
			try {				
				InetAddress ip = InetAddress.getByName("192.168.5.2");  
				DatagramPacket dp = new DatagramPacket(input.getBytes(), input.length(), ip, 55555);  
				this.ds.send(dp);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			byte[] buf = new byte[1024];

			DatagramPacket dp = new DatagramPacket(buf, 1024);

			try {
				this.ds.receive(dp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String str = new String(dp.getData(), 0, dp.getLength());

			System.out.println(str);
		}
		System.out.println("Goodbye");
		sc.close();
	}

}
