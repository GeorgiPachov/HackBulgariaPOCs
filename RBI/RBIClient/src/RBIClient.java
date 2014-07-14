import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class RBIClient {
	public static void main(String[] args) throws IOException {
		InetAddress localAddress = InetAddress.getLocalHost();
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(localAddress, RBIConstants.PORT));

		String line;
		while (!"quit".equals(line = readFromConsole())) {
			Utils.writeMessage(line, socket);
			System.out.println(Utils.readMessage(socket));
		}
	}

	private static String readFromConsole() {
		return new Scanner(System.in).nextLine();
	}
}
