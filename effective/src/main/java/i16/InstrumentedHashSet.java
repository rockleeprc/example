package i16;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class InstrumentedHashSet<E> extends HashSet<E> {
	private int addCount = 0;

	public InstrumentedHashSet() {
	}

	public InstrumentedHashSet(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}

	@Override
	public boolean add(E e) {
		System.out.println("add");
		addCount++;
		return super.add(e);
	}
	
	/**
	 * addAll()的实现基于add()，这种实现细节不是标准，不是承诺，
	 * 不能保证在Java平台中的所有实现保持不变，随着JDK版本的发行，可能会发生改变，
	 * 这种继承继承非常脆弱
	 *
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		/*
		 * 1. InstrumentedHashSet.addAll() +3
		 * 2. super.addAll()调用HashSet.addAll()
		 * 3. HashSet.addAll()调用InstrumentedHashSet.add() +3
		 */
		System.out.println("addAll");
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}

	public static void main(String[] args) {
		InstrumentedHashSet<String> s = new InstrumentedHashSet<String>();
		s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
		System.out.println(s.getAddCount());
	}
}
