# Lab6_3 Custom Quality-Gate - Cars

O meu Cars não contém nenhum Reliability Issue, tendo nota A nesse aspecto. No entanto na Security tem um Issue, de nivel High Impact, e por isso teve a nota D.

Esse Issue de elevado risco, esta presente no ficheiro CarController.java. No seguinte excerto:
' @PostMapping("/cars") public ResponseEntity<Car> createCar(@RequestBody Car oneCar) {'
Replace this persistent entity with a simple POJO or DTO object.
