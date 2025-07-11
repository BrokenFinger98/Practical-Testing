package sample.cafekiosk.spring.api.controller.order.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import sample.cafekiosk.spring.api.service.order.request.OrderCreateServiceRequest;

@Builder
public record OrderCreateRequest(
	@NotEmpty(message = "상품 번호 리스트는 필수입니다.")
	List<String> productNumbers
) {
	public OrderCreateServiceRequest toServiceRequest() {
		return OrderCreateServiceRequest.builder()
			.productNumbers(productNumbers)
			.build();
	}
}
