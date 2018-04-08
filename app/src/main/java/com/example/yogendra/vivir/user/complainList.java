
package com.example.yogendra.vivir.user;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;

        import com.example.yogendra.vivir.adapter.ComplainListAdapter;
        import com.example.yogendra.vivir.R;

        import java.util.ArrayList;
        import java.util.List;

public class complainList extends AppCompatActivity {
    RecyclerView complaintResult;
    List<ComplaintValue> complaintValues = new ArrayList<>();

    ComplainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complainlist);
        for(int i=0; i<15; i++) {
            complaintValues.add(new ComplaintValue("Yogendra Maurya", R.drawable.profile_img,"2018-04-04"));
        };


        complaintResult = findViewById(R.id.complaintResult);
        complaintResult.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        complaintResult.setLayoutManager(linearLayoutManager);
        adapter = new ComplainListAdapter(complaintValues, complainList.this);
        complaintResult.setAdapter(adapter);
    }
}
