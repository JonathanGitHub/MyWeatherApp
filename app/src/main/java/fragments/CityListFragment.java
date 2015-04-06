package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jianyang.myweatherapp.R;

import java.util.ArrayList;

import adapters.CityListAdapter;
import models.CityDataModel;

/**
 * Created by jianyang on 4/3/15.
 */
public class CityListFragment extends Fragment
{
	public CityListFragment()
	{
	}

	private ListView        mListView;
	private CityListAdapter mCityListAdapter;
	private ArrayList<CityDataModel> mCityList = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Since we have a fixed number of cities, here we simulate "fetching city data" process

		CityDataModel newYork = new CityDataModel("New York", "40.7127", "-74.0059");
		CityDataModel london = new CityDataModel("London", "51.5072", "-0.1275");
		CityDataModel losAngeles = new CityDataModel("Los Angeles", "34.0500", "-118.2500");
		CityDataModel paris = new CityDataModel("Paris", "48.8567", "-2.3508");
		CityDataModel tokyo = new CityDataModel("Tokyo", "35.6833", "-139.6833");

		mCityList.add(newYork);
		mCityList.add(london);
		mCityList.add(losAngeles);
		mCityList.add(paris);
		mCityList.add(tokyo);

		mCityListAdapter = new CityListAdapter(getActivity(), mCityList);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		mListView = (ListView)rootView.findViewById(R.id.listview);
		mListView.setAdapter(mCityListAdapter);

		return rootView;
	}
}
