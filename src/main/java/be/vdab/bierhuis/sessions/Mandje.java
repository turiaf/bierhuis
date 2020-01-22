package be.vdab.bierhuis.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<Long, Integer> bieren = new HashMap<>();
    private BigDecimal totaal = BigDecimal.ZERO;

    public void  add(long id, int aantal) {
        if(bieren.containsKey(id)) {
            bieren.put(id, bieren.get(id) + aantal);
        } else {
            bieren.put(id, aantal);
        }
    }

    public Map<Long, Integer> getBieren() {
        return bieren;
    }
    public boolean isGevuld() {
        return !bieren.isEmpty();
    }
    public void addTotaal(BigDecimal prijs) {
        totaal = totaal.add(prijs);
    }

    public BigDecimal getTotaal() {
        return totaal;
    }
    public void delete() {
        bieren.clear();
        totaal = BigDecimal.ZERO;
    }
}
