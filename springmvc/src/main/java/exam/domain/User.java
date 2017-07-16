package exam.domain;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

public class User {
	// 匹配4-30个数字,字母,小划线
	@Pattern(regexp = "w{4,30}")
	private String name;

	// 匹配6-30非空白字符
	@Pattern(regexp = "S{6,30}")
	private String password;

	// @DateTimeFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	// 必须为一个过去的时间
	@Past
	@NotNull
	private Date date;
	// 在2-100之间
	@Max(100)
	@Min(2)
	private Integer age;

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
