package be.vdab.bierhuis.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BierForm {
    @NotNull
    @Positive
    private final Long id;
    @NotNull
    @Positive
    @Min(2)
    private final Integer aantal;

    public BierForm(Long id, Integer aantal) {
        this.id = id;
        this.aantal = aantal;
    }

    public Long getId() {
        return id;
    }

    public Integer getAantal() {
        return aantal;
    }
}
