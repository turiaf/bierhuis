package be.vdab.bierhuis.forms;

import be.vdab.bierhuis.domain.Bier;

import java.math.BigDecimal;

public class MandjeTable {
    private final Bier bier;
    private final int aantal;
    private final BigDecimal teBetalen;

    public MandjeTable(Bier bier, int aantal, BigDecimal teBetalen) {
        this.bier = bier;
        this.aantal = aantal;
        this.teBetalen = teBetalen;
    }

    public Bier getBier() {
        return bier;
    }

    public int getAantal() {
        return aantal;
    }

    public BigDecimal getTeBetalen() {
        return teBetalen;
    }
}
