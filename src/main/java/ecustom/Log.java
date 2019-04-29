package ecustom;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import weaver.file.Prop;

public class Log {

	private Class<?> clazz = null;
	public static boolean isDebug = false;
	
	static {
		String appDebug = Prop.getPropValue("ecustom", "app.debug");
		if (appDebug == null || appDebug.isEmpty()) {
			List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();
			for (String arg : args) {
				if (arg.startsWith("-Xrunjdwp") || arg.startsWith("-agentlib:jdwp")) {
					isDebug = true;
					break;
				}
			}
		} else{
			isDebug = "true".equalsIgnoreCase(appDebug);
		}
	}

	public Log(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void debug(String message) {
		if (isDebug) {
			String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(new Date());
			System.out.println(dateTime + " [DEBUG] " + clazz.getName() + " - " + message);
		}
	}

	public void info(String message) {
		String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(new Date());
		System.out.println(dateTime + " [INFO] " + clazz.getName() + " - " + message);
	}

	public void error(String message) {
		String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(new Date());
		System.out.println(dateTime + " [ERROR] " + clazz.getName() + " - " + message);
	}

	public boolean isDebug() {
		return isDebug;
	}
}
