import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Utils {
	public static void writeMessage(String message, Socket socket) throws IOException {
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
		printWriter.write(message + "\n");
		printWriter.write(RBIConstants.EOM + "\n");
		printWriter.flush();
	}

	@SuppressWarnings("resource")
	public static String readMessage(Socket socket) throws IOException {
		Scanner scanner = new Scanner(socket.getInputStream());
		StringBuilder builder = new StringBuilder();
		String line;
		while (true) {
			line = scanner.nextLine();
			if (line.contains(RBIConstants.EOM)) {
				return builder.toString();
			} else {
				builder.append(line).append(System.lineSeparator());
			}
		}
	}
}
