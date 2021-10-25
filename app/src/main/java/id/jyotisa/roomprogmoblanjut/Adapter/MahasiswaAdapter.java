package id.jyotisa.roomprogmoblanjut.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.jyotisa.roomprogmoblanjut.Database.Entity.Mahasiswa;
import id.jyotisa.roomprogmoblanjut.R;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewAdapter>{
    private List<Mahasiswa> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public MahasiswaAdapter(Context context, List<Mahasiswa> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_company, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.nama.setText(list.get(position).nama);
        holder.alamat.setText(list.get(position).alamat);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView nama, alamat;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.textName);
            alamat = itemView.findViewById(R.id.textLocation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
