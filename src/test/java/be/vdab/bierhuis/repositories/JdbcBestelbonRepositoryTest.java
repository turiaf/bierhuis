package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.jupiter.api.Assertions.*;
@JdbcTest
@Import(JdbcBestelbonRepository.class)
@Sql("/insertBestelbonnen.sql")
class JdbcBestelbonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BESTELBONNEN = "bestelbonnen";
    private final JdbcBestelbonRepository repository;

    public JdbcBestelbonRepositoryTest(JdbcBestelbonRepository repository) {
        this.repository = repository;
    }
    @Test
    void create() {
        long id = repository.create(
                new Bestelbon(0, "test2", "straatTest", "huisNrTest", 1, "gemeenteTest"));
        assertThat(id).isPositive();
        assertThat(super.countRowsInTableWhere(BESTELBONNEN, "id= "+id)).isOne();
    }
}