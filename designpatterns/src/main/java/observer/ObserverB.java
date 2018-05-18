package observer;

public class ObserverB implements Observer {

	private int age;
	private String name;
	private Subject subject;

	public ObserverB(Subject subject) {
		super();
		this.subject = subject;
		subject.registerObserver(this);
	}

	@Override
	public void update(int age, String name) {

		System.out.println("ObserverB 观察到变更：" + age + " " + name);
	}

	@Override
	public String toString() {
		return "ObserverA [age=" + age + ", name=" + name + "]";
	}

}
