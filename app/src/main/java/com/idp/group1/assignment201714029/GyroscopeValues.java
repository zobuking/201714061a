package com.idp.group1.assignment201714029;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GyroscopeValues extends Fragment {

	ListView listView;
	private List <Value> data;
	DatabaseReference databaseReference;
	int num = 1;

	public GyroscopeValues() {
//		Toast.makeText(getContext(), "construct", Toast.LENGTH_SHORT).show();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
		// Inflate the layout for this fragment
		View view =  inflater.inflate(R.layout.fragment_gyroscope_values, container, false);
		listView = view.findViewById(R.id.listViewID);

		databaseReference = FirebaseDatabase.getInstance().getReference("Gyroscope");
		data = new ArrayList<Value>();

//		list.add(new Value(-1, -1, -1));


//		CustomAdapter customAdapter = new CustomAdapter(getActivity(), data);

		num = 1;

		databaseReference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				data.clear();
				for (DataSnapshot i : dataSnapshot.getChildren()) {
					Value v = i.getValue(Value.class);
					data.add(v);

//					Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
					Toast.makeText(getActivity(), v.toString() + " next" + num, Toast.LENGTH_SHORT).show();
//					data.add(v);
					num++;
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

		Toast.makeText(getActivity(), String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
//		listView.setAdapter(customAdapter);


		return view;
	}

}
