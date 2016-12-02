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




void addContact(String name,String number){
    ArrayList<ContentProviderOperation> operations = new ArrayList<>();
    int rawContactInsertIndex = operations.size();

    operations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME,null )
            .build());
    operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
            .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
            .build());
    operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactInsertIndex)
            .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
            .build());
    try {
        context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, operations);
    } catch (RemoteException | OperationApplicationException e) {
        e.printStackTrace();
    }
}
}
