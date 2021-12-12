package com.pokemon;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.company.room.R;
import com.company.room.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private PokemonesViewModel pokemonesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        pokemonesViewModel = new ViewModelProvider(this).get(PokemonesViewModel.class);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                // Top-level destinations:
                R.id.homeFragment, R.id.recyclerPokemonesFragment
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nuevoPokemonFragment
                        || destination.getId() == R.id.mostrarPokemonFragment) {
                    binding.navView.setVisibility(View.GONE);
                } else {
                    binding.navView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}