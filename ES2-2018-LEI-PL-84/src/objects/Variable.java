package objects;

public class Variable {
	
	private String name;
	private String type;
	private Object minValue;
	private Object maxValue;
	
	public Variable(String name, String type, Object minValue, Object maxValue) {
		this.name = name;
		this.type = type;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	public Variable() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public Object getMinValue() {
		return minValue;
	}
	
	public Object getMaxValue() {
		return maxValue;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setMinValue(Object minValue) {
		this.minValue = minValue;
	}
	
	public void setMaxValue(Object maxValue) {
		this.maxValue = maxValue;
	}
	
	@Override
	public String toString() {
		return "Variable [name=" + name + ", type=" + type + ", minValue=" + minValue + ", maxValue=" + maxValue + "]";
	}
}
