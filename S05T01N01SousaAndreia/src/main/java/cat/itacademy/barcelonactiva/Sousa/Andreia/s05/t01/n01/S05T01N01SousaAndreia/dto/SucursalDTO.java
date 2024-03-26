package cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n01.S05T01N01SousaAndreia.dto;

import java.util.Arrays;
import java.util.List;

public class SucursalDTO {
    private Integer pk_SucursalID;
    private String nomSucursal;
    private String paisSucursal;
    private String tipoSucursal;

    private static final List<String> paisesUE = Arrays.asList("Austria", "Bélgica", "Bulgaria", "Croacia", "Chipre",
            "República Checa", "Dinamarca", "Estonia", "Finlandia", "Francia", "Alemania", "Grecia", "Hungría", "Irlanda",
            "Italia", "Letonia", "Lituania", "Luxemburgo", "Malta", "Países Bajos", "Polonia", "Portugal", "Rumania",
            "Eslovaquia", "Eslovenia", "España", "Suecia");


    public SucursalDTO() {
    }

    public SucursalDTO(Integer pk_SucursalID, String nomSucursal, String paisSucursal) {
        this.pk_SucursalID = pk_SucursalID;
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
        this.tipoSucursal = determinarTipoSucursal(paisSucursal);
    }
    private String determinarTipoSucursal(String pais) {
        if (paisesUE.contains(pais)) {
            return "UE";
        } else {
            return "Fuera UE";
        }
    }

    public Integer getPk_SucursalID() {
        return pk_SucursalID;
    }

    public void setPk_SucursalID(Integer pk_SucursalID) {
        this.pk_SucursalID = pk_SucursalID;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public String getPaisSucursal() {
        return paisSucursal;
    }

    public void setPaisSucursal(String paisSucursal) {
        this.paisSucursal = paisSucursal;
    }

    public String getTipoSucursal() {
        return tipoSucursal;
    }

    public void setTipoSucursal(String tipoSucursal) {
        this.tipoSucursal = tipoSucursal;
    }
}

