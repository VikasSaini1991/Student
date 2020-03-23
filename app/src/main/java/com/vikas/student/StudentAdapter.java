package com.vikas.student;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.vikas.student.model.StudentData;

import java.util.ArrayList;

public class StudentAdapter extends  RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    String TAG="StudentAdapter";
    private AlertDialog dialog;
    ArrayList<StudentData> studentDataArrayList;
    Fragment fragment;

    public StudentAdapter(ArrayList<StudentData> studentDataArrayList, Fragment fragment) {
        this.studentDataArrayList = studentDataArrayList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.layout_student_list, parent, false );
        return new StudentViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentViewHolder holder, int position) {
        final  StudentData studentData=studentDataArrayList.get(position);
        Log.d(TAG, "onBindViewHolder: ");
        Log.d(TAG, "onBindViewHolder:Name"+position);
        String id= String.valueOf(position+1);
        holder.tvId.setText(id);
        holder.tvName.setText(studentData.getName());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 TextView tvName,tvBranch,tvRollNo,tvSem,tvCancel;
                Log.d(TAG, "onClick: "+studentData.getRollno().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder( fragment.getContext() );
                LayoutInflater inflater = fragment.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.student_dialog_layout, null);
                tvName=dialogView.findViewById(R.id.tv_student_name_dialog);
                tvBranch=dialogView.findViewById(R.id.tv_branch);
                tvRollNo=dialogView.findViewById(R.id.tv_roll_no);
                tvSem=dialogView.findViewById(R.id.tv_sem);
                tvName.setText(studentData.getName().toString());
                tvBranch.setText(studentData.getBranch().toString());
                tvRollNo.setText(studentData.getRollno().toString());
                tvSem.setText(studentData.getSem().toString());
                tvCancel=dialogView.findViewById(R.id.tv_cancel);
                builder.setView(dialogView);
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentDataArrayList.size();
    }

    public  class  StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvId,tvName;
        CardView cv;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId=itemView.findViewById(R.id.tv_student_id_list);
            tvName=itemView.findViewById(R.id.tv_student_name_list);
            cv=itemView.findViewById(R.id.cv);

        }
    }
}
