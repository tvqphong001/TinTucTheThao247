package tranvuongquyenphong.com.fragment.Frament_History;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import tranvuongquyenphong.com.R;
import tranvuongquyenphong.com.activity.MainActivity2;
import tranvuongquyenphong.com.activity.TinTucActivity;
import tranvuongquyenphong.com.adapter.NewsAdapter;
import tranvuongquyenphong.com.adapter.TinTucAdapter;

public class fm_DocGanDay extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fm_docganday, container, false);
        return myView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        NewsAdapter adapter;
//        ListView ListTinTuc;
//        ListTinTuc = view.findViewById(R.id.list_tintuc);
//        adapter = new NewsAdapter(getActivity(),R.layout.item_list_tintuc, MainActivity2.ListNews);
//        ListTinTuc.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        ListTinTuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MainActivity2.ListNews.get(position);
//                startActivity(new Intent(getActivity(), TinTucActivity.class));
//            }
//        });

        // test
        TinTucAdapter adapter;
        ListView ListTinTuc;
        ListTinTuc = view.findViewById(R.id.list_tintuc);
        ListTinTuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),TinTucActivity.class);
                //intent.putExtra("link",listtin.get(position).getLink());
                intent.putExtra("link",MainActivity2.listTrangChu.get(position).getLink());
                startActivity(intent);
            }
        });

//        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.lay_Refresh);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(true);
//                (new Handler()).postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                        Refresh();
//                    }
//                },300);
//            }
//        });

        adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,MainActivity2.listTrangChu);
        ListTinTuc.setAdapter(adapter);
    }
}

