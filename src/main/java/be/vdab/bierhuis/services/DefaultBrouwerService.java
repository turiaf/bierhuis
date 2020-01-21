package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Brouwers;
import be.vdab.bierhuis.repositories.BrouwersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
class DefaultBrouwerService implements BrouwerService {
    private final BrouwersRepository brouwersRepository;

    public DefaultBrouwerService(BrouwersRepository brouwersRepository) {
        this.brouwersRepository = brouwersRepository;
    }

    @Override
    public List<Brouwers> findAll() {
        return brouwersRepository.findAll();
    }
}
