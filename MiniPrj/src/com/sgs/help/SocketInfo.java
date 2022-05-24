package com.sgs.help;

import java.io.IOException;
import java.net.Socket;

public class SocketInfo {

    private final Socket socket;
    private final MsgReaderThread reader;
    private final MsgSenderThread sender;
    private final User user;
    
    public SocketInfo(Socket socket, User user) throws IOException {
        this.socket = socket;

        reader = new MsgReaderThread(this, socket);
        reader.start();

        sender = new MsgSenderThread(this, socket);
        sender.start();
    }

    public void destroy(){
        reader.destroy();
        sender.destroy();
        try{socket.close();}catch (IOException e){}
    }


}