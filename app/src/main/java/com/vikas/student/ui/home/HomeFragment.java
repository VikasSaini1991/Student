package com.vikas.student.ui.home;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vikas.student.HomeActivity;
import com.vikas.student.R;
import com.vikas.student.StudentAdapter;
import com.vikas.student.model.StudentData;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ProgressDialog dialog ;
    String TAG="HomeFragment";
    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<StudentData> studentDataArrayList=new ArrayList<>();
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("USER");



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=root.findViewById(R.id.rv_home);


        firebaseData();
        setData();

        return root;
    }

    private void setData() {
        linearLayoutManager= new LinearLayoutManager( getActivity() );
        recyclerView.setLayoutManager( linearLayoutManager );
        adapter=new StudentAdapter(studentDataArrayList,this);
        recyclerView.setAdapter( adapter );


    }

    private void firebaseData() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("please wait...");
        dialog.show();

        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d( TAG, "onDataChange: "+dataSnapshot.getValue() );
               for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                   String name=snapshot.child("name").getValue().toString();
                   String branch=snapshot.child("branch").getValue().toString();
                   String sem=snapshot.child("sem").getValue().toString();
                   String rollno=snapshot.child("rollno").getValue().toString();



                    Log.d( TAG, "onDataChange2:   "+name +"  "+branch+"  "+sem+"  "+rollno);

studentDataArrayList.add(new StudentData(snapshot.child("name").getValue().toString()
       , snapshot.child("rollno").getValue().toString(),
        snapshot.child("branch").getValue().toString(),
        snapshot.child("sem").getValue().toString()
        ));

                    Log.d(TAG, "onDataChange:size "+studentDataArrayList.size());

               }

                adapter.notifyDataSetChanged();

                dialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }


}
