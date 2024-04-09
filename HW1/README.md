# HW1: Mid-term assignment READ ME

## Run the App

### SpringBoot / Backend

Há duas formas de se inicializar o backend, sendo elas:

1. No diretório `/backend`, digitar o comando
```bash
mvn spring-boot:run
```

2. Com a ajuda do VsCode fazer run do ficheiro `/Main.java` que se encontra no `backend/src/main/java/backend/`
```xml
Clicar no Botão do VsCode Run Java
```

### Html / Frontend


Há também duas formas de tornar o frontend acessivel:

1. No diretório `/frontend` com o comando:
```bash
python3 -m http.server
```
Após iniciar o servidor, o website vai estar disponível no url: `http://localhost:8000/html/index.html`

2. Mais uma vez pode-se auxiliar da ajuda do VsCode que com a `Extensão do LiveServer` torna o trabalho mais fácil
```xml
Abrir index.html com o LiveServer
```
Desta forma, o seu website vai ser iniciado no url: `http://localhost:5500/index.html`


## API Data Initializer

Criei um `DataInitializer.java` que quando a aplicação é inicializada este cria os Dados da API, neste contexto, os chamados Tickets.
Para o SpringBoot saber que tem de correr esse DataInitializer.java ao iniciar a aplicação foi utilizada a anotação `@Component`


## Selenium

No ficheiro `SeleniumTest.java` que esta no diretório `backend/src/test/java/backend/`, dependendo do metodo que inicializar o frontend, terá de mudar o `driver.get(url)` que está no primeiro ponto do ficheiro. O default é o `http://localhost:8000/html/index.html`, que é o primeiro metodo exemplificado no tópico acima.

## SonarQube

### Local Instance of SonarQube

Para iniciar o SonarQube é necessário a utilização do docker, para assim podermos correr uma instância local do SonarQube nele.
Pode-se iniciar essa instância após termos o docker preparado, com o seguinte código no terminal:
```bash
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
```

Após isso, podemos verificar no docker se o SonarQube está a funcionar corretamente, e o mesmo está disponicel no url: `http://localhost:9000`, se não existirem conflitos entre portas.

Seram pedidas credenciais para login, e as default são: `admin / admin`. No seu primeiro login terá de redefinir a sua password e decora-la para logins futuros.

### SonarQube Project

Para o SonarQube Project ser criado é necessário ter o plugin do Jacoco no seu `pom.xml`:
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.11</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Agora depois de adicionado o Jacoco já é possivel criar o seu projeto SonarQube no terminal no diretório `/backend`
```bash
mvn verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.projectKey=busway -Dsonar.login=admin -Dsonar.password=[YOUR PASSWORD]
```

## API Documentation

Adicionando a dependência do Swagger ao `pom.xml`, é nos possivel visualizar a documentação da API implementada no `backend`

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

Essa documentação estará depois disponível no url: `http://localhost:8080/swagger-ui.html`
