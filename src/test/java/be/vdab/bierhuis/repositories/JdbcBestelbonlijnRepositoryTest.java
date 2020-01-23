package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.Bestelbonlijn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@JdbcTest
@Import(JdbcBestelbonlijnRepository.class)
@Sql("/insertBestelbonlijn.sql")
class JdbcBestelbonlijnRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BESTELBONLIJNEN = "bestelbonlijnen";
    private final JdbcBestelbonlijnRepository repository;

    public JdbcBestelbonlijnRepositoryTest(JdbcBestelbonlijnRepository repository) {
        this.repository = repository;
    }

}