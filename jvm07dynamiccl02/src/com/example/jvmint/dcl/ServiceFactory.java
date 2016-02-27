package com.example.jvmint.dcl;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ServiceFactory {

	public static SomeService newInstance()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		URL[] urls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();

		ClassLoader myCL = new MyClassLoader(urls);

		return (SomeService) myCL.loadClass("com.example.jvmint.dcl.ServiceImpl").newInstance();
	}
}

class MyClassLoader extends URLClassLoader {

	static int attempt = 0;
	final static int DELAY_FOR_CLASS_DOWNLOAD = 5;
	final static String REST_URL = "http://127.0.0.1:3000/";
	final static String REPLACED_CLASS_NAME = "ServiceImpl.class";

	public MyClassLoader(URL[] urls) {
		super(urls);
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		if (attempt < DELAY_FOR_CLASS_DOWNLOAD) {
			Downloader downloader = new Downloader();
			String destinationPath = getDestinationForDownloadedFile();
			downloader.downloadFileFromURL(REST_URL, new File(destinationPath));
		}

		if (name.contains("ServiceImpl")) {
			return findClass(name);
		}

		attempt++;

		return super.loadClass(name);
	}

	private String getDestinationForDownloadedFile() {

		URL currentFile = MyClassLoader.class.getResource("MyClassLoader.class");
		int lastIndexOfSlash = currentFile.getPath().lastIndexOf("/");
		String pathToCurrentFolder = currentFile.getPath().substring(0, lastIndexOfSlash + 1);
		String destination = pathToCurrentFolder + REPLACED_CLASS_NAME;
		return destination;
	}

}