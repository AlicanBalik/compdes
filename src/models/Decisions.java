package models;

public enum Decisions {
	
	get("get"),
	put("put"),
	print("print"),
	remove("remove");

	private String prefix;
	
	Decisions(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
}
