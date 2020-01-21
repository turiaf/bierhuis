package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.domain.Brouwers;

import java.util.List;
import java.util.Optional;

public interface BrouwersRepository {
    List<Brouwers> findAll();
}
