package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.repositories.BierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultBierService implements BierService {
    private final BierRepository bierRepository;

    public DefaultBierService(BierRepository bierRepository) {
        this.bierRepository = bierRepository;
    }

    @Override
    public long findAantalBieren() {
        return bierRepository.findAantalBieren();
    }

    @Override
    public List<Bier> findAll() {
        return bierRepository.findAll();
    }

    @Override
    public Optional<Bier> findById(long id) {
        return bierRepository.findById(id);
    }

    @Override
    public List<Bier> findByBrouwer(long idBrouwer) {
        return bierRepository.findByBrouwer(idBrouwer);
    }
}
