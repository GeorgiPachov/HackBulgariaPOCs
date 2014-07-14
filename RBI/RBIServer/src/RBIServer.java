import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;

public class RBIServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(RBIConstants.PORT);

		String command = "";
		Socket clientSocket = serverSocket.accept();
		while (true) {
			command = Utils.readMessage(clientSocket);

			String output = execute(command);
			Utils.writeMessage(output, clientSocket);
		}
	}

	private static String execute(String command) throws IOException {
		System.out.println("Executing " + command);
		Process process = Runtime.getRuntime().exec(command);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line = "";
		StringBuilder builder = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line).append(System.lineSeparator());
		}
		System.out.println("Finished executing " + command);
		process.destroy();
		return builder.toString();

	}
}
