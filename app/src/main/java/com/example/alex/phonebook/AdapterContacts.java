package com.example.alex.phonebook;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AdapterContacts extends RecyclerView.Adapter<AdapterContacts.ViewHolder> {
    List<ContactItem> contactItems;

    public AdapterContacts(List<ContactItem> contactItems) {
        this.contactItems = contactItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterContacts.ViewHolder holder, int position) {
        holder.name.setText(contactItems.get(position).getName());
        holder.number.setText(contactItems.get(position).getPhone());

    }


    @Override
    public int getItemCount() {
        return contactItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.contacts_list_item_name_id);
            number = (TextView) itemView.findViewById(R.id.contacts_list_item_number_id);
        }
    }
}
