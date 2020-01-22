package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.repositories.BestelbonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultBestelBonService implements BestelBonService {
    private final BestelbonRepository repository;

    public DefaultBestelBonService(BestelbonRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public long create(Bestelbon bestelbon) {
        return repository.create(bestelbon);
    }
}
