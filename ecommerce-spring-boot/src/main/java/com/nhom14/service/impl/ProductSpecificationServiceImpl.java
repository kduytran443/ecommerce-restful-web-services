package com.nhom14.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ProductSpecificationConverter;
import com.nhom14.dto.ProductSpecificationDTO;
import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.ProductSpecificationEntity;
import com.nhom14.entity.SpecificationEntity;
import com.nhom14.entity.id.ProductSpecificationsId;
import com.nhom14.repository.ProductRepository;
import com.nhom14.repository.ProductSpecificationRepository;
import com.nhom14.repository.SpecificationRepository;
import com.nhom14.service.ProductSpecificationService;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

	@Autowired
	private ProductSpecificationRepository productSpecificationRepository;

	@Autowired
	private ProductSpecificationConverter productSpecificationConverter;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SpecificationRepository specificationRepository;

	@Override
	public List<ProductSpecificationDTO> findAllByProductCode(String productCode) {

		ProductEntity productEntity = productRepository.findOneByCode(productCode);

		if (productEntity != null) {
			System.out.println("productCode "+productCode);
			List<ProductSpecificationEntity> productSpecificationEntities = productSpecificationRepository
					.findAllByProduct(productEntity);
			System.out.println("productSpecificationEntities "+productSpecificationEntities.size());
			return productSpecificationConverter.toDTOList(productSpecificationEntities);
		}

		return null;
	}

	@Override
	public ProductSpecificationDTO save(ProductSpecificationDTO productSpecificationDTO) {
		ProductEntity productEntity = productRepository.findOne(productSpecificationDTO.getProductId());
		SpecificationEntity specificationEntity = specificationRepository
				.findOne(productSpecificationDTO.getSpecificationId());

		if (productEntity != null && specificationEntity != null) {
			ProductSpecificationEntity productSpecificationEntity = productSpecificationRepository
					.findOneByProductAndSpecification(productEntity, specificationEntity);

			if (productSpecificationEntity == null) {
				productSpecificationEntity = productSpecificationConverter.toEntity(productSpecificationDTO);
				productSpecificationEntity.setSpecification(specificationEntity);
				productSpecificationEntity.setProduct(productEntity);

				ProductSpecificationsId productSpecificationsId = new ProductSpecificationsId();
				productSpecificationsId.setProductId(productSpecificationDTO.getProductId());
				productSpecificationsId.setSpecificationId(productSpecificationDTO.getSpecificationId());

				productSpecificationEntity.setProductSpecificationsId(productSpecificationsId);
			} else {
				productSpecificationEntity = productSpecificationConverter.toEntity(productSpecificationDTO,
						productSpecificationEntity);
			}

			productSpecificationEntity = productSpecificationRepository.save(productSpecificationEntity);
			return productSpecificationConverter.toDTO(productSpecificationEntity);
		}

		return null;
	}

	@Override
	public void delete(ProductSpecificationDTO productSpecificationDTO) {
		ProductEntity productEntity = productRepository.findOne(productSpecificationDTO.getProductId());
		SpecificationEntity specificationEntity = specificationRepository
				.findOne(productSpecificationDTO.getSpecificationId());
		ProductSpecificationEntity productSpecificationEntity = productSpecificationRepository
				.findOneByProductAndSpecification(productEntity, specificationEntity);
		productSpecificationRepository.delete(productSpecificationEntity);
	}

}
