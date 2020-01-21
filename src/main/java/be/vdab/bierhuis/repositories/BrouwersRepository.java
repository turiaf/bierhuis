package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Brouwer;

import java.util.List;
import java.util.Optional;

public interface BrouwersRepository {
    List<Brouwer> findAll();
    Optional<Brouwer> findById(long id);
}
