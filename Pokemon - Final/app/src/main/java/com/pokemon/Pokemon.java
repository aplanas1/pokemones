package com.pokemon;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    int id;

    String nombre;
    String descripcion;

    int imagen;
    int tipo1;
    int tipo2;

    public Pokemon(String nombre, int imagen, int tipo1, int tipo2, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.descripcion = descripcion;
    }
}
