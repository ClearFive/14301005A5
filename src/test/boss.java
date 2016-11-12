package test;

import test.annotation.Autowired;

public class boss {
  private office office;
  private car car;
 @Autowired
  public boss(){  
  }
  
  public boss(car car ,office office){
      this.car = car;
      this.office = office ;
  }
  
  public void setCar (car car){
	  this.car = car;
  }
  
  public void setOffice(office office){
	  this.office = office ;
  }

  public String tostring(){
	  return "this boss has "+car.tostring()+" and in "+office.tostring();
  }
}
