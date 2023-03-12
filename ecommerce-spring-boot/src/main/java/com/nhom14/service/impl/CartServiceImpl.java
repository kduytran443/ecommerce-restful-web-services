package com.nhom14.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.CartConverter;
import com.nhom14.dto.CartDTO;
import com.nhom14.entity.CartEntity;
import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.UserEntity;
import com.nhom14.entity.id.CartId;
import com.nhom14.repository.CartRepository;
import com.nhom14.repository.ProductRepository;
import com.nhom14.repository.UserRepository;
import com.nhom14.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartConverter cartConverter;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<CartDTO> findAllByUserId(Long userId) {
		UserEntity userEntity = userRepository.findOne(userId);

		List<CartEntity> cartEntities = cartRepository.findAllByUser(userEntity);

		return cartConverter.toDTOList(cartEntities);
	}

	@Override
	public CartDTO findOneByUserIdAndProductCode(CartDTO cartDTO) {
		UserEntity userEntity = userRepository.findOne(cartDTO.getUserId());
		ProductEntity productEntity = productRepository.findOne(cartDTO.getProductId());

		CartEntity cartEntity = cartRepository.findOneByUserAndProduct(userEntity, productEntity);
		if (cartEntity != null) {
			return cartConverter.toDTO(cartEntity);
		}

		return null;
	}

	@Override
	public CartDTO save(CartDTO cartDTO) {
		UserEntity userEntity = userRepository.findOne(cartDTO.getUserId());
		ProductEntity productEntity = productRepository.findOne(cartDTO.getProductId());

		CartEntity cartEntity = cartRepository.findOneByUserAndProduct(userEntity, productEntity);

		if (cartEntity != null) { // edit
			cartEntity = cartConverter.toEntity(cartDTO, cartEntity);
		} else {
			cartEntity = cartConverter.toEntity(cartDTO);
			cartEntity.setUser(userEntity);
			cartEntity.setProduct(productEntity);
			CartId cartId = new CartId();
			cartId.setProductId(productEntity.getId());
			cartId.setUserId(userEntity.getId());
			cartEntity.setCartId(cartId);
		}

		if (cartEntity != null) {
			cartEntity = cartRepository.save(cartEntity);
			return cartConverter.toDTO(cartEntity);
		}

		return null;
	}

	@Override
	public void delete(CartDTO cartDTO) {
		UserEntity userEntity = userRepository.findOne(cartDTO.getUserId());
		ProductEntity productEntity = productRepository.findOne(cartDTO.getProductId());

		CartEntity cartEntity = cartRepository.findOneByUserAndProduct(userEntity, productEntity);
		cartRepository.save(cartEntity);
	}

}
