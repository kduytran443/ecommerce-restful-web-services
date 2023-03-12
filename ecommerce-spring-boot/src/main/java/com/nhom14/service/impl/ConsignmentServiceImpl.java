package com.nhom14.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ConsignmentConverter;
import com.nhom14.dto.ConsignmentDTO;
import com.nhom14.entity.ConsignmentEntity;
import com.nhom14.entity.ProductEntity;
import com.nhom14.repository.ConsignmentRepository;
import com.nhom14.repository.ProductRepository;
import com.nhom14.service.ConsignmentService;

@Service
public class ConsignmentServiceImpl implements ConsignmentService {

	@Autowired
	private ConsignmentConverter consignmentConverter;

	@Autowired
	private ConsignmentRepository consignmentRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ConsignmentDTO> findAll() {
		List<ConsignmentEntity> entities = consignmentRepository.findAll();
		return consignmentConverter.toDTOList(entities);
	}

	@Override
	public List<ConsignmentDTO> findAllByProductCode(String productCode) {
		ProductEntity productEntity = productRepository.findOneByCode(productCode);
		if (productEntity.getConsigments() != null) {
			List<ConsignmentEntity> entities = productEntity.getConsigments();
			return consignmentConverter.toDTOList(entities);
		}
		return null;
	}

	@Override
	public ConsignmentDTO save(ConsignmentDTO consignmentDTO) {

		ConsignmentEntity consignmentEntity = null;

		if (consignmentDTO.getId() != null) { // edit
			consignmentEntity = consignmentRepository.findOne(consignmentDTO.getId());
			consignmentEntity = consignmentConverter.toEntity(consignmentDTO, consignmentEntity);
			// Còn thiếu thống kê hàng đã bán do chưa có làm phần order
		} else { // add new
			consignmentEntity = consignmentConverter.toEntity(consignmentDTO);
		}

		if (consignmentEntity != null) {
			consignmentEntity = consignmentRepository.save(consignmentEntity);
			return consignmentConverter.toDTO(consignmentEntity);
		}

		return null;
	}

	@Override
	public void delete(ConsignmentDTO consignmentDTO) {
		consignmentRepository.delete(consignmentDTO.getId());
	}

}
