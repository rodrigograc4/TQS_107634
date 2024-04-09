package backend;

import backend.entity.Tickets;
import backend.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TicketsRepository ticketsRepository;

    @Autowired
    public DataInitializer(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    @Override
    public void run(String... args) {
        var lisboa = "Lisboa";
        var porto = "Porto";
        var faro = "Faro";
        var coimbra = "Coimbra";
        var tomar = "Tomar";
        var braga = "Braga";
        var transportesRapidosLusitanos = "Transportes Rápidos Lusitanos";
        var expressoDoAtlantico = "Expresso do Atlântico";
        var transportesSolNascente = "Transportes Sol Nascente";
        var transportesDoTejo = "Transportes do Tejo";
        var lusitanoExpress = "Lusitano Express";
        var portugalConnect = "Portugal Connect";
        var redeExpressaIberica = "Rede Expressa Ibérica";
        var viacaoPortuguesaDeConexao = "Viação Portuguesa de Conexão";

        Tickets ticket10 = new Tickets(lisboa, tomar, transportesRapidosLusitanos, "2024-04-07 08:00", "2024-04-07 10:00", 10.00);
        Tickets ticket11 = new Tickets(lisboa, porto, expressoDoAtlantico, "2024-04-07 12:00", "2024-04-07 14:00", 15.00);
        Tickets ticket12 = new Tickets(lisboa, faro, expressoDoAtlantico, "2024-04-07 16:00", "2024-04-07 20:00", 30.00);
        Tickets ticket13 = new Tickets(lisboa, coimbra, portugalConnect, "2024-04-07 22:00", "2024-04-08 00:00", 10.00);

        Tickets ticket20 = new Tickets(coimbra, porto, viacaoPortuguesaDeConexao, "2024-04-08  14:00", "2024-04-08 15:30", 8.00);
        Tickets ticket21 = new Tickets(coimbra, tomar, transportesDoTejo, "2024-04-08  16:00", "2024-04-08 17:30", 5.00);
        Tickets ticket22 = new Tickets(coimbra, faro, expressoDoAtlantico, "2024-04-08  18:00", "2024-04-08 20:00", 20.00);
        Tickets ticket23 = new Tickets(coimbra, lisboa, redeExpressaIberica, "2024-04-08  22:00", "2024-04-08 23:30", 10.00);

        Tickets ticket30 = new Tickets(faro, lisboa, lusitanoExpress, "2024-04-09 10:00", "2024-04-09 14:00", 20.00);
        Tickets ticket31 = new Tickets(faro, porto, transportesDoTejo, "2024-04-09 16:00", "2024-04-09 20:00", 25.00);
        Tickets ticket32 = new Tickets(faro, tomar, lusitanoExpress, "2024-04-09 22:00", "2024-04-10 00:00", 15.00);
        Tickets ticket33 = new Tickets(faro, coimbra, transportesDoTejo, "2024-04-10 02:00", "2024-04-10 04:00", 20.00);

        Tickets ticket40 = new Tickets(braga, lisboa, lusitanoExpress, "2024-04-10 08:00", "2024-04-10 12:00", 18.00);
        Tickets ticket41 = new Tickets(braga, tomar, expressoDoAtlantico, "2024-04-10 14:00", "2024-04-10 16:00", 10.00);
        Tickets ticket42 = new Tickets(braga, porto, redeExpressaIberica, "2024-04-10 18:00", "2024-04-10 20:00", 5.00);
        Tickets ticket43 = new Tickets(braga, coimbra, portugalConnect, "2024-04-10 22:00", "2024-04-11 00:00", 15.00); 
    
        ticketsRepository.save(ticket10);
        ticketsRepository.save(ticket11);
        ticketsRepository.save(ticket12);
        ticketsRepository.save(ticket13);

        ticketsRepository.save(ticket20);
        ticketsRepository.save(ticket21);
        ticketsRepository.save(ticket22);
        ticketsRepository.save(ticket23);

        ticketsRepository.save(ticket30);
        ticketsRepository.save(ticket31);
        ticketsRepository.save(ticket32);
        ticketsRepository.save(ticket33);

        ticketsRepository.save(ticket40);
        ticketsRepository.save(ticket41);
        ticketsRepository.save(ticket42);
        ticketsRepository.save(ticket43);

        Tickets ticket101 = new Tickets(lisboa, tomar, expressoDoAtlantico, "2024-04-12 09:00", "2024-04-12 11:00", 11.50);
        Tickets ticket111 = new Tickets(lisboa, porto, transportesRapidosLusitanos, "2024-04-12 13:00", "2024-04-12 15:00", 16.50);
        Tickets ticket121 = new Tickets(lisboa, faro, transportesSolNascente, "2024-04-12 17:00", "2024-04-12 21:00", 31.50);
        Tickets ticket131 = new Tickets(lisboa, coimbra, lusitanoExpress, "2024-04-12 22:00", "2024-04-13 00:00", 11.50);

        Tickets ticket201 = new Tickets(coimbra, porto, transportesSolNascente, "2024-04-13 11:00", "2024-04-13 12:30", 9.00);
        Tickets ticket211 = new Tickets(coimbra, tomar, redeExpressaIberica, "2024-04-13 13:00", "2024-04-13 14:30", 6.00);
        Tickets ticket221 = new Tickets(coimbra, faro, expressoDoAtlantico, "2024-04-13 15:00", "2024-04-13 17:00", 21.50);
        Tickets ticket231 = new Tickets(coimbra, lisboa, lusitanoExpress, "2024-04-13 19:00", "2024-04-13 20:30", 12.00);

        Tickets ticket301 = new Tickets(faro, lisboa, portugalConnect, "2024-04-14 09:00", "2024-04-14 13:00", 21.00);
        Tickets ticket311 = new Tickets(faro, porto, transportesDoTejo, "2024-04-14 15:00", "2024-04-14 19:00", 26.00);
        Tickets ticket321 = new Tickets(faro, tomar, viacaoPortuguesaDeConexao, "2024-04-14 21:00", "2024-04-15 00:00", 16.00);
        Tickets ticket331 = new Tickets(faro, coimbra, expressoDoAtlantico, "2024-04-15 01:00", "2024-04-15 03:00", 21.00);

        Tickets ticket401 = new Tickets(braga, lisboa, portugalConnect, "2024-04-15 08:00", "2024-04-15 12:00", 19.00);
        Tickets ticket411 = new Tickets(braga, tomar, portugalConnect, "2024-04-15 14:00", "2024-04-15 16:00", 11.00);
        Tickets ticket421 = new Tickets(braga, porto, transportesRapidosLusitanos, "2024-04-15 18:00", "2024-04-15 20:00", 6.50);
        Tickets ticket431 = new Tickets(braga, coimbra, expressoDoAtlantico, "2024-04-15 22:00", "2024-04-16 00:00", 16.00); 

        ticketsRepository.save(ticket101);
        ticketsRepository.save(ticket111);
        ticketsRepository.save(ticket121);
        ticketsRepository.save(ticket131);

        ticketsRepository.save(ticket201);
        ticketsRepository.save(ticket211);
        ticketsRepository.save(ticket221);
        ticketsRepository.save(ticket231);

        ticketsRepository.save(ticket301);
        ticketsRepository.save(ticket311);
        ticketsRepository.save(ticket321);
        ticketsRepository.save(ticket331);

        ticketsRepository.save(ticket401);
        ticketsRepository.save(ticket411);
        ticketsRepository.save(ticket421);
        ticketsRepository.save(ticket431);

        Tickets ticket102 = new Tickets(lisboa, tomar, expressoDoAtlantico, "2024-04-13 08:30", "2024-04-13 10:30", 12.00);
        Tickets ticket103 = new Tickets(lisboa, tomar, transportesSolNascente, "2024-04-14 11:30", "2024-04-14 13:30", 12.50);

        Tickets ticket112 = new Tickets(lisboa, porto, expressoDoAtlantico, "2024-04-13 12:30", "2024-04-13 14:30", 17.00);
        Tickets ticket113 = new Tickets(lisboa, porto, lusitanoExpress, "2024-04-14 15:30", "2024-04-14 17:30", 17.50);

        Tickets ticket122 = new Tickets(lisboa, faro, viacaoPortuguesaDeConexao, "2024-04-13 16:30", "2024-04-13 20:30", 32.00);
        Tickets ticket123 = new Tickets(lisboa, faro, portugalConnect, "2024-04-14 21:30", "2024-04-15 01:30", 32.50);

        Tickets ticket132 = new Tickets(lisboa, coimbra, expressoDoAtlantico, "2024-04-13 22:30", "2024-04-14 00:30", 12.50);
        Tickets ticket133 = new Tickets(lisboa, coimbra, redeExpressaIberica, "2024-04-14 01:30", "2024-04-14 03:30", 13.00);

        Tickets ticket202 = new Tickets(coimbra, porto, transportesDoTejo, "2024-04-14 10:30", "2024-04-14 12:00", 9.50);
        Tickets ticket203 = new Tickets(coimbra, porto, redeExpressaIberica, "2024-04-15 13:00", "2024-04-15 14:30", 10.00);

        Tickets ticket212 = new Tickets(coimbra, tomar, lusitanoExpress, "2024-04-14 14:30", "2024-04-14 16:00", 6.50);
        Tickets ticket213 = new Tickets(coimbra, tomar, transportesRapidosLusitanos, "2024-04-15 17:00", "2024-04-15 18:30", 7.00);

        Tickets ticket222 = new Tickets(coimbra, faro, transportesRapidosLusitanos, "2024-04-14 18:30", "2024-04-14 20:30", 22.00);
        Tickets ticket223 = new Tickets(coimbra, faro, lusitanoExpress, "2024-04-15 21:30", "2024-04-15 23:30", 22.50);

        Tickets ticket232 = new Tickets(coimbra, lisboa, transportesDoTejo, "2024-04-14 22:30", "2024-04-15 00:00", 13.50);
        Tickets ticket233 = new Tickets(coimbra, lisboa, expressoDoAtlantico, "2024-04-15 01:00", "2024-04-15 02:30", 14.00);

        Tickets ticket302 = new Tickets(faro, lisboa, redeExpressaIberica, "2024-04-15 09:00", "2024-04-15 13:00", 22.50);
        Tickets ticket303 = new Tickets(faro, lisboa, expressoDoAtlantico, "2024-04-16 14:00", "2024-04-16 18:00", 23.00);

        Tickets ticket312 = new Tickets(faro, porto, lusitanoExpress, "2024-04-15 15:00", "2024-04-15 19:00", 27.00);
        Tickets ticket313 = new Tickets(faro, porto, expressoDoAtlantico, "2024-04-16 20:00", "2024-04-17 00:00", 27.50);

        Tickets ticket322 = new Tickets(faro, tomar, viacaoPortuguesaDeConexao, "2024-04-15 21:00", "2024-04-16 00:00", 17.00);
        Tickets ticket323 = new Tickets(faro, tomar, lusitanoExpress, "2024-04-16 01:00", "2024-04-16 03:00", 17.50);

        Tickets ticket332 = new Tickets(faro, coimbra, redeExpressaIberica, "2024-04-16 04:00", "2024-04-16 06:00", 22.50);
        Tickets ticket333 = new Tickets(faro, coimbra, expressoDoAtlantico, "2024-04-16 07:00", "2024-04-16 09:00", 23.00);

        Tickets ticket402 = new Tickets(braga, lisboa, redeExpressaIberica, "2024-04-16 08:00", "2024-04-16 12:00", 20.00);
        Tickets ticket403 = new Tickets(braga, lisboa, transportesDoTejo, "2024-04-17 13:00", "2024-04-17 17:00", 20.50);

        Tickets ticket412 = new Tickets(braga, tomar, redeExpressaIberica, "2024-04-16 14:00", "2024-04-16 16:00", 12.00);
        Tickets ticket413 = new Tickets(braga, tomar, transportesDoTejo, "2024-04-17 17:00", "2024-04-17 19:00", 12.50);

        Tickets ticket422 = new Tickets(braga, porto, transportesSolNascente, "2024-04-16 18:00", "2024-04-16 20:00", 7.00);
        Tickets ticket423 = new Tickets(braga, porto, transportesSolNascente, "2024-04-17 21:00", "2024-04-17 23:00", 7.50);

        Tickets ticket432 = new Tickets(braga, coimbra, portugalConnect, "2024-04-17 00:00", "2024-04-17 02:00", 17.00);
        Tickets ticket433 = new Tickets(braga, coimbra, redeExpressaIberica, "2024-04-17 03:00", "2024-04-17 05:00", 17.50); 

        ticketsRepository.save(ticket102);
        ticketsRepository.save(ticket103);

        ticketsRepository.save(ticket112);
        ticketsRepository.save(ticket113);

        ticketsRepository.save(ticket122);
        ticketsRepository.save(ticket123);

        ticketsRepository.save(ticket132);
        ticketsRepository.save(ticket133);

        ticketsRepository.save(ticket202);
        ticketsRepository.save(ticket203);

        ticketsRepository.save(ticket212);
        ticketsRepository.save(ticket213);

        ticketsRepository.save(ticket222);
        ticketsRepository.save(ticket223);

        ticketsRepository.save(ticket232);
        ticketsRepository.save(ticket233);

        ticketsRepository.save(ticket302);
        ticketsRepository.save(ticket303);

        ticketsRepository.save(ticket312);
        ticketsRepository.save(ticket313);

        ticketsRepository.save(ticket322);
        ticketsRepository.save(ticket323);

        ticketsRepository.save(ticket332);
        ticketsRepository.save(ticket333);

        ticketsRepository.save(ticket402);
        ticketsRepository.save(ticket403);

        ticketsRepository.save(ticket412);
        ticketsRepository.save(ticket413);

        ticketsRepository.save(ticket422);
        ticketsRepository.save(ticket423);

        ticketsRepository.save(ticket432);
        ticketsRepository.save(ticket433);

    }
}