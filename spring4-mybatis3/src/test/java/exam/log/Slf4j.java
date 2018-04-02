package exam.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4j {

	private static Logger logger = LoggerFactory.getLogger(Slf4j.class);

	public static void main(String[] args) {
		logger.debug("日志{} 占位符{}","liyan","sky");
	}

}
