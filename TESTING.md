# Testing

Run the isolated ProductService unit tests with:

```sh
./gradlew test --tests example.service.service.ProductServiceTest --no-daemon
```

The repository is mocked, so these tests need no Redis server.

The empty-result case was added and passed from the remote IDE.
