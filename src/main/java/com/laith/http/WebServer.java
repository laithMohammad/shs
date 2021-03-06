package com.laith.http;

import com.laith.http.common.Config;
import com.laith.http.request.HttpRequestWrapperThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
	public static final int portNumber = Config.getPort();
	private static ExecutorService executorService = Executors.newFixedThreadPool(Config.serverThreads());

	public static void start() throws IOException {
		final ServerSocket serverSocket = new ServerSocket(portNumber);
		while (true) {
			System.out.println("\n============================== Waiting Http Request ==========================");
			Socket client = serverSocket.accept();
			HttpRequestWrapperThread wrapper = new HttpRequestWrapperThread(client);
			executorService.execute(wrapper);
		}
	}
}
