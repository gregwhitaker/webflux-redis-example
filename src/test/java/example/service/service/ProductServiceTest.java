package example.service.service;

import example.service.data.ProductRepository;
import example.service.data.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class ProductServiceTest {
  private ProductRepository repository;
  private ProductService service;

  @BeforeEach
  void setUp() {
    repository = mock(ProductRepository.class);
    service = new ProductService(repository);
  }

  @Test
  void getProductReturnsProductFoundByRepository() {
    Product product = new Product();
    product.setId("product-123");
    product.setName("Example product");
    when(repository.findOne("product-123")).thenReturn(Mono.just(product));

    StepVerifier.create(service.getProduct("product-123"))
        .expectNext(product)
        .verifyComplete();

    verify(repository).findOne("product-123");
    verifyNoMoreInteractions(repository);
  }

  @Test
  void getProductCompletesEmptyWhenProductIsMissing() {
    when(repository.findOne("missing-product")).thenReturn(Mono.empty());

    StepVerifier.create(service.getProduct("missing-product"))
        .verifyComplete();

    verify(repository).findOne("missing-product");
    verifyNoMoreInteractions(repository);
  }

  @Test
  void getProductPropagatesRepositoryError() {
    RuntimeException failure = new RuntimeException("Repository unavailable");
    when(repository.findOne("product-123")).thenReturn(Mono.error(failure));

    StepVerifier.create(service.getProduct("product-123"))
        .expectErrorMatches(error -> error == failure)
        .verify();

    verify(repository).findOne("product-123");
    verifyNoMoreInteractions(repository);
  }
}
