package be.vdab.bierhuis.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class BierForm {
    @NotBlank
    @Positive
    private final long id;
    @NotBlank
    @Positive
    private final int aantal;

    public BierForm(long id, int aantal) {
        this.id = id;
        this.aantal = aantal;
    }

    public long getId() {
        return id;
    }

    public int getAantal() {
        return aantal;
    }
}
