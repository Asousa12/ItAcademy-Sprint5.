package cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.services;

import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.domain.FlorEntity;
import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.dto.FlorDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.repository.FlorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlorServicesImp implements FlorServices{

    @Autowired
   private final FlorRepository florRepository;


    @Autowired
    public FlorServicesImp(FlorRepository florRepository) {
        this.florRepository = florRepository;
    }

    @Override
    public FlorDTO save(FlorDTO florDTO) {
        FlorEntity flor = new FlorEntity(florDTO.getNombre(), florDTO.getPaisFlor());
        FlorEntity savedFlor = florRepository.save(flor);
        return convertToDTO(savedFlor);
    }

    @Override
    public FlorDTO update(int id, FlorDTO florDTO) {
        Optional<FlorEntity> optionalFlor = florRepository.findById(id);
        if (optionalFlor.isPresent()) {
            FlorEntity florEntity = optionalFlor.get();
            florEntity.setNombre(florDTO.getNombre());
            florEntity.setPaisFlor(florDTO.getPaisFlor());
            FlorEntity updatedFlor = florRepository.save(florEntity);
            return convertToDTO(updatedFlor);
        } else {
            return null;
        }
    }

    @Override
    public String delete(int id) {
        florRepository.deleteById(id);
        return "Flor eliminada con éxito";
    }

    @Override
    public FlorDTO findById(int id) {
        return florRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public List<FlorDTO> findAll() {
        return florRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FlorDTO convertToDTO(FlorEntity florEntity) {
        return new FlorDTO(florEntity.getPk_FlorID(), florEntity.getNombre(), florEntity.getPaisFlor());
    }
}



