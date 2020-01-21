package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bier;

import java.util.List;
import java.util.Optional;

public interface BierRepository {
    long findAantalBieren();
    List<Bier> findAll();
    Optional<Bier> findById(long id);
    List<Bier> findByBrouwer(long idBrouwer);
}
