package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;

public interface BestelbonRepository {
    long create(Bestelbon bestelbon);
}
