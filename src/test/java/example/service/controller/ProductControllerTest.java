package example.service.controller;

import example.service.controller.model.ProductResponse;
import example.service.data.model.Product;
import example.service.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductControllerTest {

  private final ProductService productService = mock(ProductService.class);
  private final ProductController controller = new ProductController(productService);

  @Test
  void getProductReturnsProductWhenFound() {
    final Product product = new Product();
    product.setId("product-1");
    product.setName("Test Product");
    product.setDescription("A test product");
    product.setActive(true);
    product.setStartTime(1609459200L);
    product.setEndTime(1640995200L);

    when(productService.getProduct("product-1")).thenReturn(Mono.just(product));

    StepVerifier.create(controller.getProduct("product-1"))
            .assertNext(response -> {
              assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

              final ProductResponse body = response.getBody();
              assertThat(body).isNotNull();
              assertThat(body.getId()).isEqualTo("product-1");
              assertThat(body.getName()).isEqualTo("Test Product");
              assertThat(body.getDescription()).isEqualTo("A test product");
              assertThat(body.isActive()).isTrue();
              assertThat(body.getStartDate()).isEqualTo("2021-01-01T00:00Z");
              assertThat(body.getEndDate()).isEqualTo("2022-01-01T00:00Z");
            })
            .verifyComplete();
  }

  @Test
  void getProductReturnsNotFoundWhenMissing() {
    when(productService.getProduct("missing-product")).thenReturn(Mono.empty());

    StepVerifier.create(controller.getProduct("missing-product"))
            .assertNext(response -> {
              assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
              assertThat(response.getBody()).isNull();
            })
            .verifyComplete();
  }
}
