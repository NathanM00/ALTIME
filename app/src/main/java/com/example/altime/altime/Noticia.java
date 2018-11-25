package com.example.altime.altime;

public class Noticia {

    private String idnoticia;
    private String texto;

    private String diasemana;
    private String numerodia;
    private String tiponoticia;

    public Noticia() {
    }

    public Noticia(String idnoticia, String texto, String diasemana, String numerodia, String tiponoticia) {
        this.idnoticia = idnoticia;
        this.texto = texto;
        this.diasemana = diasemana;
        this.numerodia = numerodia;
        this.tiponoticia = tiponoticia;
    }

    public String getIdnoticia() {
        return idnoticia;
    }

    public void setIdnoticia(String idnoticia) {
        this.idnoticia = idnoticia;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {
        this.diasemana = diasemana;
    }

    public String getNumerodia() {
        return numerodia;
    }

    public void setNumerodia(String numerodia) {
        this.numerodia = numerodia;
    }

    public String getTiponoticia() {
        return tiponoticia;
    }

    public void setTiponoticia(String tiponoticia) {
        this.tiponoticia = tiponoticia;
    }
}