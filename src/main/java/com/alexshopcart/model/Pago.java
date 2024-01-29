package com.alexshopcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pago", schema = "sch_control")
@Data
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPago")
	private Long idPago;

	@Column(name = "idOrden")
	private Long idOrden;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "correo")
	private String correo;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "numeroTarjeta")
	private String numeroTarjeta;

}
