package sample.cafekiosk.docs.product;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.OBJECT;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sample.cafekiosk.docs.RestDocsSupport;
import sample.cafekiosk.spring.api.controller.product.ProductController;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.ProductService;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

public class ProductControllerDocsTest extends RestDocsSupport {

	private final ProductService productService = mock(ProductService.class);

	@Override
	protected Object initController() {
		return new ProductController(productService);
	}

	@Test
	@DisplayName("신규 상품을 등록하는 API ")
	void createProduct() throws Exception {
		ProductCreateRequest request = ProductCreateRequest.builder()
			.type(ProductType.HANDMADE)
			.sellingStatus(ProductSellingStatus.SELLING)
			.name("아메리카노")
			.price(4000)
			.build();

		given(productService.createProduct(any(ProductCreateServiceRequest.class)))
			.willReturn(ProductResponse.builder()
				.id(1L)
				.productNumber("001")
				.type(ProductType.HANDMADE)
				.sellingStatus(ProductSellingStatus.SELLING)
				.name("아메리카노")
				.price(4000)
				.build());

		// when && then
		mockMvc.perform(
				post("/api/v1/products/new")
					.content(objectMapper.writeValueAsString(request))
					.contentType(APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document("product-create",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("type").type(STRING)
						.optional()
						.description("상품 타입"),
					fieldWithPath("sellingStatus").type(STRING)
						.optional()
						.description("상품 판매상태"),
					fieldWithPath("name").type(STRING)
						.description("상품 이름"),
					fieldWithPath("price").type(NUMBER)
						.description("상품 가격")
				),
				responseFields(
					fieldWithPath("code").type(NUMBER)
						.description("코드"),
					fieldWithPath("status").type(STRING)
						.description("상태"),
					fieldWithPath("message").type(STRING)
						.description("메세지"),
					fieldWithPath("data").type(OBJECT)
						.description("응답 데이터"),
					fieldWithPath("data.id").type(NUMBER)
						.description("상품 아이디"),
					fieldWithPath("data.productNumber").type(STRING)
						.description("상품 번호"),
					fieldWithPath("data.type").type(STRING)
						.description("상품 타입"),
					fieldWithPath("data.sellingStatus").type(STRING)
						.description("상품 판매상태"),
					fieldWithPath("data.name").type(STRING)
						.description("상품 이름"),
					fieldWithPath("data.price").type(NUMBER)
						.description("상품 가격")
				)
			));
	}
}
