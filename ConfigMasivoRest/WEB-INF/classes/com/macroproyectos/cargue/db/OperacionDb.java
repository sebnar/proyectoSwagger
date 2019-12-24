package com.macroproyectos.cargue.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.macroproyectos.cargue.utils.Constantes;

public class OperacionDb {

	// Declaracion del objeto preparedstatement
	private PreparedStatement stmt = null;
	// Declaracion del resulset
	private ResultSet rs = null;
	// variable que contiene el quey a base de datos
	private String consulta = new String();

	/**
	 * Metodo que permite insertar grupos en la base de datos
	 * 
	 * @param nombre
	 *            nombre del grupo
	 * 
	 * @param usuario
	 *            usuario
	 * @param maxdep
	 *            maxdep
	 * @param codigo
	 *            codigo del grupo
	 * @return Retorna el mensaje de operacion
	 */
	public String agregarGrupo(String nombre, String usuario, String maxdep, String codigo) {
		// declaracion conexcion a base de datos
		try (Connection conecc = new Conexion().conectarbd()) {
			// Consulta a base de datos
			consulta = "insert  into forest.for_cfg_tipo_grupo (codtipo,nombre,usuarios,maxdeps,codigo) values ((select max (codtipo) from forest.for_cfg_tipo_grupo)+1,?, ?,?,?)";

			stmt = conecc.prepareStatement(consulta);
			// set de parametros en la consulta
			stmt.setString(1, nombre);
			stmt.setString(2, usuario);
			stmt.setString(3, maxdep);
			stmt.setString(4, codigo);
			rs = stmt.executeQuery();
			// Se asigna el mensaje de operacion exitosa
			return Constantes.OPERACION_OK;
		} catch (Exception e) {
			// Se asigna el mensaje de error en caso de fallas en la operacion
			return Constantes.OPERACION_ERROR + e;
		}
		// Se retorna el mensjae de la operacion

	}

	/**
	 * Metodo que permite insertar medios de envio a la base de datos
	 * 
	 * @param desmedenv
	 *            nombre del medio de envio
	 * @param activo
	 *            1 activo 0 inactivo
	 * @param codigo
	 *            codigo asociado al medio de envio
	 * @param sistema
	 *            1 indica sistema 0 no
	 * @param fuente
	 *            indica la fuente del medio
	 * @param cantrotulo
	 *            no se especifica en bd
	 * @return Se retorma el mensjae de la operacion
	 */
	public String agregarMedioEnvio(String desmedenv, String activo, String codigo, String sistema, String fuente,
			String cantrotulo) {
		// Consulta a base de datos
		consulta = "Insert into FOREST.FOR_CFG_MEDIO_ENVIO (TIPMED,DESMEDENV,ACTIVO,CODIGO,SISTEMA,FUENTE,CANTROTULO) values ((select max (tipmed) from forest.for_cfg_medio_envio) +1,?,?,?,?,?,?)";
		// Se realiza la conexion a base de datos
		try (Connection conecc = new Conexion().conectarbd()) {
			stmt = conecc.prepareStatement(consulta);
			// Se settean los parametros de la consulta
			stmt.setString(1, desmedenv);
			stmt.setString(2, activo);
			stmt.setString(3, codigo);
			stmt.setString(4, sistema);
			stmt.setString(5, fuente);
			stmt.setString(5, fuente);
			stmt.setString(6, cantrotulo);
			// Se ejecuta el query
			rs = stmt.executeQuery();
			// Se asigna el mensaje de la operacion
			return Constantes.OPERACION_OK;

		} catch (Exception e) {
			// Se retorna el mensjae de la operacion
			return Constantes.OPERACION_ERROR + e;
		}
		// Se retorna el mensaje de la operacion

	}

	/**
	 * Metodo que permite agregar cargos en la base de datos
	 * 
	 * @param idCargoSup
	 *            id del cargo superior
	 * @param nombre
	 *            nombre del cargo
	 * @param descripcion
	 *            descripcion del cargo
	 * @param codigo
	 *            codigo del cargo
	 * @param activo
	 *            indica si esta activo o no
	 * @return Se retorna el mensaje de la operacion
	 */
	public String agregarCargo(String idCargoSup, String nombre, String descripcion, String codigo, String activo) {
		// Query a base de datos
		consulta = "Insert into FOREST.FOR_CFG_CARGO (IDCARGOSUP,NOMBRE,DESCRIPCION,CODIGO,ACTIVO) values (?,?,?,?,?)";
		// Conexion a base de datos
		try (Connection conecc = new Conexion().conectarbd()) {
			// sett de parametros del query
			stmt = conecc.prepareStatement(consulta);
			stmt.setString(1, idCargoSup);
			stmt.setString(2, nombre);
			stmt.setString(3, descripcion);
			stmt.setString(4, descripcion);
			stmt.setString(5, activo);
			// se ejecuta el query
			rs = stmt.executeQuery();
			// se asigna el mensaje de la operacion
			return Constantes.OPERACION_OK;

		} catch (Exception e) {
			// Se asigna el mensaje de error en caso de fallo
			return Constantes.OPERACION_ERROR + e;
		}
		// Se retorna el mensaje asignado

	}

