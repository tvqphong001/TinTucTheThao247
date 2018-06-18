package tranvuongquyenphong.com.adapter;

import android.app.Activity;
import android.content.Context;
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
import tranvuongquyenphong.com.model.News;

public class NewsAdapter extends ArrayAdapter<News> {
    Activity context;
    int resource;
    List<News> objects;
    public NewsAdapter(@NonNull Activity context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view;
        LayoutInflater inflater = this.context.getLayoutInflater();
        view = inflater.inflate(R.layout.item_list_tintuc,null);
        News news = objects.get(position);
        TextView TieuDe = view.findViewById(R.id.txtTieuDe);
        TextView TomTatNoiDung = view.findViewById(R.id.TomTatNoiDung);
        TextView SoLanXem = view.findViewById(R.id.txtSoLanXem);
        TextView LuotBinhLuan = view.findViewById(R.id.txtLuotBinhLuan);
        ImageView HinhTinTuc = view.findViewById(R.id.imvHinhTinTuc);

        TieuDe.setText(news.getTieudetin());
        TomTatNoiDung.setText(news.getNoidung());
        SoLanXem.setText(news.getSolanxem());
        LuotBinhLuan.setText(news.getIdtheloai());
        Picasso.get().load(news.getUrlhinh()).into(HinhTinTuc);
        return view;
    }
}
