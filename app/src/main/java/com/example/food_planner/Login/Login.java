package com.example.food_planner.Login;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food_planner.Home.view.homeFragment;
import com.example.food_planner.MainActivity;
import com.example.food_planner.R;
import com.example.food_planner.SignUp.Sign_up;
import com.example.food_planner.network.FireStore;
import com.example.food_planner.network.NetworkConnection;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText;
    private Button loginButton,guest;
    private FirebaseAuth auth;
    private static final int RC_SIGN_IN = 9001;
    private static final int RC_PLAY_SERVICES_MISSING = 9002;
    private static final String SHARED_PREFS = "sharedPreferences";


    private GoogleApiClient mGoogleApiClient;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        guest = findViewById(R.id.btn_guest);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        checkBox();
        auth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkConnection.getConnectivity(Login.this)){
                    String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        FirebaseUser user = auth.getCurrentUser();

                                        FireStore.getFavouriteFromFirebase(Login.this,user);
                                        FireStore.getPlanFromFireBase(Login.this,user,"1");
                                        FireStore.getPlanFromFireBase(Login.this,user,"2");
                                        FireStore.getPlanFromFireBase(Login.this,user,"3");
                                        FireStore.getPlanFromFireBase(Login.this,user,"4");
                                        FireStore.getPlanFromFireBase(Login.this,user,"5");
                                        FireStore.getPlanFromFireBase(Login.this,user,"6");
                                        FireStore.getPlanFromFireBase(Login.this,user,"7");
                                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("name", "true");
                                        editor.apply();
                                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this, MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPassword.setError("Empty fields are not allowed");
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Empty fields are not allowed");
                } else {
                    loginEmail.setError("Please enter correct email");
                }
            }
                else {

                    String yes = "OK";
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this); // Use the activity context
                    builder.setMessage("Please reconnect and try again ");
                    builder.setTitle("There is no internet connection");
                    builder.setCancelable(false);
                    builder.setPositiveButton(Html.fromHtml("<font color='#F8B66C'>" + yes + "</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {
                      dialog.cancel();
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Sign_up.class));
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        findViewById(R.id.btn_google).setOnClickListener(v -> signIn());


        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yes = "YES, I'M SURE";
                String no = "NO, GO BACK";
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this); // Use the activity context
                builder.setMessage("You will lose some features like saving your favourite meals ");
                builder.setTitle("Wait ! Are You Sure You Want To Scroll As Guest ?");
                builder.setCancelable(false);
                builder.setPositiveButton(Html.fromHtml("<font color='#F8B66C'>" + yes + "</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {
                    startActivity(new Intent(Login.this,MainActivity.class));
                    Toast.makeText(Login.this, "Login as a guest was successful", Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton(Html.fromHtml("<font color='#FFBF00'>" + no + "</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

        private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String check = sharedPreferences.getString("name","");
        if ((check.equals("true"))) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Toast.makeText(this, "Google Sign In failed. Status code: " + result.getStatus().getStatusCode(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            FireStore.getFavouriteFromFirebase(Login.this,auth.getCurrentUser());
                            FireStore.getPlanFromFireBase(Login.this,auth.getCurrentUser(),"1");
                            FireStore.getPlanFromFireBase(Login.this,auth.getCurrentUser(),"2");
                            FireStore.getPlanFromFireBase(Login.this,auth.getCurrentUser(),"3");
                            FireStore.getPlanFromFireBase(Login.this,auth.getCurrentUser(),"4");
                            FireStore.getPlanFromFireBase(Login.this,auth.getCurrentUser(),"5");
                            FireStore.getPlanFromFireBase(Login.this,auth.getCurrentUser(),"6");
                            FireStore.getPlanFromFireBase(Login.this,auth.getCurrentUser(),"7");
                            String displayName = user.getDisplayName();
                            String emailuser = user.getEmail();
                            Toast.makeText(this, "Welcome, " + displayName + "!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                            this.finish(); // Finish the current activity to prevent going back on back press
                        } else {

                            Toast.makeText(this, "User object is null in updateUI", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "signInWithCredential:failure " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection to Google services failed: " + connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
        int errorCode = connectionResult.getErrorCode();
        switch (errorCode) {
            case ConnectionResult.SERVICE_MISSING:
                GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
                Dialog dialog = apiAvailability.getErrorDialog(
                        this, errorCode, RC_PLAY_SERVICES_MISSING);
                dialog.show();
                break;
        }
    }


}