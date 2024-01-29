package com.alexshopcart.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.alexshopcart.dao.IOrderDao;
import com.alexshopcart.dao.IPaymentDao;
import com.alexshopcart.dto.base.GenericResponseServiceDto;
import com.alexshopcart.dto.payment.PaymentDto;
import com.alexshopcart.dto.payment.PaymentUpdateDto;
import com.alexshopcart.model.Orden;
import com.alexshopcart.model.Pago;
import com.alexshopcart.service.IPaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

	private final IOrderDao orderDao;
	private final ModelMapper modelMapper;
	private final IPaymentDao paymentDao;

	@Override
	public GenericResponseServiceDto<Object> create(PaymentDto request) {
		var response = new GenericResponseServiceDto<Object>();

		try {
			Optional<Orden> resOrden = orderDao.findById(request.getIdOrden());

			if (resOrden.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("No se encontro ninguna orden");

				return response;
			}

			Optional<Pago> resPago = paymentDao.findByOrder(request.getIdOrden());

			if (!resPago.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("Ya existe un pago registrado");

				return response;
			}

			Pago pago = mapperPaymentDtoToPayment(request);

			paymentDao.save(pago);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();

			response.setSuccess(false);
			response.setMessage("Ocurrio un error en servicio de registro pagos");
		}

		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> update(PaymentUpdateDto request) {
		var response = new GenericResponseServiceDto<Object>();

		try {
			Optional<Orden> resOrden = orderDao.findById(request.getIdOrden());

			if (resOrden.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("No se encontro ninguna orden");

				return response;
			}

			Optional<Pago> resPago = paymentDao.findByPayment(request.getIdPago());

			if (resPago.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("No existe ningun pago registrado");

				return response;
			}

			Pago pago = mapperPaymentUpdateDtoToPayment(request);

			paymentDao.save(pago);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();

			response.setSuccess(false);
			response.setMessage("Ocurrio un error en servicio de edicion pagos");
		}

		return response;
	}

	private Pago mapperPaymentDtoToPayment(PaymentDto request) {
		Pago payment = modelMapper.map(request, Pago.class);

		return payment;
	}

	private Pago mapperPaymentUpdateDtoToPayment(PaymentUpdateDto request) {
		Pago payment = modelMapper.map(request, Pago.class);

		return payment;
	}

}
