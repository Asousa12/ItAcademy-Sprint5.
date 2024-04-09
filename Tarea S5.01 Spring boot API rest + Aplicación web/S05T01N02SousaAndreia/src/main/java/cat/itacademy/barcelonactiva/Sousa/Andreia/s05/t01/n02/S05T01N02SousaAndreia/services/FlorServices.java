package cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.services;

import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.dto.FlorDTO;

import java.util.List;

public interface FlorServices {

    FlorDTO save (FlorDTO florDTO);
    FlorDTO update (int id, FlorDTO florDTO);
    public String delete(int id);
    FlorDTO findById(int id);
    List<FlorDTO> findAll();

}
