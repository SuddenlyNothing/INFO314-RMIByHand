import java.io.*;
import java.net.*;

public class Client {
    public static final String HOST = "localhost";

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) {
        return Integer.valueOf(rmi("add", Integer.toString(lhs), Integer.toString(rhs)));
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) {
        String res = rmi("divide", Integer.toString(num), Integer.toString(denom));
        if (res.equals("ArithmeticException")) {
            throw new ArithmeticException();
        }
        return Integer.valueOf(res);
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        return rmi("echo", message);
    }

    public static String rmi(String func, String... params) {
        try (Socket socket = new Socket(HOST, PORT)) {
            socket.setSoTimeout(15000);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            StringBuilder output = new StringBuilder();
            output.append(func);
            for (int i = 0; i < params.length; i++) {
                output.append(" ");
                output.append(params[i]);
            }

            out.println(output);
            String finished = in.readLine();
            return finished;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) {
        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}