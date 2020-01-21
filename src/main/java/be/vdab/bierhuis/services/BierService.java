package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bier;

import java.util.List;
import java.util.Optional;

public interface BierService {
    long findAantalBieren();
    List<Bier> findAll();
    Optional<Bier> findById(long id);
    List<Bier> findByBrouwer(long idBrouwer);
}
