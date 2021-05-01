package vn.binhld.hola.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.binhld.hola.R;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyDialogViewHolder> {

    // TODO (3): constructor nhan 1 danh sach cac model
    public MyRecyclerAdapter() {

    }

    @NonNull
    @Override
    public MyDialogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_item, parent, false);
        MyDialogViewHolder holder = new MyDialogViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyDialogViewHolder holder, int position) {
        holder.fill(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyDialogViewHolder extends RecyclerView.ViewHolder {

        Context context;

        ImageView avatar;
        TextView aliasName;
        TextView lastMessage;
        TextView lastSent;

        public MyDialogViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            avatar = itemView.findViewById(R.id.iv_avatar);
            aliasName = itemView.findViewById(R.id.tv_alias_name);
            lastMessage = itemView.findViewById(R.id.tv_last_message);
            lastSent = itemView.findViewById(R.id.tv_last_sent);
        }

        // TODO (1): nhan dl roi dien dl cho item view
        public void fill(int position) {

        }
    }

    // TODO (2): MainActivity se phai implement cai nay
    public interface ListItemClickedListener {
        public void onListItemClickedListener(int clickedItemIndex);
    }
}
