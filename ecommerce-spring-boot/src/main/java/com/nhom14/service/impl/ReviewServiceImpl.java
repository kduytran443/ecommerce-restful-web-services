package com.nhom14.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ReviewConverter;
import com.nhom14.dto.ReviewDTO;
import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.ReviewEntity;
import com.nhom14.entity.UserEntity;
import com.nhom14.entity.id.ReviewId;
import com.nhom14.repository.ProductRepository;
import com.nhom14.repository.ReviewRepository;
import com.nhom14.repository.UserRepository;
import com.nhom14.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ReviewConverter reviewConverter;

	@Override
	public List<ReviewDTO> findAllByProductCode(String productCode) {
		ProductEntity productEntity = productRepository.findOneByCode(productCode);
		List<ReviewEntity> reviewEntities = reviewRepository.findAllByProduct(productEntity);
		return reviewConverter.toDTOList(reviewEntities);
	}

	@Override
	public List<ReviewDTO> findAllByUserId(Long id) {
		UserEntity userEntity = userRepository.findOne(id);
		List<ReviewEntity> reviewEntities = reviewRepository.findAllByUser(userEntity);
		return reviewConverter.toDTOList(reviewEntities);
	}

	@Override
	public ReviewDTO save(ReviewDTO reviewDTO) {
		ProductEntity productEntity = productRepository.findOneByCode(reviewDTO.getProductCode());
		UserEntity userEntity = userRepository.findOneByUsername(reviewDTO.getUsername());

		if (productEntity != null && userEntity != null) {
			ReviewEntity reviewEntity = reviewRepository.findOneByProductAndUser(productEntity, userEntity);

			if (reviewEntity != null) {
				reviewEntity = reviewConverter.toEntity(reviewDTO, reviewEntity);
			} else {
				reviewEntity = reviewConverter.toEntity(reviewDTO);

				reviewEntity.setUser(userEntity);
				reviewEntity.setProduct(productEntity);

				Date date = new Date();
				reviewEntity.setDate(new Timestamp(date.getTime()));

				ReviewId reviewId = new ReviewId();
				reviewId.setProductId(productEntity.getId());
				reviewId.setUserId(userEntity.getId());

				reviewEntity.setReviewId(reviewId);
			}

			if (reviewEntity != null) {
				reviewEntity = reviewRepository.save(reviewEntity);
				return reviewConverter.toDTO(reviewEntity);
			}

		}

		return null;
	}

	@Override
	public void delete(ReviewDTO reviewDTO) {
		ProductEntity productEntity = productRepository.findOneByCode(reviewDTO.getProductCode());
		UserEntity userEntity = userRepository.findOneByUsername(reviewDTO.getUsername());
		ReviewEntity reviewEntity = reviewRepository.findOneByProductAndUser(productEntity, userEntity);
		reviewRepository.delete(reviewEntity);
	}

	@Override
	public ReviewDTO findOneByProductCodeAndUsername(String productCode, String username) {
		ProductEntity productEntity = productRepository.findOneByCode(productCode);
		UserEntity userEntity = userRepository.findOneByUsername(username);

		if (productEntity != null && userEntity != null) {
			ReviewEntity reviewEntity = reviewRepository.findOneByProductAndUser(productEntity, userEntity);
			if (reviewEntity != null) {
				return reviewConverter.toDTO(reviewEntity);
			}
		}

		return null;
	}

}
