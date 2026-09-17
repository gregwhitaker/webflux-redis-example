package example.service.service;

import example.service.data.ProductRepository;
import example.service.data.model.Product;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for the ProductService.
 */
class ProductServiceTest {

  @Test
  void foundProductEmitsProductAndCompletes() {
    Product product = new Product();
    product.setId("found-product");
    ProductRepository repository = mock(ProductRepository.class);
    when(repository.findOne("found-product")).thenReturn(Mono.just(product));
    ProductService service = new ProductService(repository);

    StepVerifier.create(service.getProduct("found-product"))
        .expectNext(product)
        .verifyComplete();

    verify(repository).findOne("found-product");
  }

  @Test
  void missingProductCompletesWithoutAValue() {
    ProductRepository repository = mock(ProductRepository.class);
    when(repository.findOne("missing-product")).thenReturn(Mono.empty());
    ProductService service = new ProductService(repository);

    StepVerifier.create(service.getProduct("missing-product"))
        .verifyComplete();

    verify(repository).findOne("missing-product");
  }

  @Test
  void repositoryErrorPropagatesWithoutWrapping() {
    RuntimeException failure = new RuntimeException("Repository lookup failed");
    ProductRepository repository = mock(ProductRepository.class);
    when(repository.findOne("failed-product")).thenReturn(Mono.error(failure));
    ProductService service = new ProductService(repository);

    StepVerifier.create(service.getProduct("failed-product"))
        .expectErrorMatches(error -> error == failure)
        .verify();

    verify(repository).findOne("failed-product");
  }
}
