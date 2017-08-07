package i02;

public class Person {
	//require
	private String name;
	private int age;
	//optional
	private double height;
	private double weight;

	// 私有化
	private Person(Builder builder) {
		//在Person内进行数据验证，而不是在Builder内
		this.name = builder.name;
		this.age = builder.age;
		this.height = builder.height;
		this.weight = builder.weight;
	}

	// 不直接生成对象，客户端使用Builder实例化
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

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", height=" + height + ", weight=" + weight + "]";
	}

	public static void main(String[] args) {
		Person p1 = new Person.Builder("Lee", 18).setHeight(183).setWeight(70).build();
		System.out.println(p1);
		Person p2 = new Person.Builder("Lee", 18).setHeight(183).build();
		System.out.println(p2);
		
	}
}
