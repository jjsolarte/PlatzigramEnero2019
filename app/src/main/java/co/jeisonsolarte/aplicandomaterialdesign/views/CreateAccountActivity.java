package co.jeisonsolarte.aplicandomaterialdesign.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import co.jeisonsolarte.aplicandomaterialdesign.R;

public class CreateAccountActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        showToolbar(getResources().getString(R.string.create_toolbar_name),true);
    }

    public void showToolbar(String title,boolean upButton){
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void crearUsuario(View view){
        Intent intent=new Intent(this,ContainerActivity.class);
        startActivity(intent);
    }
}
