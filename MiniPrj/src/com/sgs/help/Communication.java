package com.sgs.help;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication {

	private User user;
    public Communication(User user) {
    	this.user = user;
    }
    
    public void client() throws UnknownHostException, IOException {
    	
        Socket socket = new Socket("127.0.0.1", 12345);

        SocketInfo socketInfo = new SocketInfo(socket, user);
    }
    
    public void server() throws IOException {
    	
        ServerSocket serverSocket = new ServerSocket(12345);
        
        try {
        	while (true) {
                System.out.println("회원상담 대기중...");
                Socket socket = serverSocket.accept();
                System.out.println("연결된 클라이언트의 ip : " + socket.getInetAddress());
                new SocketInfo(socket, user);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	serverSocket.close();
		}
    }
	
}
