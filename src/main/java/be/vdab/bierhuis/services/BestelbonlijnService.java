package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.Bestelbonlijn;

import java.util.Map;

public interface BestelbonlijnService {
    void create(Bestelbonlijn bestelbonlijn);
    long bestelbonBevestigen(Map<Long, Integer> bieren, Bestelbon bestelbon);
}
