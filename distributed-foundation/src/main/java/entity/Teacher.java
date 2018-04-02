package entity;

import java.io.Serializable;

public class Teacher implements Serializable {
	private static final long serialVersionUID = -6635991328204468281L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Teacher{" + "name='" + name + '\'' + '}';
	}

}
