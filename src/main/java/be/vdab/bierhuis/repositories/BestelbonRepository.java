package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;

import java.util.List;
import java.util.Optional;

public interface BestelbonRepository {
    long create(Bestelbon bestelbon);
}
