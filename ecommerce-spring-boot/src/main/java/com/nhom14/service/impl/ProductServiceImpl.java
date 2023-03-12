package com.nhom14.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ProductConverter;
import com.nhom14.dto.ProductDTO;
import com.nhom14.entity.CategoryEntity;
import com.nhom14.entity.ManufacturerEntity;
import com.nhom14.entity.ProductEntity;
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

	@Override
	public List<ProductDTO> findAllByCategoryCode(String code, Pageable pageable) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(code);

		List<ProductEntity> products = categoryEntity.getProducts();
		Integer length = products.size();
		int minIndex = pageable.getPageNumber() * pageable.getPageSize();
		int maxIndex = minIndex + pageable.getPageSize() - 1;
		
		System.out.println("minIndex "+minIndex);
		System.out.println("maxIndex "+maxIndex);

		List<ProductEntity> filterProduct = IntStream.range(0, length).filter(index -> (index >= minIndex && index <= maxIndex))
				.mapToObj(index -> products.get(index)).collect(Collectors.toList());
		System.out.println("size: "+filterProduct.size());
		return productConverter.toDTOList(filterProduct);
	}

	@Override
	public List<ProductDTO> findAllByManufacturerCode(String code, Pageable pageable) {
		ManufacturerEntity manufacturerEntity = manufacturerRepository.findOneByCode(code);

		List<ProductEntity> products = manufacturerEntity.getProducts();
		Integer length = products.size();
		int minIndex = (pageable.getPageNumber() - 1) * pageable.getOffset();
		int maxIndex = minIndex + pageable.getOffset();

		List<ProductEntity> filterProduct = IntStream.range(0, length).filter(index -> (index >= minIndex && index <= maxIndex))
				.mapToObj(index -> products.get(index)).collect(Collectors.toList());

		return productConverter.toDTOList(filterProduct);
	}

	@Override
	public int countByCategoryCode(String code) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(code);
		if(categoryEntity.getProducts() == null) return 0;
		return categoryEntity.getProducts().size();
	}

	@Override
	public int countByManufacturerCode(String code) {
		ManufacturerEntity manufacturerEntity = manufacturerRepository.findOneByCode(code);
		// TODO Auto-generated method stub
		if(manufacturerEntity.getProducts() == null) return 0;
		return manufacturerEntity.getProducts().size();
	}

	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return productRepository.count();
	}

}
