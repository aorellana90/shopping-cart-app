package com.alexshopcart.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "orden", schema = "sch_control")
@Data
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idOrden")
	private Long idOrden;

	@Column(name = "fechaCreacion")
	private Timestamp fechaCreacion;

	@Column(name = "fechaModificacion")
	private Timestamp fechaModificacion;

	@Column(name = "cliente")
	private String cliente;

	@Column(name = "estadoOrden")
	private String estadoOrden;

	@Column(name = "metodoPago")
	private String metodoPago;

	@Column(name = "estadoPago")
	private String estadoPago;

	@Column(name = "total")
	private BigDecimal total;

	@OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
	private List<DetalleOrden> detallesOrden;

}
