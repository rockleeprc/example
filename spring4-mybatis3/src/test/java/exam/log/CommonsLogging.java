package exam.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonsLogging {
	
	private static Log logger = LogFactory.getLog(CommonsLogging.class);
	public static void main(String[] args) {
		logger.info("日志");

	}

}
