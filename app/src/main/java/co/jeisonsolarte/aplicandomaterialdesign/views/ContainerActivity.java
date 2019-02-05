package co.jeisonsolarte.aplicandomaterialdesign.views;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import co.jeisonsolarte.aplicandomaterialdesign.R;
import co.jeisonsolarte.aplicandomaterialdesign.post.views.HomeFragment;
import co.jeisonsolarte.aplicandomaterialdesign.views.fragment.ProfileFragment;
import co.jeisonsolarte.aplicandomaterialdesign.views.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        HomeFragment homeFragment=new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame,homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();

        BottomNavigationView btnNavigation=findViewById(R.id.container_navigation);
        btnNavigation.setSelectedItemId(R.id.menu_home);
        btnNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_search:
                        SearchFragment searchFragment=new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame,searchFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.menu_home:
                        HomeFragment homeFragment=new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame,homeFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.menu_profile:
                        ProfileFragment profileFragment=new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame,profileFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                }
                return false;
            }
        });
    }
}
