package sample.cafekiosk.spring.api.service.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductNumberFactory productNumberFactory;

	@Transactional
	public ProductResponse createProduct(ProductCreateServiceRequest request) {
		String latestProductNumber = productRepository.findLatestProductNumber();

		Product product = request.toEntity(productNumberFactory.createNextProductNumber(latestProductNumber));
		Product savedProduct = productRepository.save(product);

		return ProductResponse.of(savedProduct);
	}

	public List<ProductResponse> getSellingProducts() {
		List<Product> products = productRepository.findAllBySellingStatusIn(ProductResponse.forDisplay());

		return products.stream().map(ProductResponse::of).toList();
	}

}
