import java.io.*;
import java.net.*;

public class Server {
    static final int PORT = 10314;
    
    public static void main(String[] args) {
        try (
            ServerSocket server = new ServerSocket(PORT);
        ) {
            while (true) {
                Socket client = server.accept();
                handleClient(client);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private static void handleClient(Socket client) {
        try (
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        ) {

            String[] args = in.readLine().split(" ");
            String output = "";
            switch(args[0]) {
                case "echo":
                    output = echo(args[1]);
                    break;
                case "add":
                    output = Integer.toString(add(Integer.valueOf(args[1]), Integer.valueOf(args[2])));
                    break;
                case "divide":
                    try {
                        output = Integer.toString(divide(Integer.valueOf(args[1]), Integer.valueOf(args[2])));
                    } catch (Exception e) {
                        output = "ArithmeticException";
                    }
                    break;
            }

            out.println(output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) { 
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}