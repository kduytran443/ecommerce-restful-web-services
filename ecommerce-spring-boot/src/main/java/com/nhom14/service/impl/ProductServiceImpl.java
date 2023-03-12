package com.nhom14.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ProductConverter;
import com.nhom14.converter.ProductImageConverter;
import com.nhom14.converter.ProductInfoConverter;
import com.nhom14.dto.ProductDTO;
import com.nhom14.dto.ProductImageDTO;
import com.nhom14.entity.CategoryEntity;
import com.nhom14.entity.ManufacturerEntity;
import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.ProductImageEntity;
import com.nhom14.entity.ProductInfoEntity;
import com.nhom14.repository.CategoryRepository;
import com.nhom14.repository.ManufacturerRepository;
import com.nhom14.repository.ProductRepository;
import com.nhom14.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductImageConverter productImageConverter;

	@Autowired
	private ProductInfoConverter productInfoConverter;

	@Override
	public List<ProductDTO> findAllByCategoryCode(String code, Pageable pageable) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(code);

		List<ProductEntity> products = categoryEntity.getProducts();
		Integer length = products.size();
		int minIndex = pageable.getPageNumber() * pageable.getPageSize();
		int maxIndex = minIndex + pageable.getPageSize() - 1;

		List<ProductEntity> filterProduct = IntStream.range(0, length)
				.filter(index -> (index >= minIndex && index <= maxIndex)).mapToObj(index -> products.get(index))
				.collect(Collectors.toList());
		return productConverter.toDTOList(filterProduct);
	}

	@Override
	public List<ProductDTO> findAllByManufacturerCode(String code, Pageable pageable) {
		ManufacturerEntity manufacturerEntity = manufacturerRepository.findOneByCode(code);

		List<ProductEntity> products = manufacturerEntity.getProducts();
		Integer length = products.size();
		int minIndex = (pageable.getPageNumber() - 1) * pageable.getOffset();
		int maxIndex = minIndex + pageable.getOffset();

		List<ProductEntity> filterProduct = IntStream.range(0, length)
				.filter(index -> (index >= minIndex && index <= maxIndex)).mapToObj(index -> products.get(index))
				.collect(Collectors.toList());

		return productConverter.toDTOList(filterProduct);
	}

	@Override
	public int countByCategoryCode(String code) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(code);
		if (categoryEntity.getProducts() == null)
			return 0;
		return categoryEntity.getProducts().size();
	}

	@Override
	public int countByManufacturerCode(String code) {
		ManufacturerEntity manufacturerEntity = manufacturerRepository.findOneByCode(code);
		if (manufacturerEntity.getProducts() == null)
			return 0;
		return manufacturerEntity.getProducts().size();
	}

	@Override
	public Long countAll() {
		return productRepository.count();
	}

	@Override
	public ProductDTO save(ProductDTO productDTO) {
		ProductEntity productEntity = null;
		if (productDTO.getId() != null) {
			productEntity = productRepository.findOne(productDTO.getId());

			productEntity = productConverter.toEntity(productDTO, productEntity);
		} else {
			productEntity = productConverter.toEntity(productDTO);

			CategoryEntity categoryEntity = categoryRepository.findOneByCode(productDTO.getCategoryCode());

			productEntity.setCategory(categoryEntity);

			if (productDTO.getManufacturer() != null) {
				ManufacturerEntity manufacturerEntity = manufacturerRepository
						.findOne(productDTO.getManufacturer().getId());
				productEntity.setManufacturer(manufacturerEntity);
			}

			if (productDTO.getProductImages() != null) {
				List<ProductImageDTO> productImageDTOs = productDTO.getProductImages();

				List<ProductImageEntity> productImageEntities = productImageConverter.toEntityList(productImageDTOs);

				productEntity.setProductImages(productImageEntities);
			}

			if (productDTO.getProductInfo() != null) {
				ProductInfoEntity productInfoEntity = productInfoConverter.toEntity(productDTO.getProductInfo());
				productEntity.setProductInfo(productInfoEntity);
			}
		}
		
		if(productEntity != null) {			
			productEntity = productRepository.save(productEntity);
			return productConverter.toDTO(productEntity);
		}

		return null;
	}

	@Override
	public void delete(ProductDTO productDTO) {
		ProductEntity productEntity = productRepository.findOne(productDTO.getId());
		productEntity.setStatus(0);
		productEntity = productRepository.save(productEntity);
	}

	@Override
	public ProductDTO findOneByCode(String code) {
		return productConverter.toDTO(productRepository.findOneByCode(code));
	}

}
