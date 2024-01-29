package com.alexshopcart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexshopcart.component.util.ServiceFactory;
import com.alexshopcart.component.util.constant.ConstantesGeneral;
import com.alexshopcart.component.util.log.LogUtil;
import com.alexshopcart.component.util.log.LogUtil.TYPELOG;
import com.alexshopcart.dto.base.GenericResponseDto;
import com.alexshopcart.service.IFakesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fakestore")
@RequiredArgsConstructor
public class FakeStoreController {

	private final LogUtil logs;

	private final IFakesService fakesService;

	@GetMapping("/products")
	@Operation(summary = "Service products", description = "Obtiene todos los productos de Fake Store API")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> getProducts() {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller FakeStoreController / getProducts() backend.shoppingcart.controller, ");

		var respuesta = fakesService.getProducts();

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "No se encontraron productos en Fake Store API");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}

	@GetMapping("/productsById/{idProduct}")
	@Operation(summary = "Servicio get a product", description = "Obtiene por ID productos de Fake Store API")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> getProductsById(@PathVariable Long idProduct) {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller FakeStoreController / getProductsById() backend.shoppingcart.controller, ");

		var respuesta = fakesService.getProductsById(idProduct);

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "No se encontraron productos por ID en Fake Store API");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}

}
