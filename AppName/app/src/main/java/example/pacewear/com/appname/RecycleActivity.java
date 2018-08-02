package example.pacewear.com.appname;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.recycleview.DemoAdapter;
import com.example.recycleview.DividerItemDecoration;
import com.example.recycleview.OnItemClickLitener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p_billylu on 2018/7/30.
 */

public class RecycleActivity extends Activity implements  OnItemClickLitener{

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private DemoAdapter mAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_sub_item);

        initData();
        initViews();

    }


    private void initViews(){
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DemoAdapter(this,mDatas);
        mAdapter.setOnItemClickLitener(this);
        mRecyclerView.setAdapter(mAdapter);

//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
//        mRecyclerView.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);



        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu. main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.add_item:
                mAdapter.addData(2);
                break;

            case R.id.remove_item:
                mAdapter.removeData(2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getApplicationContext(),"get this postion:"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

}
