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

	private ListView listView;
	private ArrayList<Value> gyroscopeDataList;
	private DatabaseReference databaseReference;
	private int num = 1;

	public GyroscopeValues() {
//		Toast.makeText(getContext(), "construct", Toast.LENGTH_SHORT).show();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
		// Inflate the layout for this fragment

		View view =  inflater.inflate(R.layout.fragment_gyroscope_values, container, false);
		listView = (ListView) view.findViewById(R.id.listViewID);

		retrieve();

		return view;
	}

	private void retrieve() {
		databaseReference = FirebaseDatabase.getInstance().getReference("Gyroscope");
		gyroscopeDataList = new ArrayList<>();
//		list.add(new Value(-1, -1, -1));

		num = 1;

		databaseReference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				gyroscopeDataList.clear();
				for (DataSnapshot ds : dataSnapshot.getChildren()) {
//					Value v = ds.getValue(Value.class);
					gyroscopeDataList.add(ds.getValue(Value.class));

//					Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
					Toast.makeText(getActivity(),  " next" + num, Toast.LENGTH_SHORT).show();
//					data.add(v);
					num++;
				}
				CustomAdapter customAdapter = new CustomAdapter(getActivity(), gyroscopeDataList);
				listView.setAdapter(customAdapter);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
			}
		});

		Toast.makeText(getActivity(), String.valueOf(gyroscopeDataList.size()), Toast.LENGTH_SHORT).show();



	}

}
