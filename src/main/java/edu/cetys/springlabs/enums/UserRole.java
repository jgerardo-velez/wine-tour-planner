package edu.cetys.springlabs.enums;

public enum UserRole {
	
	ADMIN {
	    @Override
	    public String toString() {
	      return "Administrator";
	    }
	  },
	TOURIST {
	    @Override
	    public String toString() {
	      return "Tourist";
	    }
	  },
	VINTNER {
	    @Override
	    public String toString() {
	      return "Vintner";
	    }
	  }
	  
}
