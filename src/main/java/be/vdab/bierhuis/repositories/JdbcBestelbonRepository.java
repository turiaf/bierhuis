package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcBestelbonRepository implements BestelbonRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public JdbcBestelbonRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template);
        insert.usingGeneratedKeyColumns("id");
        insert.withTableName("bestelbonnen");
    }

    @Override
    public long create(Bestelbon bestelbon) {
        Map<String, Object> kolomWaarden = new HashMap<>();
        kolomWaarden.put("naam", bestelbon.getNaam());
        kolomWaarden.put("straat", bestelbon.getStraat());
        kolomWaarden.put("huisNr", bestelbon.getHuisNr());
        kolomWaarden.put("postcode", bestelbon.getPostcode());
        kolomWaarden.put("gemeente", bestelbon.getGemeente());
        Number id = insert.executeAndReturnKey(kolomWaarden);
        return id.longValue();
    }
}
