package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.Bestelbonlijn;
import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.forms.BestelLijn;
import be.vdab.bierhuis.repositories.BestelbonlijnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultBestelbonlijnService implements BestelbonlijnService {
    private final BestelbonlijnRepository bestelbonlijnRepository;
    private final BestelBonService bonService;
    private final BierService bierService;

    public DefaultBestelbonlijnService(BestelbonlijnRepository bestelbonlijnRepository,
                                       BestelBonService bonService, BierService bierService) {
        this.bestelbonlijnRepository = bestelbonlijnRepository;
        this.bonService = bonService;
        this.bierService = bierService;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Bestelbonlijn bestelbonlijn) {
        bestelbonlijnRepository.create(bestelbonlijn);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public long bestelbonBevestigen(Map<Long, Integer> bieren, Bestelbon bestelbon) {
        long idBon = bonService.create(bestelbon);
        bieren.entrySet().stream().forEach(entry -> {
            bierService.findById(entry.getKey()).ifPresent(bier -> {
                bierService.update(bier);
                this.create(new Bestelbonlijn(idBon, bier.getId(), entry.getValue(), bier.getPrijs()));
            });
        });
        return idBon;
    }
}
