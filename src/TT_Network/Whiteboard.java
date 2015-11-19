/*
@title Testing Tortuga
@author Matt Knouff / MK Design Software
@file Whiteboard.java

This file contains all methods for the distributed whiteboard for 
Testing Tortuga.
Licensed under GPL version 3.
 */
package TT_Network;

import java.awt.event.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;

public abstract class Whiteboard extends RMICollaboratorImpl implements
        MouseListener, MouseMotionListener {
    
    protected Hashtable lastPts = new Hashtable();
    protected Component whiteboard;
    protected Image buffer;
    
    // constructor
    public Whiteboard(String name, Color color, String host, String mname) 
            throws RemoteException {
        super(name);
        Properties properties = new Properties();
        properties.put("host", host);
        properties.put("mediatorName", mname);
        connect(properties);
        getIdentity().setProperty("color", color);
        System.out.println("color = " + color.getRed() + " " + color.getGreen()
            + " " + color.getBlue());
        buildUI();
    }
    // build the user interface for the Whiteboard
}
