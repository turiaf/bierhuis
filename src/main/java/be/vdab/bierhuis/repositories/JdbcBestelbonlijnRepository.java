package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbonlijn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
class JdbcBestelbonlijnRepository implements BestelbonlijnRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public JdbcBestelbonlijnRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template);
        insert.withTableName("bestelbonlijnen");
    }

    @Override
    public void create(Bestelbonlijn bestelbonlijn) {
        Map<String, Object> kolomWaarden = new HashMap<>();
        kolomWaarden.put("bestelbonid", bestelbonlijn.getBestelbonid());
        kolomWaarden.put("bierid", bestelbonlijn.getBierid());
        kolomWaarden.put("aantal", bestelbonlijn.getAantal());
        kolomWaarden.put("prijs", bestelbonlijn.getPrijs());
        insert.execute(kolomWaarden);
    }
}
