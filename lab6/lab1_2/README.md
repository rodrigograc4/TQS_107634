# Lab6_1 Local Analysis - Euromillions

O meu Euromillions não contém nenhum Security nem Reliability Issue, tendo assim nota A em ambos.


Em Maintainability obti tambem a nota A com 0 High Impact ISsues, 5 Medium Impact e 17 Low Impact.


Os Medium Impact Issues, foram 3 deles no ' src/main/java/tqs/DemoMain.java ', tendo eles a solução de ' Invoke method(s) only conditionally '.
Estes 3 issues são um problema porque ' Some method calls can effectively be "no-ops", meaning that the invoked method does nothing, based on the application’s configuration (eg: debug logs in production). However, even if the method effectively does nothing, its arguments may still need to evaluated before the method is called. '

Os outros 2 corrigiam-se fazendo o seguinte: ' Refactor the code in order to not assign to the loop counter from within the loop body ', e estavam no fichiero ' src/main/java/tqs/euromillions/Dip.java '
Era um problema porque : ' A for loop termination condition should test the loop counter against an invariant value that does not change during the execution of the loop. Invariant termination conditions make the program logic easier to understand and maintain.
This rule tracks three types of non-invariant termination conditions:
When the loop counters are updated in the body of the for loop
When the termination condition depends on a method call
When the termination condition depends on an object property since such properties could change during the execution of the loop. '

