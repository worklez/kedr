package org.treblefrei.kedr.settings;

public class Setting implements AbstractSetting {
 
	private String value;
	 
	/**
	 * @see org.treblefrei.kedr.settings.AbstractSetting#setValue(java.lang.String)
	 */
	public void setValue(String value) {
	       this.value = value;
	}

    public String getValue() {
        return value;
    }
}
 
