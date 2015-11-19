package TT_Network;

import java.io.IOException;
import java.util.Properties;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RMISecurityManager;

/**
 * Source code from "Java Distributed Computing", by Jim Farley.
 *
 * Class: RMICollaboratorImpl
 * Example: 9-11
 * Description: Implementation of the RMI-based collaborator.
 */

public class RMICollaboratorImpl
    extends UnicastRemoteObject implements RMICollaborator
{
  protected Identity id = null;
  protected RMIMediator mediator = null;

  public RMICollaboratorImpl(String name, String host, String mname)
      throws RemoteException {
    id = new Identity(0);
    id.setName(name);
    Properties p = new Properties();
    p.put("host", host);
    p.put("mediatorName", mname);
    connect(p);
  }

  public RMICollaboratorImpl(String name) throws RemoteException {
    id = new Identity(0);
    id.setName(name);
  }

  public Identity getIdentity() throws RemoteException { return id; }

  public boolean connect(Properties p) throws RemoteException {
    boolean success = false;
    String host = p.getProperty("host");
    String mName = p.getProperty("mediatorName");
    if (host != null && mName != null) {
      try {
        String url = "rmi://" + host + "/" + mName;
        System.out.println("looking up " + url);
        mediator = (RMIMediator)Naming.lookup(url);
        System.out.println("Got mediator " + mediator);
        Identity newId = mediator.newMember();
        mediator.register(newId, this);
        newId.setName(id.getName());
        id = newId;
        success = true;
      }
      catch (Exception e) {
        e.printStackTrace();
        success = false;
      }
    }

    return success;
  }

  public boolean send(String tag, String msg, Identity dst)
      throws IOException, RemoteException {
    boolean success = false;
    if (mediator != null) {
      success = mediator.send(dst, getIdentity(), tag, msg);
    }
    return success;
  }

  public boolean send(String tag, Object data, Identity dst)
      throws IOException, RemoteException {
    boolean success = false;
    if (mediator != null) {
      success = mediator.send(dst, getIdentity(), tag, data);
    }
    return success;
  }

  public boolean broadcast(String tag, String msg)
      throws IOException, RemoteException {
    boolean success = false;
    if (mediator != null) {
      success = mediator.broadcast(getIdentity(), tag, msg);
    }
    return success;
  }

  public boolean broadcast(String tag, Object data)
      throws IOException, RemoteException {
    boolean success = false;
    if (mediator != null) {
      success = mediator.broadcast(getIdentity(), tag, data);
    }
    return success;
  }

  public boolean notify(String tag, String msg, Identity src)
      throws IOException, RemoteException {
    System.out.println("Got message: \"" + tag + " " + msg + "\""
                       + " from " + src.getName());
    return true;
  }

  public boolean notify(String tag, Object data, Identity src)
      throws IOException, RemoteException {
    System.out.println("Got message: \"" + tag + " " + data + "\""
                       + " from " + src.getName());
    return true;
  }

  public static void main(String argv[]) {
    // Install a security manager
    System.setSecurityManager(new RMISecurityManager());
    try {
      String name = argv[0];
      String host = argv[1];
      String mname = argv[2];
      Properties props = new Properties();
      props.put("host", host);
      props.put("mediatorName", mname);
      RMICollaboratorImpl c = new RMICollaboratorImpl(name);
      if (c.connect(props)) {
        System.out.println("Got mediator...");
        c.broadcast("msg", "hello world");
      }
    }
    catch (Exception e) {
      System.out.println("Caught exception:");
      e.printStackTrace();
    }
  }
}