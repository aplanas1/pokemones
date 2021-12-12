package com.pokemon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PokemonesViewModel extends AndroidViewModel {

    PokemonesRepositorio pokemonesRepositorio;


    MutableLiveData<Pokemon> pokemonSeleccionado = new MutableLiveData<>();


    public PokemonesViewModel(@NonNull Application application) {
        super(application);

        pokemonesRepositorio = new PokemonesRepositorio(application);
    }


    LiveData<List<Pokemon>> obtener(){
        return pokemonesRepositorio.obtener();
    }

    void insertar(Pokemon pokemon){
        pokemonesRepositorio.insertar(pokemon);
    }

    void eliminar(Pokemon pokemon){
        pokemonesRepositorio.eliminar(pokemon);
    }


    void seleccionar(Pokemon pokemon){
        pokemonSeleccionado.setValue(pokemon);
    }

    MutableLiveData<Pokemon> seleccionado(){
        return pokemonSeleccionado;
    }
}