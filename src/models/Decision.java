package models;

public enum Decision {
	
	get("get"), // gets value of requested variable
	put("put"), // sets value to specified variable
	print("print"), // displays all stored variables
	remove("remove"), // removes variable
	move("move"), // moves variable value to another variable
	save("save"), // saves variables to .txt file
	load("load"); // loads previously saved .txt file

	private String prefix;
	
	Decision(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
}
