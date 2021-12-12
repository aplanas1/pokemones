package com.pokemon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.company.room.R;
import com.company.room.databinding.FragmentRecyclerPokemonesBinding;
import com.company.room.databinding.ViewholderPokemonBinding;

import java.util.List;


public class RecyclerElementosFragment extends Fragment {

    private FragmentRecyclerPokemonesBinding binding;
    PokemonesViewModel pokemonesViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerPokemonesBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pokemonesViewModel = new ViewModelProvider(requireActivity()).get(PokemonesViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nuevoPokemonFragment);
            }
        });

        PokemonesAdapter pokemonesAdapter;
        pokemonesAdapter = new PokemonesAdapter();

        binding.recyclerView.setAdapter(pokemonesAdapter);

        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemonesAdapter.obtenerElemento(posicion);
                pokemonesViewModel.eliminar(pokemon);

            }
        }).attachToRecyclerView(binding.recyclerView);

        obtenerPokemones().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                pokemonesAdapter.establecerLista(pokemons);
            }
        });
    }

    LiveData<List<Pokemon>> obtenerPokemones(){
        return pokemonesViewModel.obtener();
    }

    class PokemonesAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

        List<Pokemon> pokemons;

        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PokemonViewHolder(ViewholderPokemonBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

            Pokemon pokemon = pokemons.get(position);

            holder.binding.nombre.setText(pokemon.nombre);
            Glide.with(RecyclerElementosFragment.this).load(pokemon.imagen).into(holder.binding.imagen);
            Glide.with(RecyclerElementosFragment.this).load(pokemon.tipo1).into(holder.binding.tipo1);
            Glide.with(RecyclerElementosFragment.this).load(pokemon.tipo2).into(holder.binding.tipo2);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokemonesViewModel.seleccionar(pokemon);
                    navController.navigate(R.id.action_mostrarPokemonFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return pokemons != null ? pokemons.size() : 0;
        }

        public void establecerLista(List<Pokemon> pokemons){
            this.pokemons = pokemons;
            notifyDataSetChanged();
        }

        public Pokemon obtenerElemento(int posicion){
            return pokemons.get(posicion);
        }
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPokemonBinding binding;

        public PokemonViewHolder(ViewholderPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}