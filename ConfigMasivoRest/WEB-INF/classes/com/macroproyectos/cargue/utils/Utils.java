package com.macroproyectos.cargue.utils;

import java.util.List;



	/**
	 * Clase que povee utilidades al proyecto
	 * 
	 * @author Juan.Gomez
	 *
	 */
	public class Utils {
		
		String mensaje = new String();
		/**
		 * Metodo que permite validar si un registro supera la cantidad de caracteres en
		 * base de datos
		 * 
		 * @param valor
		 *            parametro al cual se cuentan los caracteres
		 * @param cantidad
		 *            maximo de caracteres del valor
		 * @return boolean con la validacion
		 */
		public boolean getLength(String valor, int cantidad) {
			// Se realiza la validacion
			if (valor.length() > cantidad) {
				// en caso de cumplirse se retorna true
				return true;

			} else {
				// En caso contrario se retorna false
				return false;
			}

		}

		/**
		 * Metodo que valida si un elemento es nulo
		 * 
		 * @param objeto
		 *            objeto a validar
		 * @return boolean con la validacion realizada
		 */
		public boolean isNull(String objeto) {

			// Se compara el objeto
			if (objeto == null) {
				// En caso de ser nulo el objeto se retorna true
				return true;
			} else {
				// En caso contrario se retorna false
				return false;

			}

		}

		/**
		 * Metodo que retorna el mensaje al poseer la lista maxima de caracteres con datos 
		 * 
		 * @param lista
		 *            a de maximo de caracteres
		 * @return mensaje de los registros repetidos
		 */
		public String validaListaMaxCaracter(List<String> lista) {
			

			for (String ls : lista) {
				mensaje = mensaje + (ls + ". \n");
			}
			return Constantes.VALORES_MAXIMO_CARACTERES + mensaje;

		}
		
		/**
		 * 
		 * @param lista con parametros repetidos
		 * @return
		 * 		mensaje con los registros repetidos
		 */
		public String validaListaRepetidos(List<String> lista) {
			
			for (String ls : lista) {
				mensaje = mensaje + (ls + ". \n");
			}
			return Constantes.VALORES_REPETIDOS + mensaje;
		}
		
		/**
		 * Metodo que retorna el mensaje de los valores nulos en el documento
		 * 
		 * @param lista
		 * 		de parametros nulos por fila
		 * @return
		 * 		retorna el mensaje con filas nulas
		 */
		public String validaListaNulos(List<String> lista) {
			for (String ls : lista) {
				mensaje = mensaje +ls +"\n";
			}
			return Constantes.VALORES_NULOS + "\n" + mensaje;
		}


}
