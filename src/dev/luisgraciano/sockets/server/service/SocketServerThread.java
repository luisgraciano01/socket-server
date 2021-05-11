/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.luisgraciano.sockets.server.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que gestiona a partir de un hilo las conexiones entrantes a un servidor
 * socket. <br/>
 * Creado el 10-05-2021
 *
 * @author Luis Alberto Graciano Padierna
 * @since 1.0.0
 */
public class SocketServerThread extends Thread {

	/**
	 * Variable Logger para crear mensajes para el seguimiento o registro de la
	 * ejecución de una aplicación.
	 */
	private static final Logger LOGGER = Logger.getLogger(SocketServerThread.class.getName());

	/**
	 * {@link ServerSocket} asociado a este hilo.
	 */
	private final ServerSocket serverSocket;

	/**
	 * Lista de clientes conectados al servidor.
	 */
	private final List<SocketClientThread> clients = new ArrayList<>();

	/**
	 * Inicializa la clase a partir de un {@link ServerSocket}.
	 * 
	 * @param serverSocket {@link ServerSocket} a asociar a este hilo.
	 */
	public SocketServerThread(ServerSocket serverSocket) {
		super();
		this.serverSocket = serverSocket;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (!this.serverSocket.isClosed()) {
			try {
				SocketClientThread socketClientThread = new SocketClientThread(serverSocket.accept());
				socketClientThread.start();

				this.clients.add(socketClientThread);
				LOGGER.log(Level.INFO, "A new client connected.");
			} catch (IOException ex) {
				LOGGER.log(Level.SEVERE, null, ex);
			}
		}
	}

}
