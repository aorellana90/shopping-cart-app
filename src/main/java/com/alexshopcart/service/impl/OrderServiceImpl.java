package com.alexshopcart.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.alexshopcart.dao.IOrderDao;
import com.alexshopcart.dto.base.GenericResponseServiceDto;
import com.alexshopcart.dto.fakes.RequestGetOneDto;
import com.alexshopcart.dto.fakes.ResponseFakeStoreApiDto;
import com.alexshopcart.dto.order.DetailsCreateDto;
import com.alexshopcart.dto.order.DetailsUpdateDto;
import com.alexshopcart.dto.order.OrderCreateDto;
import com.alexshopcart.dto.order.OrderDto;
import com.alexshopcart.model.Orden;
import com.alexshopcart.model.DetalleOrden;
import com.alexshopcart.service.IFakesService;
import com.alexshopcart.service.IOrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

	private final IOrderDao orderDao;
	private final IFakesService fakesService;
	private final ModelMapper modelMapper;

	/**
	 * Servicio que retorna todas las ordenes
	 */
	@Override
	public GenericResponseServiceDto<List<OrderDto>> getAll() {
		List<OrderDto> listDtoOrder = new ArrayList<>();
		var response = new GenericResponseServiceDto<List<OrderDto>>();

		List<Orden> listOrder = orderDao.orderList();

		if (listOrder.isEmpty()) {
			response.setMessage("No se encontraron datos");
			response.setSuccess(false);
			response.setItem(listDtoOrder);
		} else {
			listDtoOrder = mapperListOrderToListOrderDto(listOrder);

			response.setSuccess(true);
			response.setItem(listDtoOrder);
		}
		return response;
	}

	/**
	 * Servicio que retorna Orden por ID
	 */
	@Override
	public GenericResponseServiceDto<OrderDto> findOrderByID(Long id) {
		var response = new GenericResponseServiceDto<OrderDto>();

		Optional<Orden> result = orderDao.findOrderById(id);

		if (result.isEmpty()) {
			response.setMessage("No se encontro el ID de la orden");
			response.setSuccess(false);
		} else {
			OrderDto orderDto = mapperOrderToOrderDto(result.get());

			response.setSuccess(true);
			response.setItem(orderDto);
		}
		return response;
	}

	/**
	 * Servicio que crea la orden
	 */
	@Override
	public GenericResponseServiceDto<Object> create(OrderCreateDto request) {
		var response = new GenericResponseServiceDto<Object>();

		try {
			int contador = 0;

			for (DetailsCreateDto detailsOrderDto : request.getDetallesOrden()) {
				RequestGetOneDto requestCheck = new RequestGetOneDto();
				requestCheck.setIdProducto(detailsOrderDto.getIdProducto());

				GenericResponseServiceDto<ResponseFakeStoreApiDto> resultProduct = fakesService
						.getProductsById(detailsOrderDto.getIdProducto());

				if (Boolean.FALSE.equals(resultProduct.getSuccess())) {
					response.setSuccess(false);
					response.setMessage("El producto con ID: " + requestCheck.getIdProducto() + " no existe");

					return response;
				}

				if (!detailsOrderDto.getPrecio().equals(resultProduct.getItem().getPrice())) {
					request.getDetallesOrden().get(contador).setPrecio((resultProduct.getItem().getPrice()));
				}

				contador++;
			}

			Orden orden = mapperOrderCreateDtoToOrder(request);

			orderDao.save(orden);
			response.setSuccess(true);
			response.setMessage("Orden registrada");
		} catch (Exception e) {
			e.printStackTrace();

			response.setSuccess(false);
			response.setMessage("Ocurrio un error insperado en servicio de registro orden");
		}

		return response;
	}

	/**
	 * Servicio que actualiza la orden
	 */
	@Override
	public GenericResponseServiceDto<Object> update(OrderDto request) {
		var response = new GenericResponseServiceDto<Object>();
		try {
			Optional<Orden> resOrdenId = orderDao.findById(request.getIdOrden());

			if (resOrdenId.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("No se encontro registro de la orden: " + request.getIdOrden());

				return response;
			}

			Orden order = resOrdenId.get();
			int contador = 0;

			for (DetailsUpdateDto detailsUpdateDto : request.getDetallesOrden()) {
				RequestGetOneDto requestCheck = new RequestGetOneDto();
				requestCheck.setIdProducto(detailsUpdateDto.getIdProducto());

				GenericResponseServiceDto<ResponseFakeStoreApiDto> resultProduct = fakesService
						.getProductsById(detailsUpdateDto.getIdProducto());

				if (Boolean.FALSE.equals(resultProduct.getSuccess())) {
					response.setSuccess(false);
					response.setMessage("El producto con ID: " + requestCheck.getIdProducto() + " no existe");

					return response;
				}

				if (!detailsUpdateDto.getPrecio().equals(resultProduct.getItem().getPrice())) {
					request.getDetallesOrden().get(contador).setPrecio((resultProduct.getItem().getPrice()));
				}

				contador++;
			}

			order = mapperOrderUpdateDtoToOrder(request);

			orderDao.save(order);

			response.setSuccess(true);
			response.setMessage("Orden actualizada");
		} catch (Exception e) {
			e.printStackTrace();

			response.setSuccess(false);
			response.setMessage("Ocurrio un error insperado en servicio de actualizacion orden");
		}

		return response;
	}

	/**
	 * Servicio que elimina la orden
	 */
	@Override
	public GenericResponseServiceDto<Object> delete(Long id) {
		var response = new GenericResponseServiceDto<Object>();

		Optional<Orden> orderOpt = orderDao.findById(id);

		if (orderOpt.isEmpty()) {
			response.setMessage("No se encontro registro de la orden: " + id);
			response.setSuccess(false);

			return response;
		} else {
			Orden order = orderOpt.get();

			orderDao.delete(order);

			response.setMessage("Orden eliminada");
			response.setSuccess(true);
		}

		return response;
	}

	private OrderDto mapperOrderToOrderDto(Orden request) {
		OrderDto order = modelMapper.map(request, OrderDto.class);

		order.setDetallesOrden(modelMapper.map(request.getDetallesOrden(), new TypeToken<List<DetailsUpdateDto>>() {
		}.getType()));

		return order;
	}

	private List<OrderDto> mapperListOrderToListOrderDto(List<Orden> request) {
		List<OrderDto> listOrder = new ArrayList<>();

		for (Orden order : request) {
			OrderDto orderUpd = modelMapper.map(order, OrderDto.class);

			orderUpd.setDetallesOrden(
					modelMapper.map(order.getDetallesOrden(), new TypeToken<List<DetailsUpdateDto>>() {
					}.getType()));

			listOrder.add(orderUpd);
		}

		return listOrder;
	}

	private Orden mapperOrderCreateDtoToOrder(OrderCreateDto request) {
		Orden orden = modelMapper.map(request, Orden.class);
		List<DetalleOrden> detailsList = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;
		Date fechaHora = new Date();
		Timestamp actualFechaHora = new Timestamp(fechaHora.getTime());

		for (DetailsCreateDto detailDto : request.getDetallesOrden()) {
			DetalleOrden orderDetail = new DetalleOrden();
			orderDetail.setOrden(orden);
			orderDetail.setIdProducto(detailDto.getIdProducto());
			orderDetail.setCantidad(detailDto.getCantidad());
			orderDetail.setPrecio(detailDto.getPrecio());
			BigDecimal quantityBigDecimal = BigDecimal.valueOf(detailDto.getCantidad());
			BigDecimal subtotal = quantityBigDecimal.multiply(detailDto.getPrecio());
			orderDetail.setSubtotal(subtotal);
			total = total.add(subtotal);
			detailsList.add(orderDetail);
		}

		orden.setFechaCreacion(actualFechaHora);
		orden.setDetallesOrden(detailsList);
		orden.setTotal(total);

		return orden;
	}

	private Orden mapperOrderUpdateDtoToOrder(OrderDto request) {
		Orden orden = modelMapper.map(request, Orden.class);
		List<DetalleOrden> detailsList = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;
		Date fechaHora = new Date();
		Timestamp actualFechaHora = new Timestamp(fechaHora.getTime());

		for (DetailsUpdateDto detailDto : request.getDetallesOrden()) {
			DetalleOrden orderDetail = new DetalleOrden();
			orderDetail.setIdDetalleOrden(detailDto.getId());
			orderDetail.setOrden(orden);
			orderDetail.setIdProducto(detailDto.getIdProducto());
			orderDetail.setCantidad(detailDto.getCantidad());
			orderDetail.setPrecio(detailDto.getPrecio());
			BigDecimal quantityBigDecimal = BigDecimal.valueOf(detailDto.getCantidad());
			BigDecimal subtotal = quantityBigDecimal.multiply(detailDto.getPrecio());
			orderDetail.setSubtotal(subtotal);
			total = total.add(subtotal);
			detailsList.add(orderDetail);
		}

		orden.setFechaCreacion(actualFechaHora);
		orden.setFechaModificacion(actualFechaHora);
		orden.setDetallesOrden(detailsList);
		orden.setTotal(total);

		return orden;
	}

}
