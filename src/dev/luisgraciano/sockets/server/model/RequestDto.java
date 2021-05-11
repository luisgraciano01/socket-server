package dev.luisgraciano.sockets.server.model;

import java.io.Serializable;

/**
 * Definición de una petición base por parte del cliente hacia el servidor.<br/>
 * <b>NOTA:</b> Extender está clase en caso de necesitar más propiedades. <br/>
 * <br/>
 * Creado el 10-05-2021
 *
 * @author Luis Alberto Graciano Padierna
 * @since 1.0.0
 */
public class RequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Nombre del evento asociado a la petición.
	 */
	private String event;

	/**
	 * Retorna el valor de la propiedad {@link RequestDto#event}<br/>
	 * <br/>
	 * Creado el 10-05-2021
	 *
	 * @return {@link String}, con el valor de la propiedad {@link RequestDto#event}
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * Asigna un valor a la propiedad {@link RequestDto#event}<br/>
	 * <br/>
	 * Creado el 10-05-2021
	 *
	 * @param event Un valor de tipo {@link String} a asignar a la propiedad
	 *              {@link RequestDto#event}
	 */
	public void setEvent(String event) {
		this.event = event;
	}

}
