## Single Threaded Web com.gcit.server.Server Program
### Structure
This project structured using intellij platform and maven as build system.
### About Project
-> This Project is used for communication between server and single client at a time.

-> In this project, client is the localhost who can send to the server to open the specified file in the browser.

-> When client given the local port number with specified file to open, the message will be sent to server for given port number.

-> server will accept the request from client and response to client with specified file.

-> It is one to one communication between server and client.

-> The response given by server will be HTTP response shown in the console output of server program.

-> In this program, code is designed for only one client can open at a time. If another client open at the same time it will not open.

-> In server program, fileInputStream is used for getting the required file to open.

-> To print the HTTP response, used printWriter and get it from outputStream.

-> Client can get the response from server with HTTP response shown in the opened browser.

-> The output in the console will be:

GET /index.html HTTP/1.1

Host: localhost:8080

Connection: keep-alive

sec-ch-ua: "Not?A_Brand";v="8", "Chromium";v="108", "Google Chrome";v="108"

sec-ch-ua-mobile: ?0

sec-ch-ua-platform: "Windows"

Upgrade-Insecure-Requests: 1

User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9

### Reference Link

[Link]( https://javarevisited.blogspot.com/2014/04/10-jdk-7-features-to-revisit-before-you.html#ixzz7oBm7RCKO
)

### To Run The Project
for maven, to build the project use
```
mvn clean install
```
to run the program, in main application by clicking the run button icon where the java class file is created.
```bash
public class com.gcit.server.Server {
```