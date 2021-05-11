/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.luisgraciano.sockets.server.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestiona un seridor de sockets que soporta multiples clientes. <br/>
 * <br/>
 * Creado el 10-05-2021
 *
 * @author Luis Alberto Graciano Padierna
 * @since 1.0.0
 */
public class SocketServerManager {

	/**
	 * Variable Logger para crear mensajes para el seguimiento o registro de la
	 * ejecución de una aplicación.
	 */
	private static final Logger LOGGER = Logger.getLogger(SocketServerManager.class.getName());

	/**
	 * Instancia del servidor socket.
	 */
	private ServerSocket serverSocket;

	/**
	 * Hilo asociado a la instancia del servidor socket.
	 */
	private SocketServerThread thread;

	/**
	 * Inicializa un servidor de sockets en un puerto específico.
	 * 
	 * @param port Puerto donde se ejecutará el servidor.
	 * @return <b>true</b> Sí se ejecutó de forma exitosa el hilo del servidor, de
	 *         lo contratrio <b>false</b>.
	 */
	public boolean start(int port) {
		try {
			if (serverSocket == null) {
				LOGGER.log(Level.INFO, "Starting socket in port " + port);
				serverSocket = new ServerSocket(port);
				thread = new SocketServerThread(serverSocket);
				thread.start();

				LOGGER.log(Level.INFO, "Socket server is running on " + serverSocket.getLocalSocketAddress().toString());
				return true;
			} else {
				LOGGER.log(Level.WARNING, "There is already a server running on " + serverSocket.getLocalSocketAddress().toString());
			}
		} catch (IOException ioException) {
			LOGGER.log(Level.SEVERE, "Error running server.", ioException);
		}

		return false;
	}

	/**
	 * Detiene la actual instancia del servidor socket y su hilo asociado.
	 * 
	 * @return <b>true</b> Sí se detuvo de forma exitosa el servidor, de lo
	 *         contratrio <b>false</b>.
	 */
	public boolean stop() {
		try {
			if (serverSocket != null) {
				serverSocket.close();
				thread.interrupt();
				return true;
			}
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}

		return false;
	}

}
