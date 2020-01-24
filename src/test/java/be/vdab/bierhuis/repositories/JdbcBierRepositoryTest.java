package be.vdab.bierhuis.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.bierhuis.domain.Bier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcBierRepository.class)
@Sql("/insertBieren.sql")
class JdbcBierRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BIEREN = "bieren";
    private final JdbcBierRepository repository;

    public JdbcBierRepositoryTest(JdbcBierRepository repository) {
        this.repository = repository;
    }

    @Test
    void findAantalBieren() {
        assertThat(repository.findAantalBieren()).isEqualTo(super.countRowsInTable(BIEREN));
    }

    @Test
    void findAll() {
        assertThat(repository.findAll())
                .hasSize(super.countRowsInTable(BIEREN))
                .extracting(bier -> bier.getId())
                .isSorted();
    }

    private long idVanTestBier() {
        return super.jdbcTemplate.queryForObject("select id from bieren where naam = 'test'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestBier()).get().getNaam()).isEqualTo("test");
    }

    @Test
    void findByOnbestaandeIdVindtGeenBier() {
        assertThat(repository.findById(-1)).isEmpty();
    }

    //    private long idVanBrouwer() {
//        return super.jdbcTemplate.queryForObject("select id from brouwers where naam = 'test'", Long.class);
//    }
    @Test
    void findByBrouwer() {
        assertThat(repository.findByBrouwer(1))
                .hasSize(super.countRowsInTableWhere(BIEREN, "brouwerid= 1"))
                .extracting(bier -> bier.getNaam())
                .isSortedAccordingTo((o1, o2) -> o1.compareToIgnoreCase(o2));
    }
    @Test
    void update() {
        long id = idVanTestBier();
        Bier bier = new Bier(id, "test4", 1, 1, BigDecimal.ONE, BigDecimal.TEN, 0);
        repository.update(bier);
        assertThat(super.jdbcTemplate.queryForObject("select besteld from bieren where id= ?", Long.class, id)).isOne();
    }
}