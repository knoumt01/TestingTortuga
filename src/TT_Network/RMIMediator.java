/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TT_Network;
import java.rmi.RemoteException;
import java.io.IOException;
import java.rmi.Remote;
import java.util.Vector;

/**
 * Source code from "Java Distributed Computing", by Jim Farley.
 *
 * Class: RMIMediator
 * Example: 9-10
 * Description: Interface for an RMI-based mediator.
 */

public interface RMIMediator extends Remote
{
  public boolean register(Identity i, RMICollaborator c)
      throws RemoteException;
  public Identity newMember() throws RemoteException;
  public boolean  remove(Identity i) throws RemoteException;
  public Vector   getMembers() throws RemoteException;

  public boolean send(Identity to, Identity from,
                      String mtag, String msg)
      throws IOException, RemoteException;
  public boolean broadcast(Identity from,
                           String mtag, String msg)
      throws IOException, RemoteException;
  public boolean send(Identity to, Identity from,
                      String mtag, Object data)
      throws IOException, RemoteException;
  public boolean broadcast(Identity from,
                         String mtag, Object data)
      throws IOException, RemoteException;
}