package be.vdab.bierhuis.domain;

import java.math.BigDecimal;

public class Bestelbonlijn {
    private final long bestelbonid;
    private final long bierid;
    private final int aantal;
    private final BigDecimal prijs;

    public Bestelbonlijn(long bestelbonid, long bierid, int aantal, BigDecimal prijs) {
        this.bestelbonid = bestelbonid;
        this.bierid = bierid;
        this.aantal = aantal;
        this.prijs = prijs;
    }

    public long getBestelbonid() {
        return bestelbonid;
    }

    public long getBierid() {
        return bierid;
    }

    public int getAantal() {
        return aantal;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }
}
