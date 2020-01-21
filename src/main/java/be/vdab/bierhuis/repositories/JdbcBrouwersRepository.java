package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Brouwers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class JdbcBrouwersRepository implements BrouwersRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public JdbcBrouwersRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template);
        insert.withTableName("brouwers");
        insert.usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Brouwers> brouwerMapper = (result, rowNum) ->
        new Brouwers(result.getLong("id"), result.getString("naam"),
                result.getString("straat"), result.getString("huisNr"),
                result.getInt("postcode"), result.getString("gemeente"),
                result.getLong("omzet"));
    @Override
    public List<Brouwers> findAll() {
        String sql = "select id, naam, straat, huisNr, postcode, gemeente, omzet from brouwers order by naam";
        return template.query(sql, brouwerMapper);
    }
}
