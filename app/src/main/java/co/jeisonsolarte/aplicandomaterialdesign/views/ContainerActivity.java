package co.jeisonsolarte.aplicandomaterialdesign.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import co.jeisonsolarte.aplicandomaterialdesign.R;
import co.jeisonsolarte.aplicandomaterialdesign.login.view.LoginActivity;
import co.jeisonsolarte.aplicandomaterialdesign.post.views.HomeFragment;
import co.jeisonsolarte.aplicandomaterialdesign.views.fragment.ProfileFragment;
import co.jeisonsolarte.aplicandomaterialdesign.views.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mAuth=FirebaseAuth.getInstance();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.container_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.container_menu_singout:
                mAuth.signOut();
                if (LoginManager.getInstance()!=null){
                    LoginManager.getInstance().logOut();
                }
                Toast.makeText(this, "Sesión Cerrada", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.container_menu_options:
                Toast.makeText(this, "Platzigram by JeisonSolarte.com", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
