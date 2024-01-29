package com.alexshopcart.dto.order;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {

	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max = 250, message = "message.request.orden.size")
	private String cliente;

	@DecimalMin(value = "0.00")
	@NotNull
	private BigDecimal total;

	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max = 15, message = "message.request.statusorder.size")
	private String estadoOrden;

	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max = 15, message = "message.request.statusorder.size")
	private String metodoPago;

	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max = 15, message = "message.request.statusorder.size")
	private String estadoPago;

	@Valid
	@NotEmpty(message = "message.request.list.empty")
	private List<DetailsCreateDto> detallesOrden;

}
