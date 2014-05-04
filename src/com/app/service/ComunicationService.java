package com.app.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.app.db.model.SocketRequest;

public class ComunicationService {

	private static ComunicationService comunicationService;
	private Socket requestSocket;

	private ComunicationService() {
	}

	private void createSocket() {
		try {
			requestSocket = new Socket("localhost", 6969);
			System.out.println("Connected to localhost in port 6969");
			// 2. get Input and Output streams
			// out = new ObjectOutputStream(requestSocket.getOutputStream());
			// ObjectInputStream in = new
			// ObjectInputStream(requestSocket.getInputStream());
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static ComunicationService getInstance() {
		if (comunicationService == null) {
			comunicationService = new ComunicationService();
		}

		return comunicationService;
	}

	private void closeSocket() {
		try {
			requestSocket.getInputStream().close();
			requestSocket.getOutputStream().close();
			requestSocket.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void sendRequest(SocketRequest request) {
		createSocket();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					requestSocket.getOutputStream());
			out.writeObject(request);
			out.flush();
			System.out.println("client>" + request);
			if (request.isNeedsResponse()) {
				getResponse(request);
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
//		closeSocket();
	}

	private void getResponse(SocketRequest request) {
		System.out.println("client awaits for response");
		
		try {
			ObjectInputStream in = new ObjectInputStream(
					requestSocket.getInputStream());

			SocketRequest sock = (SocketRequest) in.readObject();
			System.out.println(sock.getUser().getUsername());
			parseResponse(sock);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parseResponse(SocketRequest sock) {
		// TODO Auto-generated method stub
		
	}
}
