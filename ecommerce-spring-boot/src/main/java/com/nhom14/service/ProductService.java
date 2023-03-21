package com.nhom14.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nhom14.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> findAllByCategoryCode(String code, Pageable pageable);
	List<ProductDTO> findAll();
	List<ProductDTO> findAllByManufacturerCode(String code, Pageable pageable);
	ProductDTO findOneByCode(String code);
	int countByCategoryCode(String code);
	int countByManufacturerCode(String code);
	Long countAll();
	ProductDTO save(ProductDTO productDTO);
	void delete(ProductDTO productDTO);
	List<ProductDTO> searchByName(String name);
	List<ProductDTO> getAllProductDetails();
	List<ProductDTO> getAllProductDetailsByName(String name);
	List<ProductDTO> getAllProductFavoritedByUser(Long userId);
	boolean checkFavorited(String productCode, Long userId);
	void favorite(String productCode, Long userId);
}
