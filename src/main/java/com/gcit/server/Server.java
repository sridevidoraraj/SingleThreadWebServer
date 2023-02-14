package com.gcit.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;
import java.util.logging.LogManager;

public class Server {
    static colorLogger colorLogger = new colorLogger();

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

        Properties properties = new Properties();
        FileReader fr = new FileReader("src/main/resources/application.properties");

        properties.load(fr);
        int number = Integer.parseInt(properties.getProperty("server.port"));

        try (ServerSocket serverSocket = new ServerSocket(number)) {
            while (true) {
                colorLogger.logInfo("com.gcit.server.Server Started ");
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


