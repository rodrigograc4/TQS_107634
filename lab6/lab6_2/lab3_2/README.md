# Lab6_2 Technical Dept - Cars

O meu Cars não contém nenhum Reliability Issue, tendo nota A nesse aspecto. No entanto na Security tem um Issue, de nivel High Impact, e por isso teve a nota D.

Esse Issue de elevado risco, esta presente no ficheiro CarController.java. No seguinte excerto:
' @PostMapping("/cars") public ResponseEntity<Car> createCar(@RequestBody Car oneCar) {'
Replace this persistent entity with a simple POJO or DTO object.

Isto é um Issue porque ' Attackers could forge malicious HTTP requests that will alter unexpected properties of persistent objects. This can lead to unauthorized modifications of the entity’s state. This is known as a mass assignment attack.

Depending on the affected objects and properties, the consequences can vary. '

E poderia ser resolvido fazendo o seguinte:

Introdução de um DTO (Data Transfer Object):
Em vez de receber diretamente a entidade persistente "Wish", o método saveForLater agora recebe um objeto do tipo "WishDTO". Isso ajuda a garantir que apenas as informações necessárias e seguras sejam transferidas.

Criação de um Wish persistente a partir do WishDTO:
Em vez de salvar diretamente o objeto "WishDTO", agora é criado um novo objeto "Wish" persistente.
As propriedades do objeto "WishDTO" são atribuídas às propriedades correspondentes do objeto "Wish" persistente.

Obtenção do cliente a partir do ID:
O método getClientById é chamado para obter o objeto "Client" correto com base no ID fornecido no objeto "WishDTO".

Persistência do objeto Wish persistente:
O objeto "Wish" persistente é então salvo no banco de dados.