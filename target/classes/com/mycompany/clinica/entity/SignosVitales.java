
package com.mycompany.clinica.entity;

/**
 *
 * @author jacob
 */
public class SignosVitales {
    private int idSignosVitales;
    private String presionArterial;
    private String frecuenciaCardiaca;
    private String frecuenciaRespiratoria;
    private String temperatura;
    private double peso;
    private double talla;
    private String descripcion;
    private String imc;
    
    public SignosVitales() {
    }
    
    public SignosVitales(String presionArterial, String frecuenciaCardiaca, String frecuenciaRespiratoria, 
            String temperatura, double peso, double talla, String descripcion, String imc) {
        this.presionArterial = presionArterial;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        this.temperatura = temperatura;
        this.peso = peso;
        this.talla = talla;
        this.descripcion = descripcion;
        this.imc = imc;
    }

    public int getIdSignosVitales() {
        return idSignosVitales;
    }

    public void setIdSignosVitales(int idSignosVitales) {
        this.idSignosVitales = idSignosVitales;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public String getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(String frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public String getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(String frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImc() {
        
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    @Override
    public String toString() {
        return "SignosVitales{" + "idSignosVitales=" + idSignosVitales + 
                ", presionArterial=" + presionArterial + ", frecuenciaCardiaca=" + frecuenciaCardiaca + 
                ", frecuenciaRespiratoria=" + frecuenciaRespiratoria + ", temperatura=" + temperatura + 
                ", peso=" + peso + ", talla=" + talla + ", descripcion=" + descripcion + ", imc=" + imc + '}';
    }  
}
