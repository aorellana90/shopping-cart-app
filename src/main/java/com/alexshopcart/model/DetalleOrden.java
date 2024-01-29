package com.alexshopcart.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "detalleOrden", schema = "sch_control")
@Data
public class DetalleOrden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDetalleOrden")
	private Long idDetalleOrden;

	@ManyToOne
	@JoinColumn(name = "idOrden")
	private Orden orden;

	@Column(name = "idProducto")
	private Long idProducto;

	@Column(name = "cantidad")
	private int cantidad;

	@Column(name = "precio")
	private BigDecimal precio;

	@Column(name = "subtotal")
	private BigDecimal subtotal;

}
