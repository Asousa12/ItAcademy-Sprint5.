package cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "flor")
public class FlorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_FlorID;
    @Column(name = "nombre")
    private String nombre;

    @Column(name="pais")
    private String paisFlor;


    public FlorEntity() {

    }

    public FlorEntity(String nombre, String paisFlor) {
        this.nombre = nombre;
        this.paisFlor=paisFlor;
    }

    public void setPk_FlorID(Integer pk_FlorID) {
        this.pk_FlorID = pk_FlorID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPaisFlor(String paisFlor){
        this.paisFlor=paisFlor;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPaisFlor(){
        return paisFlor;
    }

    public Integer getPk_FlorID() {
        return pk_FlorID;
    }

    @Override
    public String toString() {
        return "Flor{" +
                "id=" + pk_FlorID +
                ", nombre='" + nombre + '\'' +
                ", paisFlor='" + paisFlor + '\'' +
                '}';
    }
}
