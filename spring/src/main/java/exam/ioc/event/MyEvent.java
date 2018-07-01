
package exam.ioc.event;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
	private static final long serialVersionUID = -7410622007815024418L;
	private String info;

	public MyEvent(Object source, String info) {
		super(source);
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
