package e2;

public class Person {

	private String name;
	private int age;

	private double height;
	private double weight;

	// 私有化
	private Person(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.height = builder.height;
		this.weight = builder.weight;
	}

	// 不直接生成对象，客户端使用构造器实例化
	public static class Builder {
		private String name;
		private int age;
		private double height;
		private double weight;

		public Builder(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		public Person build() {
			return new Person(this);
		}

		public Builder setHeight(double height) {
			this.height = height;
			return this;
		}

		public Builder setWeight(double weight) {
			this.weight = weight;
			return this;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", height=" + height + ", weight=" + weight + "]";
	}

	public static void main(String[] args) {
		Person p = new Person.Builder("Lee", 18).setHeight(183).setWeight(70).build();
		System.out.println(p);
	}
}
