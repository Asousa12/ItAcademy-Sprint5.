package cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.dto;

import jakarta.persistence.*;

import java.util.List;

public class FlorDTO {


    private Integer pk_FlorID;
    private static String nombre;
    private static String paisFlor;
    private String tipoFlor;

    private static final List<String> paisesUE = List.of("Austria", "Bélgica", "Bulgaria", "Croacia", "Chipre",
            "República Checa", "Dinamarca", "Estonia", "Finlandia", "Francia", "Alemania", "Grecia", "Hungría", "Irlanda",
            "Italia", "Letonia", "Lituania", "Luxemburgo", "Malta", "Países Bajos", "Polonia", "Portugal", "Rumania",
            "Eslovaquia", "Eslovenia", "España", "Suecia");

    public FlorDTO() {

    }

    public FlorDTO(Integer pk_FlorID, String nombre, String paisFlor) {
        this.pk_FlorID=pk_FlorID;
        this.nombre = nombre;
        this.paisFlor =paisFlor;
        this.tipoFlor = determinarTipoFlor(paisFlor);
    }


    public void setPk_FlorID(Integer pk_FlorID) {
        this.pk_FlorID = pk_FlorID;
    }
    public void setPaisFlor(String paisFlor) {
        this.paisFlor = paisFlor;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoFlor(String tipoFlor) {
        this.tipoFlor = tipoFlor;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPaisFlor() {
        return paisFlor;
    }
    public Integer getPk_FlorID() {
        return pk_FlorID;
    }
    public String getTipoFlor() {
        return tipoFlor;
    }

    private String determinarTipoFlor(String pais) {
        if (paisesUE.contains(pais)) {
            return "UE";
        } else {
            return "Fuera UE";
        }
    }

    @Override
    public String toString() {
        return "Flor{" +
                "id=" + pk_FlorID +
                ", nombre='" + nombre + '\'' +
                ", tipoFlor=" + tipoFlor +
                '}';
    }

    }

