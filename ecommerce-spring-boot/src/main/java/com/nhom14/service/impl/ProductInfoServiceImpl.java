package com.nhom14.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ProductInfoConverter;
import com.nhom14.dto.ProductInfoDTO;
import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.ProductInfoEntity;
import com.nhom14.repository.ProductInfoRepository;
import com.nhom14.repository.ProductRepository;
import com.nhom14.service.ProductInfoService;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Autowired
	private ProductInfoConverter productInfoConverter;

	@Override
	public ProductInfoDTO findOneByProductCode(String productCode) {

		ProductEntity productEntity = productRepository.findOneByCode(productCode);

		if (productEntity != null) {
			return productInfoConverter.toDTO(productEntity.getProductInfo());
		}

		return null;
	}

	@Override
	public ProductInfoDTO save(ProductInfoDTO productInfo) {

		ProductInfoEntity productInfoEntity = productInfoRepository.findOne(productInfo.getId());
		if (productInfoEntity != null) {
			productInfoEntity = productInfoConverter.toEntity(productInfo, productInfoEntity);
			return productInfoConverter.toDTO(productInfoEntity);
		}

		return null;
	}

}
