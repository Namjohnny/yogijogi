package com.sgs.help;

import java.io.IOException;
import java.net.Socket;

import com.yogijogi.member.User;

public class SocketInfo {

    private final Socket socket;
    private final MsgReaderThread reader;
    private final MsgSenderThread sender;
    private User user;
    
    public SocketInfo(Socket socket, User user) throws IOException {
        this.socket = socket;
        this.user = user;

        reader = new MsgReaderThread(this, socket, user);
        reader.start();

        sender = new MsgSenderThread(this, socket, user);
        sender.start();
    }

    public void destroy(){
        reader.destroy();
        sender.destroy();
        try{socket.close();}catch (IOException e){}
    }


}