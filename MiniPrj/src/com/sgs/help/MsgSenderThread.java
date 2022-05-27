package com.sgs.help;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.yogijogi.member.User;

public class MsgSenderThread extends Thread {

    private final Socket socket;
    private final SocketInfo socketInfo;
    private final PrintWriter writer;
    private final Scanner sc = new Scanner(System.in);
    private boolean flag = true;
    private final User user;


    public MsgSenderThread(SocketInfo socketInfo, Socket socket, User user) throws IOException {
        this.socket = socket;
        writer = new PrintWriter(socket.getOutputStream());
        this.socketInfo = socketInfo;
        this.user = user;
    }

    @Override
    public void run() {
        try{
            while (flag){
                sendMsg();
                Thread.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socketInfo.destroy();
        }
    }

    private void sendMsg(){
        String inputMsg = sc.nextLine();
        writer.println(user.getNick()+" : "+inputMsg);
        writer.flush();
    }

    public void destroy() {
        flag = false;
        writer.close();
    }
}