package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.ReviewDTO;
import com.nhom14.entity.ReviewEntity;

@Component
public class ReviewConverter implements IConverterToDTO<ReviewEntity, ReviewDTO>, IConverterToEntity<ReviewEntity, ReviewDTO> {

	@Override
	public ReviewEntity toEntity(ReviewDTO dto) {
		ReviewEntity entity = new ReviewEntity();
		entity.setContent(dto.getContent());
		entity.setDate(dto.getDate());
		entity.setRating(dto.getRating());
		return entity;
	}

	public ReviewEntity toEntity(ReviewDTO dto, ReviewEntity entity) {
		entity.setContent(dto.getContent());
		entity.setDate(dto.getDate());
		entity.setRating(dto.getRating());
		return entity;
	}

	@Override
	public List<ReviewEntity> toEntityList(List<ReviewDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

	@Override
	public ReviewDTO toDTO(ReviewEntity entity) {
		ReviewDTO dto = new ReviewDTO();
		
		dto.setUserId(entity.getUser().getId());
		dto.setContent(entity.getContent());
		dto.setDate(entity.getDate());
		dto.setProductCode(entity.getProduct().getCode());
		dto.setRating(entity.getRating());
		dto.setUsername(entity.getUser().getUsername());
		
		return dto;
	}

	@Override
	public List<ReviewDTO> toDTOList(List<ReviewEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

}
