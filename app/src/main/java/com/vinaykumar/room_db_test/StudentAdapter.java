package com.vinaykumar.room_db_test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinaykumar.room_db_test.db.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private ArrayList<Student> studentArrayList;

    public void setStudents(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_student,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int i) {

        Student student = studentArrayList.get(i);

        holder.tvName.setText(student.getName());
        holder.tvEmail.setText(student.getEmail());
        holder.tvCountry.setText(student.getCountry());
        holder.tvDate.setText(student.getCreatedDate());

    }

    @Override
    public int getItemCount() {
        if(studentArrayList!=null) {
            return studentArrayList.size();
        }
        else{
            return 0;
        }
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{

        private TextView tvName , tvEmail , tvCountry ,tvDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvEmail= (TextView) itemView.findViewById(R.id.tv_email);
            tvCountry = (TextView) itemView.findViewById(R.id.tv_country);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);

        }
    }
}
