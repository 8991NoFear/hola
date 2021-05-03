package vn.binhld.hola.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.binhld.hola.R;
import vn.binhld.hola.model.User;

public class HomeTab extends Fragment {

    RecyclerView mRecyclerView;
    MyRecyclerAdapter mAdapter;
    LinearLayoutManager mLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.rv_home);

        // TODO (5): truyen dl cho adapter
        mAdapter = new MyRecyclerAdapter();
        mLayout = new LinearLayoutManager(getContext());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayout);

        setUpViewModel();

        return view;
    }

    private void setUpViewModel() {
        HomeTabViewModel viewModel = new ViewModelProvider(this).get(HomeTabViewModel.class);
        LiveData<User> userLiveData = viewModel.getUserLiveData();
    }

}
