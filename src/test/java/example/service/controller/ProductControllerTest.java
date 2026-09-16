package example.service.controller;

import example.service.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductControllerTest {

  @Test
  void getProductReturnsNotFoundWhenProductDoesNotExist() {
    ProductService productService = mock(ProductService.class);
    when(productService.getProduct("missing-product")).thenReturn(Mono.empty());
    ProductController controller = new ProductController(productService);

    StepVerifier.create(controller.getProduct("missing-product"))
            .assertNext(response -> {
              assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
              assertNull(response.getBody());
            })
            .verifyComplete();

    verify(productService).getProduct("missing-product");
  }
}
