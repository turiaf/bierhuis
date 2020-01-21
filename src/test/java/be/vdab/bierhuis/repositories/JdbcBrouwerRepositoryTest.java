package be.vdab.bierhuis.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@JdbcTest
@Import(JdbcBrouwersRepository.class)
@Sql("/insertBrouwers.sql")
class JdbcBrouwerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BROUWERS = "brouwers";
    private final JdbcBrouwersRepository repository;

    public JdbcBrouwerRepositoryTest(JdbcBrouwersRepository repository) {
        this.repository = repository;
    }

    @Test
    void findAll() {
        assertThat(repository.findAll())
            .hasSize(super.countRowsInTable(BROUWERS))
            .extracting(brouwer -> brouwer.getNaam())
            .isSortedAccordingTo((o1, o2) -> o1.compareToIgnoreCase(o2));
    }
    private long idVanBrouwerTest() {
        return super.jdbcTemplate.queryForObject("select id from brouwers where naam = 'test'", Long.class);
    }
    @Test
    void findById() {
        long id = idVanBrouwerTest();
        assertThat(repository.findById(id).get().getNaam()).isEqualTo("test");
    }
    @Test
    void findByOnbestaandeIdVindtGeenBrouwer() {
        assertThat(repository.findById(-1)).isEmpty();
    }
}