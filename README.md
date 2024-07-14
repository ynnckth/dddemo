# DDDDemo
*A simplified example playground applying some DDD and Clean Architecture concepts*

## Some of the covered topics
- Entities and value objects (see e.g. domain.Client and domain.Amount)
- Use-cases (see e.g. GetClientsUseCase and ClientService)
- Ports and adapters (see adapter package)
- Arch unit tests which enforce defined clean architecture package structure and references (see ArchitectureTests)


**TODO**
- [x] Add a controller and reference unit test using Mock MVC
- [x] Add arch unit tests to verify reference violations
- [ ] Add an integration test (@SpringBootTest) https://www.baeldung.com/spring-boot-testing
- [ ] Try out auto mapping (from adapter to domain, from domain to presenter DTO)
- [ ] Let the ClientService implement a second UC (separate interface)

