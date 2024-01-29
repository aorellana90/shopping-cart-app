package com.alexshopcart.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexshopcart.component.util.ServiceFactory;
import com.alexshopcart.component.util.constant.ConstantesGeneral;
import com.alexshopcart.component.util.log.LogUtil;
import com.alexshopcart.component.util.log.LogUtil.TYPELOG;
import com.alexshopcart.dto.base.GenericResponseDto;
import com.alexshopcart.dto.order.OrderCreateDto;
import com.alexshopcart.dto.order.OrderDto;
import com.alexshopcart.service.IOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrdenController {

	private final LogUtil logs;

	private final IOrderService orderService;

	@GetMapping("/getAll")
	@Operation(summary = "Servicio getAll", description = "Obtiene todas las ordenes registradas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> getAll() {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller OrdenController / getAll() backend.shoppingcart.controller, ");

		var respuesta = orderService.getAll();

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "Error al obtener todas las ordenes");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}

	@GetMapping("/findBy/{idOrder}")
	@Operation(summary = "Service findBy idOrder", description = "Obtiene por ID una orden registrada")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> findById(@PathVariable Long idOrder) {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller OrdenController / findBy() backend.shoppingcart.controller, ");

		var respuesta = orderService.findOrderByID(idOrder);

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "Error al obetener orden por ID");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}

	@PostMapping("save")
	@Operation(summary = "Service save", description = "Registra una orden y su detalle")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> save(@RequestBody @Valid OrderCreateDto request) {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller OrdenController / save() backend.shoppingcart.controller, ");

		var respuesta = orderService.create(request);

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "Error al registrar orden");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}

	@PutMapping("edit")
	@Operation(summary = "Service edit", description = "Modifica por ID una orden registrada")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> update(@RequestBody @Valid OrderDto request) {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller OrdenController / update() backend.shoppingcart.controller, ");

		var respuesta = orderService.update(request);

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "Error al editar orden");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}

	@DeleteMapping("/delete/{idOrder}")
	@Operation(summary = "Service delete", description = "Elimina por ID una orden registrada")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> delete(@PathVariable Long idOrden) {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller OrdenController / delete() backend.shoppingcart.controller, ");

		var respuesta = orderService.delete(idOrden);

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "Error al eliminar orden");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}

}
