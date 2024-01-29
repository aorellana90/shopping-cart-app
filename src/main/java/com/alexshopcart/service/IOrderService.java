package com.alexshopcart.service;

import java.util.List;

import com.alexshopcart.dto.base.GenericResponseServiceDto;
import com.alexshopcart.dto.order.OrderCreateDto;
import com.alexshopcart.dto.order.OrderDto;

public interface IOrderService {

	public GenericResponseServiceDto<List<OrderDto>> getAll();

	public GenericResponseServiceDto<OrderDto> findOrderByID(Long id);

	public GenericResponseServiceDto<Object> create(OrderCreateDto rquest);

	public GenericResponseServiceDto<Object> update(OrderDto request);

	public GenericResponseServiceDto<Object> delete(Long id);

}
