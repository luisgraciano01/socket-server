/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.luisgraciano.sockets.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import dev.luisgraciano.sockets.server.model.RequestDto;
import dev.luisgraciano.sockets.server.model.ResponseDto;

/**
 * Esta clase gestiona a partir de un hilo los eventos en un socket abierto por
 * un cliente. <br/>
 * <br/>
 * Creado el 10-05-2021
 *
 * @author Luis Alberto Graciano Padierna
 * @since 1.0.0
 */
public class SocketClientThread extends Thread {

	/**
	 * Variable Logger para crear mensajes para el seguimiento o registro de la
	 * ejecución de una aplicación.
	 */
	private final static Logger LOGGER = Logger.getLogger(SocketClientThread.class.getName());

	/**
	 * Socket asociado a este hilo.
	 */
	private final Socket clientSocket;

	/**
	 * Stream de datos de entrada.<br/>
	 * Se usará para recibir información del cliente.
	 */
	private BufferedReader in;

	/**
	 * Stream de datos de salida.<br/>
	 * Se usará para enviar información al cliente.
	 */
	private PrintWriter out;

	public SocketClientThread(Socket socket) {
		super();
		this.clientSocket = socket;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			Gson gson = new Gson();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				RequestDto peticionDto = gson.fromJson(inputLine, RequestDto.class);
				SocketClientEventManager.getInstance().callEvent(peticionDto.getEvent(), inputLine);
			}

			in.close();
			out.close();

			clientSocket.close();
		} catch (IOException ioException) {
			LOGGER.log(Level.SEVERE, null, ioException);
		}
	}

	/**
	 * Envía una respuesta al cliente.
	 * 
	 * @param respuestaDto Información a enviar al cliente.
	 */
	public void sendResponse(ResponseDto respuestaDto) {
		if (!this.clientSocket.isClosed()) {
			out.println(new Gson().toJson(respuestaDto));
		}
	}

}
