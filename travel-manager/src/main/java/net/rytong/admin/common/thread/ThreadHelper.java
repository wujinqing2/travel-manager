package net.rytong.admin.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadHelper {
	/**固定大小的公共线程池**/
	public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(15);
}
