import java.io.*;
import java.net.Socket;

/**
 * Handles communication between the server and one client, for SketchServer
 *
 * @author Mark Tao, Spring 2020
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012; revised Winter 2014 to separate SketchServerCommunicator
 */
public class SketchServerCommunicator extends Thread {
	private Socket sock;					// to talk with client
	private BufferedReader in;				// from client
	private PrintWriter out;				// to client
	private SketchServer server;			// handling communication for

	public SketchServerCommunicator(Socket sock, SketchServer server) {
		this.sock = sock;
		this.server = server;
	}

	/**
	 * Sends a message to the client
	 * @param msg
	 */
	public void send(String msg) {
		out.println(msg);
	}
	
	/**
	 * Keeps listening for and handling (your code) messages from the client
	 */
	public void run() {
		try {
			System.out.println("someone connected");
			
			// Communication channel
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);

			// Keep getting and handling messages from the client
			String line;
			while((line = in.readLine())!=null) {
				sendToServer(line);
			}

			// Clean up -- note that also remove self from server's list so it doesn't broadcast here
			server.removeCommunicator(this);
			out.close();
			in.close();
			sock.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	//method that sends the info to the server
	private synchronized void sendToServer(String line) {
		String[] splitWords = line.split(":");
		int stringID = Integer.parseInt(splitWords[1]);

		serverCommunicator(splitWords, stringID, server.getSketch());
	}

	//helper method that delineates all of the cases
	static void serverCommunicator(String[] splitWords, int stringID, Sketch sketch) {
		switch (splitWords[0]) {
			case "create": //creating a shape
				String shapeType = splitWords[2];
				String shapeInfo = splitWords[3];
				sketch.create(stringID, shapeType, shapeInfo);
				break;
			case "recolor": //recoloring the shape
				sketch.recolor(stringID, Integer.parseInt(splitWords[2]));
				break;
			case "move": //moving it
				sketch.move(stringID, Integer.parseInt(splitWords[2].split(" ")[0]),
						Integer.parseInt(splitWords[2].split(" ")[1]));
				break;
			case ("delete"): //deleting it
				sketch.remove(stringID);
				break;
		}
	}
}
