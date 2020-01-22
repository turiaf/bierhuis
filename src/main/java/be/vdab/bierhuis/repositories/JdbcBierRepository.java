package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
class JdbcBierRepository implements BierRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public JdbcBierRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template);
        insert.usingGeneratedKeyColumns("id");
        insert.withTableName("bieren");
    }

    @Override
    public long findAantalBieren() {
        String sql = "select count(*) from bieren";
        return template.queryForObject(sql, Long.class);
    }

    private final RowMapper<Bier> bierMapper = (result, rowNum) ->
            new Bier(result.getLong("id"), result.getString("naam"), result.getLong("brouwerid"),
                    result.getLong("soortid") ,result.getBigDecimal("alcohol"),
                    result.getBigDecimal("prijs"), result.getLong("besteld"));
    @Override
    public List<Bier> findAll() {
        String sql = "select id, naam, brouwerid, soortid, alcohol, prijs, besteld from bieren";
        return template.query(sql, bierMapper);
    }

    @Override
    public Optional<Bier> findById(long id) {
        try {
            String sql ="select id, naam, brouwerid, soortid, alcohol, prijs, besteld from bieren where id = ?";
            return Optional.of(template.queryForObject(sql, bierMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Bier> findByBrouwer(long idBrouwer) {
        String sql ="select id, naam, brouwerid, soortid, alcohol, prijs, besteld from bieren where brouwerid = ? order by naam";
        return template.query(sql, bierMapper, idBrouwer);
    }

    @Override
    public void update(Bier bier) {
        String sql = "update bieren set besteld = besteld + 1 where id = ?";
        template.update(sql, bier.getId());
    }

}
