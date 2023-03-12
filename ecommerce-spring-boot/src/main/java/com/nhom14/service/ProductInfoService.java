package com.nhom14.service;

import com.nhom14.dto.ProductInfoDTO;

public interface ProductInfoService {
	
	ProductInfoDTO findOneByProductCode(String productCode);
	ProductInfoDTO save(ProductInfoDTO productInfo);
	
}
