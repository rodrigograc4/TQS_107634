# Questions Lab 3.1 

## a) Identify a couple of examples that use AssertJ expressive methods chaining.
* In A_EmployeeRepoTest.java
```
   assertThat(found).isEqualTo(alex);
   assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

## b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).
* In B_EmployeeServiceTest.java
```
   @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    void whenSearchValidName_thenEmployeeShouldBeFound() {
        String name = "alex";
        Employee found = employeeService.getEmployeeByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }
```


## c) What is the difference between standard @Mock and @MockBean?
```
@Mock: Typically employed in unit testing scenarios using Mockito and JUnit frameworks. It creates a mock object for a specific class or interface.

@MockBean: Primarily utilized in integration testing within Spring Boot applications. This annotation is used to incorporate mock objects into the Spring application context, enabling the substitution of real beans with mock implementations for testing purposes.
```

## d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
```
"application-integrationtest.properties" é um arquivo de configuração para testes de integração do Spring Boot, fornecendo propriedades específicas do ambiente para personalizar o comportamento da aplicação durante esses testes. É utilizado quando a aplicação interage com componentes externos, como bancos de dados ou serviços da web, assegurando um comportamento consistente e previsível.
```

## e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 
```
As estratégias de teste C, D e E diferem em suas abordagens para avaliar APIs com Spring Boot.

- Estratégia C (Teste de Unidade): Isola a camada web, simulando a camada de serviço sem usar banco de dados.

- Estratégia D (Teste de Integração com MockMvc): Carrega o contexto completo da aplicação, usando MockMvc para simular solicitações HTTP e substitui o banco de dados real por um em memória.

- Estratégia E (Teste de Integração com TestRestTemplate): Inicia o servidor em uma porta aleatória e interage com ele como um cliente, substituindo o banco de dados real por um em memória.

As principais diferenças incluem o uso de bancos de dados (ausente no teste C), a utilização de @WebMvcTest (apenas no teste C) e a implementação específica de testes de integração com TestRestTemplate (somente no teste E) ou MockMvc (somente no teste D).
```