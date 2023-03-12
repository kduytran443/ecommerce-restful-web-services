package com.nhom14.dto.search;

import java.util.List;

import com.nhom14.dto.CategoryDTO;
import com.nhom14.dto.ManufacturerDTO;

public class ProductSearchDTO {
	private List<CategoryDTO> categories;
	private Long minPrice;
	private Long maxPrice;
	private List<Integer> Status;
	private String name;
	private List<ManufacturerDTO> manufacturer;
	
	
	
}
