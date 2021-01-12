package entities.enums;

public enum WorkerLevel {
	JUNIOR(0, "Junior"), MID_LEVEL(1, "Mid-Level"), SENIOR(2, "Senior");

	private Integer code;
	private String description;

	private WorkerLevel(Integer code, String description) {
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

	public static WorkerLevel getEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (WorkerLevel level : WorkerLevel.values()) {
			if (code.equals(level.getCode())) {
				return level;
			}
		}
		throw new IllegalArgumentException("Invalid id: " + code);
	}
}
