package vn.binhld.hola.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.binhld.hola.model.User;

public class AppRepository {

    private final String TAG = AppRepository.class.getSimpleName();

    private static AppRepository mInstance;
    private static Object LOCK = new Object();

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mUserRef;
    private DatabaseReference mChatRoomRef;

    public static AppRepository getInstance() {
        if (mInstance == null) {
            synchronized (LOCK) {
                mInstance = new AppRepository();
            }
        }
        return mInstance;
    }

    public AppRepository() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        mUserRef = mDatabase.getReference().child("users");
        mChatRoomRef = mDatabase.getReference().child("chat_rooms");
    }

    public LiveData<User> loadUser() {
        LiveData<User> userLiveData;
        Task<DataSnapshot> tasks = mUserRef.child("userId1").get();
        tasks.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, String.valueOf(task.getResult().getValue()));
                } else {
                    Log.d(TAG, "loi thay ba luon: " + task.getException());
                }
            }
        });
        return null;
    }

}
