package com.nhom14.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ProductImageConverter;
import com.nhom14.dto.ProductImageDTO;
import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.ProductImageEntity;
import com.nhom14.repository.ProductImageRepository;
import com.nhom14.repository.ProductRepository;
import com.nhom14.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductImageRepository productImageRepository;

	@Autowired
	private ProductImageConverter productImageConverter;

	@Override
	public List<ProductImageDTO> findAllByProductCode(String productCode) {
		ProductEntity product = productRepository.findOneByCode(productCode);
		if (product != null) {
			List<ProductImageEntity> entities = product.getProductImages();
			return productImageConverter.toDTOList(entities);
		}

		return null;
	}

	@Override
	public ProductImageDTO save(ProductImageDTO productImageDTO) {

		if (productImageDTO.getId() == null) {
			ProductImageEntity entity = productImageConverter.toEntity(productImageDTO);
			entity = productImageRepository.save(entity);

			ProductEntity productEntity = productRepository.findOneByCode(productImageDTO.getProductCode());

			entity.setProduct(productEntity);
			return productImageConverter.toDTO(entity);
		}

		return null;
	}

	@Override
	public void delete(ProductImageDTO productImageDTO) {
		productImageRepository.delete(productImageDTO.getId());
	}

}
