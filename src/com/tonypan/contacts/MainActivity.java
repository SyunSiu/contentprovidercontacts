package com.tonypan.contacts;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	private List<String> contactsList = new ArrayList<String>();
	private ListView contactsView;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		contactsView = (ListView) findViewById(R.id.contacts_view);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
		contactsView.setAdapter(adapter);
		readContacts();
		
	}
	
	private void readContacts(){
		Cursor cursor = null;
		//查询联系人
		cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				null, null, null, null);
		while (cursor.moveToNext()) {
			//获取联系人姓名
			String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			//获取联系人号码
			String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			contactsList.add(displayName + "\n" + number); 
		}
		cursor.close();
	}
}
