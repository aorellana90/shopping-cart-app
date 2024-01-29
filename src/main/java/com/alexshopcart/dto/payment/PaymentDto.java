package com.alexshopcart.dto.payment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class PaymentDto {

	@Min(value = 1, message = "message.request.id.size")
	private Long idOrden;

	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max = 50, message = "message.request.nombre.size")
	@Pattern(regexp = "^[\\p{L} .'-]+$", message = "message.request.nombre.format")
	private String nombres;

	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max = 50, message = "message.request.nombre.size")
	@Pattern(regexp = "^[\\p{L} .'-]+$", message = "message.request.nombre.format")
	private String apellidos;

	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max = 100, message = "message.request.email.size")
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "error.input.email.format")
	private String correo;

	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max = 20, message = "message.request.phone.size")
	private String telefono;

	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max = 30, message = "message.request.numbercard.size")
	private String numeroTarjeta;

}
