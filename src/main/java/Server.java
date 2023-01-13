import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server {

//    static Logger logger = Logger.getLogger(Server.class.getName());
private static Logger logger = null;

    static {
        InputStream stream = Server.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            logger= Logger.getLogger(Server.class.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        logger.info("\u001B[34m"+"server started"+"\u001B[0m");
        while (true) {
            listenToClientConnections(serverSocket);
        }

    }

    private static void listenToClientConnections(ServerSocket serverSocket) throws IOException {
        try {
            Socket clientSocket = serverSocket.accept();
            logger.info("\u001B[33m"+"client send request");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            File file = new File("D:\\Projects\\csweb\\index.html");
            FileInputStream inputStream = new FileInputStream(file);
            String requestedResource = "";
            String incomingLineFromClient;
            while ((incomingLineFromClient = in.readLine()) != null) {
                System.out.println(incomingLineFromClient);

                if (incomingLineFromClient.contains("HTTP/1.1")) {
                    requestedResource = incomingLineFromClient;
                }

                if (incomingLineFromClient.equals(""))
                    break;
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

            String response = "You have requested this resource: " + requestedResource;

            out.print("HTTP/1.1 200 OK\n");
            out.print("Content-Length: " + response.length() + "\n");
            out.print("Content-Type: text/html; charset=utf-8\n");
            out.print("Date: Tue, 25 Oct 2016 08:17:59 GMT\n");
            out.print("\n");
            out.print(response);
            out.print(inputStream.readAllBytes());
            out.flush();
        } catch (IOException e) {
            logger.severe("\u001B[31m"+e);
            throw new RuntimeException(e);
        }
    }
}

//            OutputStream os = clientSocket.getOutputStream();
//            File file = new File("D:\\Projects\\csweb\\index.html");
//
//
//            try (FileInputStream fin = new FileInputStream(file);
//                     BufferedReader br = new BufferedReader(new InputStreamReader(fin));) {
//                    if (br.ready()) {
//                        String line1 = br.readLine();
//                        System.out.println(line1);
//                    }
////            OutputStream os = clientSocket.getOutputStream();
//                    os.write("HTTP/1.1 200 OK\r\n".getBytes());
//                    os.write("\r\n".getBytes());
//                    os.write(fin.readAllBytes());
//                    os.flush();
//                }
//
//            } catch (FileNotFoundException ex) {
//                System.out.println("Info.xml is not found");
//            } catch (IOException ex) {
//                System.out.println("Can't read the file");
//            }


//        Read more: https://javarevisited.blogspot.com/2014/04/10-jdk-7-features-to-revisit-before-you.html#ixzz7oBm7RCKO


