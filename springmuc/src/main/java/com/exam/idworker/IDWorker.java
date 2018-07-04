package com.exam.idworker;

public class IDWorker {

	public static final long nextId() {
		System.out.println(IDWorkerHolder.INSTANCE);
		return IDWorkerHolder.INSTANCE.nextId();
	}

	private static class IDWorkerHolder {
		private static final SnowflakeIdWorker INSTANCE = new SnowflakeIdWorker(0, 0);
	}
}
