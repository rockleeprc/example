package proxy.myproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
	private File clazzPath;

	public MyClassLoader() {
		String classPath = MyClassLoader.class.getResource("").getPath();
		this.clazzPath = new File(classPath);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		String clazzFileName = MyClassLoader.class.getPackage().getName() + "." + name;

		if (clazzPath != null) {
			File classFile = new File(clazzPath, name.replaceAll("\\.", "/") + ".class");
			if (classFile.exists()) {
				FileInputStream in = null;
				ByteArrayOutputStream out = null;

				try {
					in = new FileInputStream(classFile);
					out = new ByteArrayOutputStream();
					byte[] buff = new byte[1024];
					int len;
					while ((len = in.read(buff)) != -1) {
						out.write(buff, 0, len);
					}
					return defineClass(clazzFileName, out.toByteArray(), 0, out.size());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (null != in) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					if (out != null) {
						try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}

		return null;

	}

}
