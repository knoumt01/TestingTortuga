
package TT_Network;

import java.util.Hashtable;
import java.io.Serializable;

/**
 * Source code from "Java Distributed Computing", by Jim Farley.
 *
 * Class: Identity
 * Example: 9-2
 * Description: Representation of an agent identity.
 */

public class Identity implements Serializable {
  Hashtable props = new Hashtable();

  public Identity(int id) { props.put("idnum", new Integer(id)); }

  public boolean equals(Object o) {
    boolean same = false;
    if (o != null && o.getClass() == this.getClass()) {
      Identity oi = (Identity)o;
      if (oi == this ||
           (oi.getId() == this.getId() &&
             ((oi.getName() == null && this.getName() == null) ||
              (oi.getName() != null && this.getName() != null &&
               oi.getName().compareTo(this.getName()) == 0)))) {
        same = true;
      }
    }
    return same;
  }


  public int    getId() {
    Integer idNum = (Integer)props.get("idnum");
    return idNum.intValue();
  }

  public String getName() { return (String)props.get("name"); }
  public void   setName(String n) { props.put("name", n); }

  public Object getProperty(Object key) {
    return props.get(key);
  }
  public void   setProperty(Object key, Object val) {
    props.put(key, val);
  }
}