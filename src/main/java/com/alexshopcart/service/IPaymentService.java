package com.alexshopcart.service;

import com.alexshopcart.dto.base.GenericResponseServiceDto;
import com.alexshopcart.dto.payment.PaymentDto;
import com.alexshopcart.dto.payment.PaymentUpdateDto;

public interface IPaymentService {
	public GenericResponseServiceDto<Object> create(PaymentDto rquest);
	public GenericResponseServiceDto<Object> update(PaymentUpdateDto rquest);
}
