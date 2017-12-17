package models;

public enum Decision {
	
	get("get"),
	put("put"),
	print("print"),
	remove("remove");

	private String prefix;
	
	Decision(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
}
