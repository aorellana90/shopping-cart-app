package com.alexshopcart.service;

import com.alexshopcart.dto.base.GenericResponseServiceDto;
import com.alexshopcart.dto.fakes.ResponseFakeStoreApiDto;
import com.alexshopcart.dto.fakes.ResponseServiceFakeStoreApi;

public interface IFakesService {

	public ResponseServiceFakeStoreApi getProducts();

	public GenericResponseServiceDto<ResponseFakeStoreApiDto> getProductsById(Long id);

}
