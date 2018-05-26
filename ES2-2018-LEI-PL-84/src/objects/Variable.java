package objects;

public class Variable {
	
	private String name;
	private String type;
	private Object minValue;
	private Object maxValue;
	private int nrBits;
	
	public Variable(String name, String type, Object minValue, Object maxValue, int nrBits) {
		this.name = name;
		this.type = type;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.nrBits = nrBits;
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
	public void setNumberBits(int n) {
		this.nrBits = n;
	}
	public int getNumberBits() {
		return nrBits;
	}
	
	
	@Override
	public String toString() {
		return "Variable [name=" + name + ", type=" + type + ", minValue=" + minValue + ", maxValue=" + maxValue + ", nrBits="+nrBits+"]";
	}
}
