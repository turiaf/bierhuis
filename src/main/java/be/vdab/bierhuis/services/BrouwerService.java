package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Brouwers;

import java.util.List;

public interface BrouwerService {
    List<Brouwers> findAll();
}
