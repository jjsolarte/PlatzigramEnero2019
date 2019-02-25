package co.jeisonsolarte.aplicandomaterialdesign;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PlatzigramAplication extends Application {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseStorage firebaseStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

        FirebaseCrash.log("inicializando variables en PlatzigramAplication");

        mAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=mAuth.getCurrentUser();
                if (firebaseAuth!=null){
                    //usuario logeado
                    Crashlytics.log(Log.WARN,"PlatzigramAplication","usuario logeado "+firebaseUser.getEmail());
                }else {
                    //usuario no logeado
                    Crashlytics.log(Log.WARN,"PlatzigramAplication","usuario no logeado ");

                }
            }
        };

        firebaseStorage=FirebaseStorage.getInstance();
    }

    public StorageReference getStorageReference(){
        return firebaseStorage.getReference();
    }

}
