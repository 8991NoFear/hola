package vn.binhld.hola.ui.chat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import vn.binhld.hola.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // TODO: neu la toi gui tin nhan thi inflate xml cua toi, neu ho gui tin nhan thi inflate xml cua ho
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyTheyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView avatar;
        MaterialTextView message;
        MaterialTextView timeSent;

        public MyTheyViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.iv_chat_avatar_they);
            message = (MaterialTextView) itemView.findViewById(R.id.mtv_chat_message_they);
            timeSent = (MaterialTextView) itemView.findViewById(R.id.mtv_chat_sent_they);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO:
        }
    }

    class MyMeViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        MaterialTextView message;
        MaterialTextView timeSent;

        public MyMeViewHolder2(@NonNull View itemView) {
            super(itemView);

            message = (MaterialTextView) itemView.findViewById(R.id.mtv_chat_message_me);
            timeSent = (MaterialTextView) itemView.findViewById(R.id.mtv_chat_sent_me);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO:
        }
    }
}
