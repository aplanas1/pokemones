package com.pokemon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.company.room.databinding.FragmentMostrarPokemonBinding;


public class MostrarPokemonFragment extends Fragment {
    private FragmentMostrarPokemonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarPokemonBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonesViewModel pokemonesViewModel = new ViewModelProvider(requireActivity()).get(PokemonesViewModel.class);

        pokemonesViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon) {
                binding.nombre.setText(pokemon.nombre);
                binding.imagen.setImageResource(pokemon.imagen);
                binding.tipo1.setImageResource(pokemon.tipo1);
                binding.tipo2.setImageResource(pokemon.tipo2);
                binding.descripcion.setText(pokemon.descripcion);
            }
        });
    }
}