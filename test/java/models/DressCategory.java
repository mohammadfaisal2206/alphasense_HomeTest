package models;

public enum DressCategory {
	PRINTED_SUMMER_DRESS ("Printed Summer Dress"),
	PRINTED_DRESS("Printed Dress"),
	PRINTED_SCHIFFON_DRESS("Printed Chiffon Dress"),
	INVALID_CATEGORY("Invalid");
	
	
	private String name;

	DressCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    } 

}
