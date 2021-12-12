package com.pokemon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.company.room.R;
import com.company.room.databinding.FragmentNuevoPokemonBinding;


public class NuevoPokemonFragment extends Fragment {
    private FragmentNuevoPokemonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoPokemonBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonesViewModel pokemonesViewModel = new ViewModelProvider(requireActivity()).get(PokemonesViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();
                String tipo1 = binding.tipo1.getText().toString();
                String tipo2 = binding.tipo2.getText().toString();
                int img = 0;
                int t1 = 0;
                int t2 = 0;

                switch (tipo1){
                    case "Agua":
                        t1 = R.drawable.agua;
                        break;
                    case "Fuego":
                        t1 = R.drawable.fuego;
                        break;
                    case "Planta":
                        // El nombre es panta y no planta porque con "planta" el archivo me da error
                        t1 = R.drawable.panta;
                        break;
                    case "Normal":
                        t1 = R.drawable.normal;
                        break;
                    case "Volador":
                        t1 = R.drawable.volador;
                        break;
                    case "Electrico":
                        t1 = R.drawable.electrico;
                        break;
                    case "Veneno":
                        t1 = R.drawable.veneno;
                        break;
                    case "Psqiquico":
                        t1 = R.drawable.psiquico;
                        break;
                    case "Fantasma":
                        t1 = R.drawable.fantasma;
                        break;
                    case "Siniestro":
                        t1 = R.drawable.siniestro;
                        break;
                    case "Bicho":
                        t1 = R.drawable.bicho;
                        break;
                    case "Acero":
                        t1 = R.drawable.acero;
                        break;
                    case "Dragon":
                        t1 = R.drawable.dragon;
                        break;
                    case "Hada":
                        t1 = R.drawable.hada;
                        break;
                    case "Lucha":
                        t1 = R.drawable.lucha;
                        break;
                    case "Roca":
                        t1 = R.drawable.roca;
                        break;
                    case "Tierra":
                        t1 = R.drawable.tierra;
                        break;
                    default:
                        t1 = R.drawable.nada;
                        break;
                }
                switch (tipo2){
                    case "Agua":
                        t2 = R.drawable.agua;
                        break;
                    case "Fuego":
                        t2 = R.drawable.fuego;
                        break;
                    case "Planta":
                        // El nombre es panta y no planta porque con "planta" el archivo me da error
                        t2 = R.drawable.panta;
                        break;
                    case "Normal":
                        t2 = R.drawable.normal;
                        break;
                    case "Volador":
                        t2 = R.drawable.volador;
                        break;
                    case "Electrico":
                        t2 = R.drawable.electrico;
                        break;
                    case "Veneno":
                        t2 = R.drawable.veneno;
                        break;
                    case "Psqiquico":
                        t2 = R.drawable.psiquico;
                        break;
                    case "Fantasma":
                        t2 = R.drawable.fantasma;
                        break;
                    case "Siniestro":
                        t2 = R.drawable.siniestro;
                        break;
                    case "Bicho":
                        t2 = R.drawable.bicho;
                        break;
                    case "Acero":
                        t2 = R.drawable.acero;
                        break;
                    case "Dragon":
                        t2 = R.drawable.dragon;
                        break;
                    case "Hada":
                        t2 = R.drawable.hada;
                        break;
                    case "Lucha":
                        t2 = R.drawable.lucha;
                        break;
                    case "Roca":
                        t2 = R.drawable.roca;
                        break;
                    case "Tierra":
                        t2 = R.drawable.tierra;
                        break;
                    default:
                        t2 = R.drawable.nada;
                        break;
                }
                switch (nombre){
                    case "Bulbasaur":
                        img = R.drawable.bulbasaur;
                        break;
                    case "Ivysaur":
                        img = R.drawable.ivysaur;
                        break;
                    case "Venusaur":
                        img = R.drawable.venusaur;
                        break;
                    case "Charmander":
                        img = R.drawable.charmander;
                        break;
                    case "Charmeleon":
                        img = R.drawable.charmeleon;
                        break;
                    case "Charizard":
                        img = R.drawable.charizard;
                        break;
                    case "Squirtle":
                        img = R.drawable.squirtle;
                        break;
                    case "Wartortle":
                        img = R.drawable.wartortle;
                        break;
                    case "Blastoise":
                        img = R.drawable.blastoise;
                        break;
                    default:
                        img = R.drawable.agumon;
                        break;
                }

                pokemonesViewModel.insertar(new Pokemon(nombre, img, t1, t2, descripcion));

                navController.popBackStack();
            }
        });
    }
}