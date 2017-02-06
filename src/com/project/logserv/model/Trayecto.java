package com.project.logserv.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trayecto {
	
	private	final StringProperty origen;
	private final StringProperty destino;
	private final IntegerProperty kilometros;
	private final ObjectProperty<LocalDate> fechaSalida;
	private final ObjectProperty<LocalDate> fechaLlegada;
	
	
	
	/**
     * Default constructor.
     */
    public Trayecto() {
        this(null, null);
    }

	/**
     * Constructor with some initial data.
     * 
     * @param origen
     * @param destino
     */
    public Trayecto(String origen, String destino) {
    	
        this.origen = new SimpleStringProperty(origen);
        this.destino = new SimpleStringProperty(destino);

        // Some initial dummy data, just for convenient testing. 
        this.kilometros= new SimpleIntegerProperty(12000);
        this.fechaSalida= new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        this.fechaLlegada = new SimpleObjectProperty<LocalDate>(LocalDate.of(2000, 3, 22));
        
        
    }
    
	
	public String getOrigen() {
		return this.origen.get();
	}
	public void setOrigen(String origen){
		this.origen.set(origen);
	}
	public StringProperty origenProperty() {
        return origen;
    }
	
	public String getDestino() {
		return this.destino.get();
	}
	public void setDestino(String destino){
		this.destino.set(destino);
	}
	public StringProperty destinoProperty() {
        return destino;
    }
	
	
	public int getKilometros() {
		return this.kilometros.get();
	}
	public void setKilometros(int kilometros){
		this.kilometros.set(kilometros);
	}
	public IntegerProperty kilometrosProperty() {
        return kilometros;
    }
	public LocalDate getFechaSalida(){
        return fechaSalida.get();
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }

    public ObjectProperty<LocalDate> fechaSalidaProperty() {
        return fechaSalida;
    }
	
    public LocalDate getFechaLlegada(){
        return fechaLlegada.get();
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada.set(fechaLlegada);
    }

    public ObjectProperty<LocalDate> fechaLlegadaProperty() {
        return fechaLlegada;
    }
	
	

	public String toString(){
		return "O: " + this.origen + " D: " + this.getDestino() + "\n K: " 
	+ this.kilometros + " Fs: " + this.fechaSalida;
	}
	
	

}
