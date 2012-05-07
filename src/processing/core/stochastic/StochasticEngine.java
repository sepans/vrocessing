package processing.core.stochastic;

import java.util.Date;
import java.util.HashMap;

public class StochasticEngine {
  
  private static StochasticEngine ref = null;
  
  private StochasticEngine() {}
  
  public static StochasticEngine getReference() {
    if(ref==null)
        ref=new StochasticEngine();
    return ref;
  }
  
  private HashMap<String,Deformation> settings = new HashMap<String,Deformation>();
  
  public Deformation getDeformation(String func) {
    Deformation ret = null;
    if(func.equals(Functions.rect)) {
      ret = deformRect();
    }
    else if(func.startsWith(Functions.line.toString())) {
      ret = deformLine(func);
    }
    return ret;
    
  }

  private Deformation deformLine(String key) {
    if(settings.containsKey(key)) {
      Date now = new Date();
      Deformation def = settings.get(key);
      if(def.start.getTime()+def.duration<now.getTime()) { 
        //System.out.println("removing "+key);
        settings.remove(key);
        return null;
      }
      else {
        return def;
      }
        
    }
    Deformation ret=null;
    if(Math.random()<0.0005) {
      ret = new Deformation();
      ret.duration = (int) Math.round(Math.random()*20000);
      ret.role = "break";
      ret.params.put("stepX", (float) Math.min(Math.random(),0.5));
      ret.params.put("stepY", (float) Math.min(Math.random(),0.5));
      settings.put(key, ret);
    }
    else if(Math.random()<0.0002) {
      ret = new Deformation();
      ret.duration = (int) Math.round(Math.random()*20000);
      ret.role = "disp";
      ret.params.put("dispX1", (float)(Math.random())*20);
      ret.params.put("dispX2", (float)(Math.random())*20);
      ret.params.put("dispY1", (float)(Math.random())*20);
      ret.params.put("dispY2", (float)(Math.random())*20);
      settings.put(key, ret);
    }
    else if(Math.random()<0.00002) {
      ret = new Deformation();
      ret.duration = (int) Math.round(Math.random()*20000);
      ret.role = "box";
      ret.params.put("sizeX", (float)(Math.random())*100);
      ret.params.put("sizeY", (float) (Math.random())*100);
      settings.put(key, ret);
    }
    else if(Math.random()<0.00002) {
      ret = new Deformation();
      ret.duration = (int) Math.round(Math.random()*5000);
      ret.role = "cir";
      ret.params.put("sizeX", (float)(Math.random())*50);
      ret.params.put("sizeY", (float) (Math.random())*50);
      settings.put(key, ret);
    }
    else if(Math.random()<0.000001) {
      ret = new Deformation();

      ret.duration = (int) Math.round(Math.random()*1000);
      ret.role = "blck";
      ret.params.put("colorR", (int)Math.round((Math.random()*255)));
      ret.params.put("colorG", (int)Math.round((Math.random()*255)));
      ret.params.put("colorB", (int)Math.round((Math.random()*255)));
      settings.put(key, ret);
    }
    else if(Math.random()<0.00001) {
      ret = new Deformation();

      ret.duration = (int) Math.round(Math.random()*15000);
      ret.role = "rst";
      settings.put(key, ret);
    }   
    else if(Math.random()<0.00006) {
      ret = new Deformation();
      ret.duration = (int) Math.round(Math.random()*10000);
      ret.role = "mul";
      ret.params.put("num", (int)Math.round((Math.random()*10)));
      settings.put(key, ret);
    }
 
    return ret;
  }

  private Deformation deformRect() {
    // TODO Auto-generated method stub
    return null;
  }

}