	/**
	 * Metodo que permite agregar terceros en la base de datos
	 * 
	 * @param numero
	 *            Numero del tercero
	 * @param nombre
	 *            Nombre del tercero
	 * @param dir
	 *            Direccion del tercero
	 * @param email
	 *            Correo electronico del tercero
	 * @param activo
	 *            Se indica si es activo
	 * @param tipo
	 *            Tipo de tercero
	 * @return Retorna el mensaje de la operacion
	 */
	public String agregarTercero(String numero, String nombre, String dir, String email, String activo, String tipo) {
		// query a base de datos
		consulta = "INSERT INTO FOREST.FOR_CFG_TERCERO (ID,NUMTER,NOMTER,DIR,EMAIL,ACTIVO,TIPTER) VALUES ((SELECT MAX (ID) FROM FOREST.FOR_CFG_TERCERO)+1,?,?,?,?,?,?)";

		try (Connection conecc = new Conexion().conectarbd()) {
			// set de parametros al query
			stmt = conecc.prepareStatement(consulta);
			stmt.setString(1, numero);
			stmt.setString(2, nombre);
			stmt.setString(3, dir);
			stmt.setString(4, email);
			stmt.setString(5, activo);
			stmt.setString(6, tipo);
			// se ejecuta el query
			rs = stmt.executeQuery();
			// Se asigna el mensaje de la operacion
			return Constantes.OPERACION_OK;
		} catch (Exception e) {
			// Se asigna el mensaje de error en caso de fallo
			return Constantes.OPERACION_ERROR + e;
		}
		// Se retorna el mensaje asignado

	}

	/**
	 * Metodo que permite agregar dependencias en la base de datos
	 * 
	 * @param nombreDep
	 *            Nombre de la dependencia
	 * @param codigoDep
	 *            Codigo de la dependencia
	 * @param tipoDep
	 *            Tipo de la dependencia
	 * @param depPadre
	 *            Dependencia padre
	 * @param profundidad
	 *            Profundidad de la dependencia
	 * @param tercero
	 *            Tercero de la dependencia
	 * @return Retorna el mensaje de la operacion
	 */
	public String agregarDependencia(String nombreDep, String codigoDep, String tipoDep, String depPadre,
			String profundidad, String tercero) {
		// query a para insertar dependencias
		consulta = "Insert into FOREST.FOR_CFG_DEPENDENCIAS (ID, NOMDEPENDENCIA, FECINI, FECFIN, CODIGO, CODTIPO, FECCREACION, FECMODIFICACION, ID_ENTIDAD, CODDEPPADRE, PROFUNDIDAD, BK_CODE, ID_TERCERO) values ( 1, ?, CURRENT_DATE,  NULL,?, (SELECT CODTIPO FROM FOREST.FOR_CFG_TIPO_GRUPO WHERE NOMBRE = ?), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, (select ID from forest.for_cfg_dependencias where codigo = ? ), ?, NULL,(SELECT ID FROM FOREST.FOR_CFG_TERCERO WHERE NUMTER = ?))";
		// conexion a base de datos
		try (Connection conecc = new Conexion().conectarbd()) {
			// set de parametros al query
			stmt = conecc.prepareStatement(consulta);
			stmt.setString(1, nombreDep);
			stmt.setString(2, codigoDep);
			stmt.setString(3, tipoDep);
			stmt.setString(4, depPadre);
			stmt.setString(5, profundidad);
			stmt.setString(6, tercero);
			// ejecucion del query
			rs = stmt.executeQuery();
			// se retorna el mensaje de la operacion
			return Constantes.OPERACION_OK;
		} catch (Exception e) {
			return Constantes.OPERACION_ERROR + e;
		}

	}

	/**
	 * Metodo que permite agregar funcionarios en la base de datos
	 * 
	 * @param codFun
	 *            usuario
	 * @param codDep
	 *            codigo de la dependencia asociada
	 * @param tipDoc
	 *            tipo de documento
	 * @param nomFun
	 *            Nombre del funcionario
	 * @param activo
	 *            indica si es activo
	 * @param email
	 *            correo electronico del funcionario
	 * @param codCargo
	 *            codigo del cargo
	 * @param cedula
	 *            cedula del funcionario
	 * @return
	 */
	public String agregarFuncionario(String codFun, String codDep, String tipDoc, String nomFun, String activo,
			String email, String codCargo, String cedula) {
		// consulta de base de datos
		consulta = "INSERT INTO FOREST.FOR_CFG_FUNCIONARIO (CODFUN,CODDEP,TIPDOC,NOMFUN,ACTIVO,EMAIL,IDROL,CODCARGO,CEDULA,TIPO,AUSENTE,ID_TERCERO)VALUES (?,(SELECT ID FROM FOREST.FOR_CFG_DEPENDENCIAS WHERE CODIGO = ?),?,?,?,?,2,(SELECT IDCARGO FROM FOREST.FOR_CFG_CARGO WHERE CODIGO = ?),?,0,0, (SELECT ID FROM FOREST.FOR_CFG_TERCERO WHERE NUMTER = ?))";
		// conecxion a base de datos
		try (Connection conecc = new Conexion().conectarbd()) {
			// set de parametros al query
			stmt = conecc.prepareStatement(consulta);
			stmt.setString(1, codFun);
			stmt.setString(2, codDep);
			stmt.setString(3, tipDoc);
			stmt.setString(4, nomFun);
			stmt.setString(5, activo);
			stmt.setString(6, email);
			stmt.setString(7, codCargo);
			stmt.setString(8, cedula);
			stmt.setString(9, cedula);

			// ejecucion del query
			rs = stmt.executeQuery();
			// se retorna el mensaje de la operacion
			return Constantes.OPERACION_OK;
		} catch (Exception e) {
			return Constantes.OPERACION_ERROR + e;
		}

	}

}
