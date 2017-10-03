/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author cesar
 */
public class LogLocal {
    private String status;
    private String local;
    private String thread;
    private String group;
    private String miembro;
    private String tamano;
    private String estado;
    private String archive;
    private String tipo;
    private String rdf;
   private String sequence;
    private String first;
    private String second;

 
    
    //   String th,gr,mi,tam,est,arc,tip,rdf,seq,fir,sec;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getMiembro() {
        return miembro;
    }

    public void setMiembro(String miembro) {
        this.miembro = miembro;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRdf() {
        return rdf;
    }

    public void setRdf(String rdf) {
        this.rdf = rdf;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
    //Constructores
    
    public LogLocal(String status, String local) {
        this.status = status;
        this.local = local;
    }
        public LogLocal(String thread, String group, String miembro, String tamano, String estado, String archive, String tipo, String rdf, String sequence, String first, String second) {
        this.thread = thread;
        this.group = group;
        this.miembro = miembro;
        this.tamano = tamano;
        this.estado = estado;
        this.archive = archive;
        this.tipo = tipo;
        this.rdf = rdf;
        this.sequence = sequence;
        this.first = first;
        this.second = second;
    }
}
