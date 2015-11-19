package TT_ChatServices;

/* text chat server */

import java.io.*;
import java.net.*;
import java.util.*;

public class textChatServer {
    ServerSocket ss;
    Socket s;
    ArrayList al = new ArrayList();
    ArrayList al1 = new ArrayList();
    ArrayList al2 = new ArrayList();
    ArrayList alname = new ArrayList();
    Socket s1, s2;
    
    textChatServer() throws IOException {
        ss = new ServerSocket(4200);
        while(true) {
            s = ss.accept();
            s1 = ss.accept();
            s2 = ss.accept();
            al.add(s);
            al1.add(s1);
            al2.add(s2);
            System.out.println("Client is connected");
            
            MyThread m2 = new MyThread2(s2,al2,alname); // username list thread
            Thread t2 = new Thread(m);
            t2.start();
            
            MyThread r = new MyThread(s,all); // SENDING & RECEIVING MSGS
            Thread t = new Thread(r);
            t.start();
            
        }
    }
}
