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
        buildGUI();
    }
    // build the user interface for the Whiteboard
    protected void buildGUI() {
        Frame frame = new Frame();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        frame.setLayout(gridbag);
        frame.addNotify();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        Canvas canvas1 = new java.awt.Canvas();
        canvas1.setSize(320,240);
        canvas1.setBackground(Color.white);
        gridbag.setConstraints(canvas1, constraints);
        frame.add(canvas1);
    }
}