package com.alexshopcart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alexshopcart.component.util.constant.ConstantesGeneral;
import com.alexshopcart.dto.base.GenericResponseServiceDto;
import com.alexshopcart.dto.fakes.ResponseFakeStoreApiDto;
import com.alexshopcart.dto.fakes.ResponseServiceFakeStoreApi;
import com.alexshopcart.service.IFakesService;
import com.alexshopcart.service.consumer.Consumer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FakeStoreServiceImpl implements IFakesService {

	@Value("${fakestoreapi.url}")
	private String urlFakeStoreApi;

	private final Consumer consumer;

	@Override
	public ResponseServiceFakeStoreApi getProducts() {
		ResponseServiceFakeStoreApi respuesta = new ResponseServiceFakeStoreApi();
		List<ResponseFakeStoreApiDto> resultFakeProducts = servicioFakeStoreProducts();

		if (resultFakeProducts == null) {
			respuesta.setSuccess(false);
			respuesta.setMessage("No se encontraron productos en Fake Store API");

			return respuesta;
		} else {
			respuesta.setItem(resultFakeProducts);
			respuesta.setSuccess(true);
		}

		return respuesta;
	}

	@Override
	public GenericResponseServiceDto<ResponseFakeStoreApiDto> getProductsById(Long id) {
		var respuesta = new GenericResponseServiceDto<ResponseFakeStoreApiDto>();
		ResponseFakeStoreApiDto resultFakeProductsId = servicioFakeStoreProductsById(id);

		if (resultFakeProductsId == null) {
			respuesta.setSuccess(false);
			respuesta.setMessage("No se encontraron productos en Fake Store API");

			return respuesta;
		} else {
			respuesta.setItem(resultFakeProductsId);
			respuesta.setSuccess(true);
		}

		return respuesta;
	}

	private final List<ResponseFakeStoreApiDto> servicioFakeStoreProducts() {
		Map<String, String> headers = new HashMap<>();
		headers.put(ConstantesGeneral.NCONTENTTYPE, ConstantesGeneral.APPLICATIONJSON);
		headers.put(ConstantesGeneral.ACCEPTLANGUAGE, ConstantesGeneral.LANGUAGE);

		consumer.setDefaultHeaders(headers);

		return consumer.getOneByQueryStringList(urlFakeStoreApi, "/products").block();
	}

	private ResponseFakeStoreApiDto servicioFakeStoreProductsById(Long id) {
		Map<String, String> headers = new HashMap<>();
		headers.put(ConstantesGeneral.NCONTENTTYPE, ConstantesGeneral.APPLICATIONJSON);
		headers.put(ConstantesGeneral.ACCEPTLANGUAGE, ConstantesGeneral.LANGUAGE);

		consumer.setDefaultHeaders(headers);

		return consumer.getOneByQueryStringOne(urlFakeStoreApi, "/products/" + id, ResponseFakeStoreApiDto.class)
				.block();
	}

}
