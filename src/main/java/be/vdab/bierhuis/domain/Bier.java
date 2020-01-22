package be.vdab.bierhuis.domain;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public class Bier {
    private final long id;
    private final String naam;
    private final long brouwerId;
    private final long soortId;
//    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private final BigDecimal alcohol;
//    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private final BigDecimal prijs;
    private final long bestelId;

    public Bier(long id, String naam, long brouwerId, long soortId, BigDecimal alcohol, BigDecimal prijs, long bestelId) {
        this.id = id;
        this.naam = naam;
        this.brouwerId = brouwerId;
        this.soortId = soortId;
        this.alcohol = alcohol;
        this.prijs = prijs;
        this.bestelId = bestelId;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public long getBrouwerId() {
        return brouwerId;
    }

    public long getSoortId() {
        return soortId;
    }

    public BigDecimal getAlcohol() {
        return alcohol;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public long getBestelId() {
        return bestelId;
    }
    public BigDecimal teBetalen(int aantal) {
        return prijs.multiply(BigDecimal.valueOf(aantal));
    }
}
