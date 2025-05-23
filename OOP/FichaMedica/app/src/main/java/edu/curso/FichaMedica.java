package edu.curso;

public class FichaMedica {
    private long id;
    private String tipoSanguineo;
    private float peso;
    private String alergias;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setTipoSanguineo(String tipoSanguineo){
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getTipoSanguineo(){
        return this.tipoSanguineo;
    }

    public void setPeso(float peso){
        this.peso = peso;
    }

    public float getPeso(){
        return this.peso;
    }

    public void setAlergias(String alergias){
        this.alergias = alergias;
    }

    public String getAlergias(){
        return this.alergias;
    }

}
