package com.nhom14.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.CategoryConverter;
import com.nhom14.dto.CategoryDTO;
import com.nhom14.entity.CategoryEntity;
import com.nhom14.repository.CategoryRepository;
import com.nhom14.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@Override
	public List<CategoryDTO> findAll() {
		return categoryConverter.toDTOList(categoryRepository.findAllByStatus(1));
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = null;
		if(categoryDTO.getId() != null) {	//edit
			categoryEntity = categoryRepository.findOne(categoryDTO.getId());
			categoryEntity = categoryConverter.toEntity(categoryDTO, categoryEntity);
		} else {
			categoryEntity = new CategoryEntity();
			
			CategoryEntity checkCategory = categoryRepository.findOneByCode(categoryDTO.getCode());
			if(checkCategory != null) {
				throw new RuntimeException("Mã code đã tồn tại");
			}
			
			categoryEntity = categoryConverter.toEntity(categoryDTO);
		}
		
		if(categoryEntity != null) categoryEntity = categoryRepository.save(categoryEntity);
		
		return categoryConverter.toDTO(categoryEntity);
	}

	@Override
	public void delete(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = categoryRepository.findOne(categoryDTO.getId());
		categoryEntity.setStatus(0);
		categoryEntity = categoryRepository.save(categoryEntity);
	}

	@Override
	public CategoryDTO findOneByCode(String code) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(code);
		return categoryConverter.toDTO(categoryEntity);
	}

}
