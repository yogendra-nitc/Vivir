package com.example.yogendra.vivir;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.SearchView;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;

public class RegUserSearch extends AppCompatActivity {
    SearchView searchView;
    RecyclerView searchResult;
    List<SearchItem> flatList = new ArrayList<>();

    SearchActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_user_search);
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home1));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home2));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home3));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home4));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home5));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home6));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home7));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home8));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home9));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home10));


        searchResult = findViewById(R.id.searchResult);
        searchResult.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        searchResult.setLayoutManager(linearLayoutManager);
        adapter = new SearchActivityAdapter(flatList, RegUserSearch.this);
        searchResult.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_file, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();

        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text)).
                setHintTextColor(getResources().getColor(R.color.colorAccent));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final  List<SearchItem> filtermodelist=filter(flatList, newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });

        return true;
    }
    private List<SearchItem> filter(List<SearchItem> pl,String query)
    {
        query=query.toLowerCase();
        final List<SearchItem> filteredModeList=new ArrayList<>();
        for (SearchItem model:pl)
        {
            final String text=model.getName().toLowerCase();
            if (text.startsWith(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    //for changing the text color of searchview
    private void changeSearchViewTextColor(View view) {
        if (view != null) {
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(Color.WHITE);
            return;
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                changeSearchViewTextColor(viewGroup.getChildAt(i));
            }
        }
    }
}
}
