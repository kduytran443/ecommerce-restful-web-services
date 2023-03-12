package com.nhom14.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.nhom14.converter.DiscountConverter;
import com.nhom14.dto.DiscountDTO;
import com.nhom14.entity.DiscountEntity;
import com.nhom14.entity.ProductEntity;
import com.nhom14.repository.DiscountRepository;
import com.nhom14.repository.ProductRepository;
import com.nhom14.service.DiscountService;

public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountRepository discountRepository;

	@Autowired
	private DiscountConverter discountConverter;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<DiscountDTO> findAll() {
		return discountConverter.toDTOList(discountRepository.findAllByOrderByIdAsc());
	}

	@Override
	public List<DiscountDTO> findOneByProductCode(String productCode) {

		ProductEntity productEntity = productRepository.findOneByCode(productCode);
		if (productEntity != null) {
			List<DiscountEntity> discountEntities = productEntity.getDiscounts();
			return discountConverter.toDTOList(discountEntities);
		}

		return null;
	}

	@Override
	public DiscountDTO findOneById(Long id) {

		DiscountEntity discountEntity = discountRepository.findOne(id);
		if (discountEntity != null) {
			return discountConverter.toDTO(discountEntity);
		}

		return null;
	}

	@Override
	public DiscountDTO save(DiscountDTO discountDTO) {
		DiscountEntity discountEntity = null;

		if (discountDTO.getId() != null) {
			discountEntity = discountRepository.findOne(discountDTO.getId());
			if (discountEntity != null) {
				discountEntity = discountConverter.toEntity(discountDTO, discountEntity);
			}
		} else {
			discountEntity = discountConverter.toEntity(discountDTO);
		}

		return null;
	}

	@Override
	public void delete(DiscountDTO discountDTO) {
		discountRepository.delete(discountDTO.getId());
	}

	@Override
	public List<DiscountDTO> findAllActive() {
		Date date = new Date();

		List<DiscountEntity> discountEntities = discountRepository.findAllByOrderByIdAsc().stream()
				.filter(discount -> discount.getEndTime().getTime() > date.getTime()).collect(Collectors.toList());
		
		return discountConverter.toDTOList(discountEntities);
	}

}
