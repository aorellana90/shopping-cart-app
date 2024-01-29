package com.alexshopcart.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
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
import com.alexshopcart.dto.payment.PaymentDto;
import com.alexshopcart.dto.payment.PaymentUpdateDto;
import com.alexshopcart.service.IPaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PagoController {

	private final LogUtil logs;

	private final IPaymentService paymentService;

	@PostMapping("save")
	@Operation(summary = "Service save", description = "Registra un pago")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> create(@RequestBody @Valid PaymentDto request) {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller PagoController / create() backend.shoppingcart.controller, ");

		var respuesta = paymentService.create(request);

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "Error al registar pago");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}

	@PutMapping("edit")
	@Operation(summary = "Service edit", description = "Modifica por ID un pago registrada")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> edit(@RequestBody @Valid PaymentUpdateDto request) {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller OrdenController / edit() backend.shoppingcart.controller, ");

		var respuesta = paymentService.update(request);

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "Error al modificar pago");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}

}
