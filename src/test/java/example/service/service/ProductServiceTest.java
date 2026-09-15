package example.service.service;

import example.service.data.ProductRepository;
import example.service.data.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository repo;

  private ProductService service;

  @BeforeEach
  void setUp() {
    service = new ProductService(repo);
  }

  @Test
  void getProductEmitsProductFoundByRepository() {
    Product product = new Product();
    product.setId("product-42");
    product.setName("Example product");
    when(repo.findOne("product-42")).thenReturn(Mono.just(product));

    StepVerifier.create(service.getProduct("product-42"))
        .expectNext(product)
        .verifyComplete();

    verify(repo).findOne("product-42");
    verifyNoMoreInteractions(repo);
  }

  @Test
  void getProductCompletesEmptyWhenProductIsMissing() {
    when(repo.findOne("missing-product")).thenReturn(Mono.empty());

    StepVerifier.create(service.getProduct("missing-product"))
        .verifyComplete();

    verify(repo).findOne("missing-product");
    verifyNoMoreInteractions(repo);
  }

  @Test
  void getProductPropagatesRepositoryError() {
    RuntimeException failure = new RuntimeException("Product lookup failed");
    when(repo.findOne("product-42")).thenReturn(Mono.error(failure));

    StepVerifier.create(service.getProduct("product-42"))
        .expectErrorMatches(error -> error == failure)
        .verify();

    verify(repo).findOne("product-42");
    verifyNoMoreInteractions(repo);
  }
}
