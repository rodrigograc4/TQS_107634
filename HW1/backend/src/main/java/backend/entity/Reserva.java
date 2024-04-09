package backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idticket;

    @NotNull
    @Size(min = 1, max = 255)
    private String nome;

    @NotNull
    @Email
    @Size(min = 1, max = 255)
    private String email;

    @NotNull
    @Pattern(regexp = "^\\d{4}([\\s\\-]?)\\d{4}\\1\\d{4}\\1\\d{4}$")
    private String numeroCartao;
    
    @NotNull
    @Pattern(regexp = "^(0[1-9]|1[0-2])/(\\d{2})$")
    private String validade;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    private Long cvv;

    @NotNull
    @Size(min = 1, max = 255)
    private String nomeCartao;

    public Reserva() {
    }

    public Reserva(Long idticket, String nome, String email, String numeroCartao, String validade, Long cvv, String nomeCartao) {
        this.idticket = idticket;
        this.nome = nome;
        this.email = email;
        this.numeroCartao = numeroCartao;
        this.validade = validade;
        this.cvv = cvv;
        this.nomeCartao = nomeCartao;
    }

    public Long getId() {
        return id;
    }

    public Long getIdticket() {
        return idticket;
    }

    public void setIdticket(Long idticket) {
        this.idticket = idticket;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public Long getCvv() {
        return cvv;
    }

    public void setCvv(Long cvv) {
        this.cvv = cvv;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    @Override
    public String toString() {
        return "[idticket=" + idticket + ", nome=" + nome + ", email=" + email + ", numeroCartao="
                + numeroCartao + ", validade=" + validade + ", cvv=" + cvv + ", nomeCartao=" + nomeCartao + "]";
    }

}
