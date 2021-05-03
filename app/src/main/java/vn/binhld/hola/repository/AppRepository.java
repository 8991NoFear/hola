package vn.binhld.hola.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import vn.binhld.hola.model.User;

public class AppRepository {

    private final String TAG = AppRepository.class.getSimpleName();

    private static AppRepository mInstance;
    private static Object LOCK = new Object();

    // auth
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    // realtime-database
    private FirebaseDatabase mDatabase;
    private DatabaseReference mUserRef;
    private DatabaseReference mChatRoomRef;

    // firestore
    private FirebaseStorage mStorage;
    private StorageReference mPhotoRef;

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

        mStorage = FirebaseStorage.getInstance();
        mPhotoRef = mStorage.getReference().child("photos");
    }

    public LiveData<User> loadUser() {
//        LiveData<User> userLiveData;
//        Task<DataSnapshot> tasks = mUserRef.child("userId1").get();
//        tasks.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    Log.d(TAG, String.valueOf(task.getResult().getValue()));
//                } else {
//                    Log.d(TAG, "exception occur: " + task.getException());
//                }
//            }
//        });

        return null;
    }

    public void pushUser(User newUser) {
        mUserRef.child(mUser.getUid()).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "insert new user successful");
                } else {
                    Log.d(TAG, "exception occur: " + task.getException());
                }
            }
        });
    }

}
