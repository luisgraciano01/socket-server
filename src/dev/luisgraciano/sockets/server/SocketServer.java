package dev.luisgraciano.sockets.server;

import dev.luisgraciano.sockets.server.service.SocketClientEventManager;
import dev.luisgraciano.sockets.server.service.SocketServerManager;

/**
 * Clase principal por donde iniciará la ejecución de la aplicación. <br/>
 * <br/>
 * Creado el 10-05-2021
 *
 * @author Luis Alberto Graciano Padierna
 * @since 1.0.0
 */
public class SocketServer {

	public static void main(String[] args) {
		SocketServerManager socketServerManager = new SocketServerManager();
		socketServerManager.start(5000);

		SocketClientEventManager.getInstance().addEvent("test", SocketServer::prueba);
	}

	public static void prueba(String json) {
		System.out.println(json);
	}

}
