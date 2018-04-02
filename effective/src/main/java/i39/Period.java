package i39;

import java.util.Date;

public final class Period {

	private final Date start;
	private final Date end;

	public Period(Date start, Date end) {
		super();
		// 保护性拷贝是在检查有效性前进行的
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());
		// 检查参数有效性
		if (this.start.compareTo(this.end) > 0) {
			throw new IllegalArgumentException(start + " after " + end);
		}
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public static void main(String[] args) {
		Period p = new Period(new Date(), new Date());
		Date end = p.getEnd();
		end = null;
		System.out.println(p.getEnd() == null);
		System.out.println(end == null);

	}
}
