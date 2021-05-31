package com.example.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private OnItemClickListener itemClickListener;

    private CardsSource dataSource;

    private final Fragment fragment;

    private int menuPosition;

    public int getMenuPosition() {
        return menuPosition;
    }

    public NotesAdapter(CardsSource dataSource, Fragment fragment) {
        this.dataSource = dataSource;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(dataSource.getNote(position));
    }


    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item;
        private TextView name;
        private TextView date;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_note);
            date = itemView.findViewById(R.id.date_note);
            item = itemView.findViewById(R.id.item);

            registerContextMenu(itemView);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });

            item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition();
                    itemView.showContextMenu();
                    return true;
                }
            });
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null) {
                fragment.registerForContextMenu(itemView);
            }
        }


        public void setData(Note note) {
            name.setText(note.getName());
            date.setText(note.getDate());
        }
    }

}
