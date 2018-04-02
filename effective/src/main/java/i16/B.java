package i16;

public class B extends A {
	/**
	 * 返回的类型和A.method不一样时，编译无法通过
	 */
	public /*String*/ void method() {
		/*return "";*/
	}
}
