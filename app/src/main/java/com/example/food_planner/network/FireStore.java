package com.example.food_planner.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.food_planner.DB.MealsLocalDataSourceImp;
import com.example.food_planner.model.Meal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.rxjava3.annotations.Nullable;


public class FireStore {
    public  static void  addFavouriteToFirebase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Favorites").child(myMeal.getStrMeal()).setValue(myMeal)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public static void addPlanToFirebase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid())
                    .child("Plan")
                    .child(String.valueOf(myMeal.getDAY()))
                    .child(myMeal.getStrMeal())
                    .setValue(myMeal, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if (error != null) {
                                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {

                            }
                        }
                    });


        }
    }

    public static void getFavouriteFromFirebase(Context context, FirebaseUser user) {

        DatabaseReference rootFav = FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(user.getUid()).child("Favorites");
        rootFav.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren() ){
                    Meal meal = dataSnapshot.getValue(Meal.class);
                    MealsLocalDataSourceImp repo = MealsLocalDataSourceImp.getInstance(context.getApplicationContext());
                    repo.insert(meal);
                    Log.i("finaaaaaaaal",meal.getStrMeal()+""+meal.getIdMeal());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("test",error.getMessage());
            }
        });
    }


    public static void getPlanFromFireBase(Context context, FirebaseUser user, String day) {
        DatabaseReference rootPlan = FirebaseDatabase.getInstance().getReference()
                .child("Food Planner's Users").child(user.getUid()).child("Plan").child(day);

        Log.i("FireStore", "getPlanFromFireBase called for day: " + day);

        rootPlan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Meal meal = dataSnapshot.getValue(Meal.class);
                    MealsLocalDataSourceImp repo = MealsLocalDataSourceImp.getInstance(context.getApplicationContext());
                    repo.insert(meal);
                    Log.i("FireStore", "Meal retrieved: " + meal.getStrMeal() + ", " + meal.getIdMeal());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FireStore", "Error getting Plan data: " + error.getMessage());
            }
        });
    }


    public static void removeFavouriteFromFirebase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Favorites").child(myMeal.getStrMeal()).removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            //   Toast.makeText(context, "removed from your favList", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "failed to remove from your favList", Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    }


    public static void removePlanFromFireBase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Plan").child(String.valueOf(myMeal.getDAY())).child(myMeal.getStrMeal()).removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            //   Toast.makeText(context, "removed firebase", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }
    }
