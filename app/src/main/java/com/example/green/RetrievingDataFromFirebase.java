package com.example.green;


import com.google.firebase.auth.FirebaseAuth;

public class RetrievingDataFromFirebase {
    private FirebaseAuth mAuth;
    public String retrieveUserID(){
        mAuth = FirebaseAuth.getInstance();
        return mAuth.getCurrentUser().getUid();
    }

}
