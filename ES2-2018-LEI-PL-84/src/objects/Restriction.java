package objects;

public class Restriction {
	private String varName;
	private String operation;
	private Object value;
	@Override
	public String toString() {
		return "Restriction [varName=" + varName + ", operation=" + operation + ", value=" + value + "]";
	}
	public Restriction(String varName, String operation, String value) {
		super();
		this.varName = varName;
		this.operation = operation;
		this.value = value;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
