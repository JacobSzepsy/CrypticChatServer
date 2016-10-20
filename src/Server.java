import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class Server {
	static ServerSocket socketServer = null;
	BufferedReader in = null;
	PrintWriter out = null;
	static ArrayList<Socket> sockets = new ArrayList<Socket>();
	public class sendMessage
	{
		sendMessage(String message) throws Exception
		{
			for(int i = 0; i<sockets.size(); i++)
			{
				PrintWriter out = new PrintWriter(sockets.get(i).getOutputStream(), true);
				out.println(message);
			}
		}
	}
public class clientThread extends Thread
{
	Socket socketClient = null;
	
	public clientThread(Socket socket)
	{
		socketClient = socket;
	}
	public void run()
	{
		try(
				BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				
				){
			while(true)
			{
				try{
					if(in.ready())
						new sendMessage(in.readLine());
						
				}catch(Exception e){}
					
			}
		}catch(Exception e){}
	}
}
public static void main(String[] args) throws Exception
{
	
		socketServer = new ServerSocket(1337);
		while(true)
		{
			Socket temp = socketServer.accept();
			sockets.add(temp);
			new Server().new clientThread(temp).start();
		}

	/*try{
		while(true)
		{
			Scanner s = new Scanner(System.in);
			Scanner s2 = new Scanner(System.in);
			
			try{
				 
				while(true)
				{
					//if (s.next().equalsIgnoreCase("send"))
					//{

					//	String output = s2.next();
					//	out.println(output);
					//out.println("file:///F:/downloadedstuff/dwnloads/AIRPORN.mp3|50.0");
					//	}
					if(in.ready())
					{
						out.println(in.readLine());
					}
				}
			}catch(Exception e) {}finally{
				socketClient.close();
			}
		}
		}catch(Exception e) {}finally{
			try {
				socketServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try(
							ServerSocket socketServer = new ServerSocket(1337);
							Socket socketClient = socketServer.accept();
							PrintWriter out = new PrintWriter(socketClient.getOutputStream());
							BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
								Scanner s = new Scanner(System.in);
									){
								while(true){
									String x = s.nextLine();
									if (x.equalsIgnoreCase("send"))
									{
										out.println("file:///F:/downloadedstuff/dwnloads/AIRPORN.mp3|50");
									}
									out.println("file:///F:/downloadedstuff/dwnloads/AIRPORN.mp3|50");
						}catch(Exception e){}

*/



	}
}