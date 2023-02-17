package com.gcit.webserver;

import com.gcit.webserver.subpackage.HttpServer;
import com.gcit.webserver.subpackage.colorLogger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.logging.LogManager;

public class Server {
    static com.gcit.webserver.subpackage.colorLogger colorLogger = new colorLogger();

    static {
        InputStream stream = Server.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        colorLogger.logInfo("Getting the port Number");
        System.out.print("enter the port number : ");
        int port = sc.nextInt();
        System.out.println("port number is : " +port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                colorLogger.logInfo("Server Started ");
                try (Socket client = serverSocket.accept()) {
                    Instant start = Instant.now();
                    colorLogger.logInfo("Starting Time : " + start);
                    HttpServer.handleClient(client);
                    Instant end = Instant.now();
                    colorLogger.logInfo("End Time : " + end);
                    long elapsedTime = Duration.between(start, end).toMillis();
                    colorLogger.logInfo("Duration of Request and Response : " + elapsedTime + " milliseconds");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}


