package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.repositories.BrouwersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
class DefaultBrouwerService implements BrouwerService {
    private final BrouwersRepository brouwersRepository;

    public DefaultBrouwerService(BrouwersRepository brouwersRepository) {
        this.brouwersRepository = brouwersRepository;
    }

    @Override
    public List<Brouwer> findAll() {
        return brouwersRepository.findAll();
    }

    @Override
    public Optional<Brouwer> findById(long id) {
        return brouwersRepository.findById(id);
    }
}
