package TT_Network;

import java.rmi.RemoteException;
import java.io.IOException;
import java.rmi.Remote;
import java.util.Properties;

/**
 * Source code from "Java Distributed Computing", by Jim Farley.
 *
 * Class: RMICollaborator
 * Example: 9-9
 * Description: RMI-based version of the collaborator interface.
 */

public interface RMICollaborator extends Remote
{
  public Identity getIdentity() throws RemoteException;

  // Connect to a mediator - subclasses dictate properties needed
  public boolean connect(Properties p) throws RemoteException;

  // Outgoing messages/data
  public boolean send(String tag, String msg, Identity dst)
      throws IOException, RemoteException;
  public boolean send(String tag, Object data, Identity dst)
      throws IOException, RemoteException;
  public boolean broadcast(String tag, String msg)
      throws IOException, RemoteException;
  public boolean broadcast(String tag, Object data)
      throws IOException, RemoteException;

  // Incoming messages/data
  public boolean notify(String tag, String msg, Identity src)
      throws IOException, RemoteException;
  public boolean notify(String tag, Object data, Identity src)
      throws IOException, RemoteException;
}