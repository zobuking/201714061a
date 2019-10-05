package com.idp.group1.assignment201714029;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.idp.group1.assignment201714029.R;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Value> {

	public Activity context;
	public List<Value> list;


	public CustomAdapter(Activity context, List<Value> objects) {
		super(context, R.layout.layout_list_view, objects);
		this.context = context;
		this.list = objects;
	}

	@NonNull
	@Override
	public View getView(int position,  View convertView, ViewGroup parent) {

		LayoutInflater layoutInflater = context.getLayoutInflater();
		View view = layoutInflater.inflate(R.layout.layout_list_view, null, true);

		TextView t = view.findViewById(R.id.listViewTextID);
		t.setText(String.valueOf(list.get(position)));

		Toast.makeText(context, "working custom Adapter", Toast.LENGTH_SHORT).show();
		return  view;
	}
}
