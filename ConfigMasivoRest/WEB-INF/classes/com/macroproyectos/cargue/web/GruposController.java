package com.macroproyectos.cargue.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.macroproyectos.cargue.db.OperacionDb;
import com.macroproyectos.cargue.utils.Utils;
import com.macroproyectos.cargue.vo.GrupoVo;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "grupo")
@RestController
public class GruposController {

	@ApiOperation(value = "Inserta los grupos en la base de datos")
	@PostMapping(path = "/subirArchivoGrupos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String inserGrup(@ApiParam(value = "file", required = true) @RequestPart("file") MultipartFile file) {

		List<GrupoVo> grupos;
		Reader reader = null;
		Utils ut = new Utils();
		File convFile = null;
		FileOutputStream fos;
		OperacionDb obd = new OperacionDb();
		try {
			convFile = new File(file.getOriginalFilename());
			fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (Exception e) {
			e.getMessage();
		}

		try {
			reader = Files.newBufferedReader(Paths.get(convFile.getAbsolutePath()));
		} catch (Exception e) {
			e.getMessage();
		}
		// listas que se encargan de controlar los datos leidos del csv
		List<String> listaValidar = new ArrayList<>();
		List<String> listaDuplicados = new ArrayList<>();
		List<String> listaMaxCaracteres = new ArrayList<>();
		List<String> listaNulos = new ArrayList<>();
		List<String> listaEjecucion = new ArrayList<>();
		String mensajeRetorno = new String();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		CsvToBean<GrupoVo> csvToBeanGrupo = new CsvToBeanBuilder(reader).withType(GrupoVo.class)
				.withIgnoreLeadingWhiteSpace(true).build();
		// Parceo de la lista de objetos CargosVO
		grupos = csvToBeanGrupo.parse();

		int linea = 1;
		// Se recorre la lista de objetos VO que reprecentan el archivo csv

		for (GrupoVo grupo : grupos) {

			try {
				// Se validan los datos nulos
				if (ut.isNull(grupo.getNombre()) || ut.isNull(grupo.getCodigo()) || ut.isNull(grupo.getUsuario())
						|| ut.isNull(grupo.getMaxdep())) {
					// Se retorna la fila donde se encuentra el valor nulo
					listaNulos.add("Fila: " + linea);
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			// Bloque de validaciones del maximo de caracteres permitidos, por parametro
			if (!ut.isNull(grupo.getNombre()) && ut.getLength(grupo.getNombre(), 100))
				listaMaxCaracteres.add("Fila: " + linea + " - Nombre: " + grupo.getNombre());

			if (!ut.isNull(grupo.getCodigo()) && ut.getLength(grupo.getCodigo(), 10))
				listaMaxCaracteres.add("Fila: " + linea + " - Codigo: " + grupo.getCodigo());

			if (!ut.isNull(grupo.getUsuario()) && ut.getLength(grupo.getUsuario(), 1))
				listaMaxCaracteres.add("Fila: " + linea + " - Usuario: " + grupo.getUsuario());

			if (!ut.isNull(grupo.getMaxdep()) && ut.getLength(grupo.getMaxdep(), 38))
				listaMaxCaracteres.add("Fila: " + linea + " - Maxdep: " + grupo.getMaxdep());

			// validacion de duplicados
			if (listaValidar.contains(grupo.getNombre()))
				listaDuplicados.add("Fila: " + linea + " - Nombre: " + grupo.getNombre());

			if (listaValidar.contains(grupo.getCodigo()))
				listaDuplicados.add("Fila: " + linea + " - Codigo: " + grupo.getCodigo());

			// se agregan los registros a la lista que valida los datos duplicados
			listaValidar.add(grupo.getNombre());
			listaValidar.add(grupo.getCodigo());

			// se itera el contador de filas
			linea++;

		}
		linea = 1;

		// Se valida si existen registros nulos
		if (!listaNulos.isEmpty())
			return ut.validaListaNulos(listaNulos);

		// se valida si la lista de duplicados NO esta vacia
		if (!listaDuplicados.isEmpty()) {
			// se retotna el mensaje de error en caso de poseer valores repetidos
			return ut.validaListaRepetidos(listaDuplicados);

		}

		// Se valida si la lista posee registros con un maximo de caracteres superado al
		// de base de datos
		if (!listaMaxCaracteres.isEmpty())
			// Se retorna el mensaje de error al cumplir la condicion anterior
			return ut.validaListaMaxCaracter(listaMaxCaracteres);

		// En este punto los datos se han validado y por ende se puede realizar el
		// cargue de datos recorriendo el archivo csv

		for (GrupoVo gr : grupos) {
			// Se amacena el mensaje de respuesta al ejecutar el metodo que permite insertar
			// los registros en base de datos
			listaEjecucion.add(obd.agregarGrupo(gr.getNombre(), gr.getUsuario(), gr.getMaxdep(), gr.getCodigo()));
		}

			for (String le : listaEjecucion) {
				mensajeRetorno = (mensajeRetorno + (le + "\n"));

		}
		// Se retorna el mensaje del estado de la operacion
		grupos.clear();
		System.out.println(mensajeRetorno);
		return mensajeRetorno;
	}
}
