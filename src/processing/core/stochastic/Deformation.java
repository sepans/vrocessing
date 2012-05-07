package processing.core.stochastic;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Deformation {
  public String role;
  public HashMap<String,Object> params;
  public Date start;
  public int duration; //ms
  
  public Deformation() {
    role = "";
    params = new HashMap<String,Object>();
    start=new Date();
    duration = 500;
  }
}
