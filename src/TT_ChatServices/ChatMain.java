/*
@title: Testing Tortuga
@author: Matt Knouff / MK Design Software
@file: ChatMain.java

Testing Tortuga is licensed under the LGLP-v3
 */
package TT_ChatServices;

public class ChatMain {
    
    public ChatMain() {
        
    }
    
    
    // connect to a server method
    public static void connect(Server server, User user) {
        Connection connection = new Connection(server, user, callback);
        connection.setAcceptAllSSLCerts(true);
        connection.connect();
    }
}