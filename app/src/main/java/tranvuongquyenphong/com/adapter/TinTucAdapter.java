package tranvuongquyenphong.com.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tranvuongquyenphong.com.R;
import tranvuongquyenphong.com.model.TinTuc;

public class TinTucAdapter extends ArrayAdapter<TinTuc> {
    Activity context;
    int resource;
    List<TinTuc> objects;

    public TinTucAdapter(@NonNull Activity context, int resource, @NonNull List<TinTuc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.item_list_tintucc,null);
        TinTuc tinTuc  = objects.get(position);

        TextView textView = view.findViewById(R.id.textview);
        ImageView imageView = view.findViewById(R.id.imview);
        TextView NguonBao = view.findViewById(R.id.txtNguonTin);
        TextView NgayDang = view.findViewById(R.id.txtNgayDang);

        if(tinTuc!=null) {
            textView.setText(tinTuc.title);
            Picasso.get().load(tinTuc.getHinhanh()).into(imageView);
            NguonBao.setText(tinTuc.getNguonbao());
            //NgayDang.setText(tinTuc.getNgaydang().getDate()+"");
        }
        return view;
    }
}
