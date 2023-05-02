package com.nhom14.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ProductConverter;
import com.nhom14.converter.ProductImageConverter;
import com.nhom14.dto.ProductDTO;
import com.nhom14.dto.ProductImageDTO;
import com.nhom14.entity.CategoryEntity;
import com.nhom14.entity.ConsignmentEntity;
import com.nhom14.entity.ManufacturerEntity;
import com.nhom14.entity.OrderDetailsEntity;
import com.nhom14.entity.OrderEntity;
import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.ProductImageEntity;
import com.nhom14.entity.UserEntity;
import com.nhom14.repository.CategoryRepository;
import com.nhom14.repository.ManufacturerRepository;
import com.nhom14.repository.OrderDetailsRepository;
import com.nhom14.repository.OrderRepository;
import com.nhom14.repository.ProductRepository;
import com.nhom14.repository.UserRepository;
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
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

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
			
			/*
			List<ProductImageDTO> productImageDTOs = productDTO.getProductImages();

			List<ProductImageEntity> productImageEntities = productImageConverter.toEntityList(productImageDTOs);

			productEntity.setProductImages(productImageEntities);*/
		} else {
			productEntity = productConverter.toEntity(productDTO);

			CategoryEntity categoryEntity = categoryRepository.findOneByCode(productDTO.getCategoryCode());

			productEntity.setCategory(categoryEntity);

			if (productDTO.getManufacturer() != null) {
				ManufacturerEntity manufacturerEntity = manufacturerRepository
						.findOne(productDTO.getManufacturer().getId());
				productEntity.setManufacturer(manufacturerEntity);
			}
			productEntity.setStatus(1);
			if (productDTO.getProductImages() != null) {
				List<ProductImageDTO> productImageDTOs = productDTO.getProductImages();

				List<ProductImageEntity> productImageEntities = productImageConverter.toEntityList(productImageDTOs);

				productEntity.setProductImages(productImageEntities);
			}
		}

		if (productEntity != null) {
			productEntity = productRepository.save(productEntity);
			return productConverter.toDTO(productEntity);
		}

		return null;
	}

	@Override
	public void delete(ProductDTO productDTO) {
		ProductEntity productEntity = null;
		if (productDTO.getId() != null) {
			productEntity = productRepository.findOne(productDTO.getId());
			productEntity.setStatus(0);
			productRepository.save(productEntity);
		}
	}

	@Override
	public ProductDTO findOneByCode(String code) {
		ProductEntity productEntity = productRepository.findOneByCode(code);
		ProductDTO productDTO = productConverter.toDTO(productEntity);
		
		Integer stock = 0;
		List<ConsignmentEntity> consignmentEntities = productEntity.getConsigments();
		for (ConsignmentEntity consignmentEntity : consignmentEntities) {
			stock += consignmentEntity.getQuantity();
		}
		
		List<OrderDetailsEntity> orderDetailsEntities = orderDetailsRepository.findAllByProduct(productEntity);
		for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntities) {
			stock -= orderDetailsEntity.getQuantity();
		}
		
		productDTO.setRemainingAmount(stock);
		
		return productDTO;
	}

	@Override
	public List<ProductDTO> findAll() {
		return productConverter.toDTOList(productRepository.findAll());
	}

	@Override
	public List<ProductDTO> searchByName(String name) {
		List<ProductEntity> list = productRepository.findByNameContainingIgnoreCase(name);

		return productConverter.toDTOList(list);
	}

	@Override
	public List<ProductDTO> getAllProductDetails() {

		List<ProductEntity> productEntities = productRepository.findByOrderByIdDesc();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (ProductEntity productEntity : productEntities) {
			int stock = 0;
			Long revenue = 0L;
			Long like = 0L;

			List<UserEntity> entities = productEntity.getUsers();
			if (entities != null)
				like = entities.size() + 0L;

			List<ConsignmentEntity> consignmentEntities = productEntity.getConsigments();
			for (ConsignmentEntity consignmentEntity : consignmentEntities) {
				stock += consignmentEntity.getQuantity();
				revenue -= consignmentEntity.getPrice() * consignmentEntity.getQuantity();
			}

			/*
			List<OrderDetailsEntity> orderDetailsEntities = orderDetailsRepository.findAllByProduct(productEntity);
			for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntities) {
				revenue += orderDetailsEntity.getQuantity() * (orderDetailsEntity.getPrice()
						- orderDetailsEntity.getPrice() * (orderDetailsEntity.getDiscountPercent())/100);
				stock -= orderDetailsEntity.getQuantity();
			}
			*/
			
			System.out.println("Before "+revenue);
			List<OrderDetailsEntity> orderDetailsEntities = orderDetailsRepository.findAllByProduct(productEntity);
			for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntities) {
				stock -= orderDetailsEntity.getQuantity();
				revenue += orderDetailsEntity.getQuantity() * (orderDetailsEntity.getPrice()
						- orderDetailsEntity.getPrice() * (orderDetailsEntity.getDiscountPercent())/100);
				System.out.println();
				System.out.println("After "+revenue);
			}
			
			/*
			Map<Long, List<OrderDetailsEntity>> orderMap =
					orderDetailsEntities.stream().collect(Collectors.groupingBy(entity -> entity.getOrder().getId()));
			Set<Long> keys = orderMap.keySet();
			for (Long key : keys) {
				OrderEntity entity = orderRepository.findOne(key);
				if(entity.getPayment() != null) {
					revenue += entity.getPayment().getTotalPrice();
				}
			}
			*/
			
			
			
			ProductDTO productDTO = productConverter.toDTO(productEntity);
			productDTO.setStock(stock);
			productDTO.setRevenue(revenue);
			productDTO.setFavorited(like);

			productDTOs.add(productDTO);
		}

		return productDTOs;
	}

	@Override
	public List<ProductDTO> getAllProductDetailsByName(String name) {
		List<ProductEntity> productEntities = productRepository.findByNameContainingIgnoreCase(name);
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (ProductEntity productEntity : productEntities) {
			int stock = 0;
			Long revenue = 0L;
			Long like = 0L;

			List<UserEntity> entities = productEntity.getUsers();
			if (entities != null)
				like = entities.size() + 0L;

			List<ConsignmentEntity> consignmentEntities = productEntity.getConsigments();
			for (ConsignmentEntity consignmentEntity : consignmentEntities) {
				stock += consignmentEntity.getQuantity();
				revenue -= consignmentEntity.getPrice() * consignmentEntity.getQuantity();
			}
			//a
			List<OrderDetailsEntity> orderDetailsEntities = orderDetailsRepository.findAllByProduct(productEntity);
			for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntities) {
				revenue += orderDetailsEntity.getQuantity() * (orderDetailsEntity.getPrice()
						- orderDetailsEntity.getPrice() * orderDetailsEntity.getDiscountPercent());
				stock -= orderDetailsEntity.getQuantity();
			}

			ProductDTO productDTO = productConverter.toDTO(productEntity);
			productDTO.setStock(stock);
			productDTO.setRevenue(revenue);
			productDTO.setFavorited(like);

			productDTOs.add(productDTO);
		}

		return productDTOs;
	}

	@Override
	public List<ProductDTO> getAllProductFavoritedByUser(Long userId) {
		UserEntity userEntity = userRepository.findOne(userId);

		List<ProductEntity> productEntities = userEntity.getFavoritedProducts();

		return productConverter.toDTOList(productEntities);
	}

	@Override
	public boolean checkFavorited(String productCode, Long userId) {
		UserEntity userEntity = userRepository.findOne(userId);
		ProductEntity productEntity = productRepository.findOneByCode(productCode);

		if (userEntity.getFavoritedProducts() != null && userEntity.getFavoritedProducts().contains(productEntity)) {

			return true;
		}

		return false;
	}

	@Override
	public void favorite(String productCode, Long userId) {
		UserEntity userEntity = userRepository.findOne(userId);
		ProductEntity productEntity = productRepository.findOneByCode(productCode);

		List<ProductEntity> productEntities = userEntity.getFavoritedProducts();
		List<ProductEntity> filterList = productEntities.stream().filter(entity -> entity.getCode().toLowerCase().equals(productCode.toLowerCase())).collect(Collectors.toList());
		
		if(filterList != null && filterList.size() != 0) {
			for (ProductEntity entity : filterList) {
				productEntities.remove(entity);
			}
		}
		else {
			productEntities.add(productEntity);
		}
		userEntity.setFavoritedProducts(productEntities);
		userRepository.save(userEntity);
	}

}
