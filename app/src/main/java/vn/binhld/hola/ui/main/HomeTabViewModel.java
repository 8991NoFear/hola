package vn.binhld.hola.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import vn.binhld.hola.model.User;
import vn.binhld.hola.repository.AppRepository;

public class HomeTabViewModel extends AndroidViewModel {
    LiveData<User> userLiveData;
    AppRepository mRepository;

    public HomeTabViewModel(@NonNull Application application) {
        super(application);
        // TODO: lay du lieu va gan cho userLiveData
        mRepository = AppRepository.getInstance();
        userLiveData = mRepository.loadUser();
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
