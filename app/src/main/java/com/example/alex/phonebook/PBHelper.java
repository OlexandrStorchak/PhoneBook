package com.example.alex.phonebook;

import android.annotation.TargetApi;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 23.11.2016.
 */

public class PBHelper {
    Context context;
    Uri phoneContacts = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;



    public PBHelper(Context context) {
        this.context = context;
    }

    List<ContactItem> getContacts(){
        List<ContactItem> ListContact = new ArrayList<>();
        Cursor phones = context.getContentResolver().query(
                phoneContacts,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactItem contactItem = new ContactItem(name,phoneNumber);
            ListContact.add(contactItem);

        }
//        testList(ListContact);
        phones.close();
        return ListContact;

    }


    void addContacts() {
        Intent addContacts = new Intent(ContactsContract.Intents.Insert.ACTION);
        addContacts.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        context.startActivity(addContacts);



    }
//    void testList(ArrayList<ContactItem> list){
//        int index =0;
//        while (index !=  list.size()){
//            Log.d("test", "name : "+list.get(index).getName()+" Number : " + list.get(index).getPhone());
//            index++;
//        }
//
//
//    }
}
