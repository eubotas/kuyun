package com.kuyun.eam.common.constant;

public enum TicketSearchCategory {
	
	MY_OPEN("myOpen"),
    MY_ALL("myAll"),
    OPEN("open"),
    ALL("all"),
    INIT("init"),
    PROCESSING("processing"),
    NOTRESOLVED("notResolved"),
    RESOLVED("resolved"),
	OTHER("other");
	
    private TicketSearchCategory(String name){
        this.name = name;
    }
    
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
    	 	return this.name;
    }
    
    public boolean equalTo (String name) {
    		return this.name.equals(name);
    }
    
    public static TicketSearchCategory getCategroy (String name) {
    		switch (name) {
			case "myAll":
				return MY_ALL;
			case "myOpen":
				return MY_OPEN;
			case "open":
				return OPEN;
			case "all":
				return ALL;
			case "init":
                return INIT;
            case "processing":
                return PROCESSING;

                case "notResolved":
                    return NOTRESOLVED;
                case "resolved":
                    return RESOLVED;
			default:
				return OTHER;
			}
    }
}
