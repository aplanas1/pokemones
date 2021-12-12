package com.pokemon;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonesRepositorio {

    PokemonesBaseDeDatos.ElementosDao pokemonesDao;
    Executor executor = Executors.newSingleThreadExecutor();

    PokemonesRepositorio(Application application){
        pokemonesDao = PokemonesBaseDeDatos.obtenerInstancia(application).obtenerElementosDao();
    }


    LiveData<List<Pokemon>> obtener(){
        return pokemonesDao.obtener();
    }


    void insertar(Pokemon pokemon){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pokemonesDao.insertar(pokemon);
            }
        });
    }

    void eliminar(Pokemon pokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pokemonesDao.eliminar(pokemon);
            }
        });
    }
}