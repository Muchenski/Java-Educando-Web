package entities.enums;

public enum Cor {
	BLACK(0, "Black"), WHITE(1, "White");

	private Integer code;
	private String description;

	private Cor(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Cor toEnum(Integer code) {
		if (code == null)
			return null;

		for (Cor cor : Cor.values()) {
			if (code.equals(cor.getCode()))
				return cor;
		}

		throw new IllegalArgumentException("Invalid code for color - " + code);
	}
}
