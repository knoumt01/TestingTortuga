/*
Class that will handle video chat connections for TT (Testing Tortuga.
 */
package TT_ChatServices;

import javax.media.*;
import java.io.File;
import java.io.IOException;
import java.net.*;

public class videoChat {
    
    private Player mediaPlayer = null;
    
    public videoChat(URL url) throws IOException, NoPlayerException, CannotRealizeException {
        mediaPlayer = Manager.createRealizedPlayer(url);
    }
  
    public void play() {
        mediaPlayer.start();
    }
    
    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.close();
    }
    
    public static void main(String[] args) throws IOException, NoPlayerException, CannotRealizeException {
        URL audioFile = new URL(args[0]);
        videoChat player = new videoChat(audioFile);
        
        player.play();
    }
    
}


class videoChatNet {
    
    /* breaks ip4 address down into 4 separate integers */
    void breakDownIP4Address(String IPAddress) {
        int integerArray[] = {0,0,0,0};
        int start = 0;
        int end = 2;
        for (int i = 0; i < 4; i++) {
            integerArray[i] = Integer.getInteger(IPAddress.substring(start, end));
            start += 4;
            end += 4;
        }
        isIP4Valid(integerArray);
    }
    /* checks validity of user specified IP4 address prior to attempting connection */
    boolean isIP4Valid(int numArray[]) {
        boolean returnValue = false;
        int a, b, c, d;
        /* initialize to 0 for now until other code is completed */
        a = 0; b = 0; c = 0; d = 0;
        numArray[0] = a;
        numArray[1] = b;
        numArray[2] = c;
        numArray[3] = d;
        
        if (a >= 1 && a <= 255) {
            if (b >= 1 && b <= 255) {
                if (c >= 1 && c <= 255) {
                    if (d >= 1 && d <= 255) {
                        returnValue = true;
                    }
                }
            }
        }
        return returnValue;
    }
    
    void breakDownIP6Address(String IPAddress) {
        // future implementation
    }
    
    boolean isIP6Valid(int numArray[]) {
        // future implementation - always returns false
        return false;
    }
}
