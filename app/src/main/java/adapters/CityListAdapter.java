package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jianyang.myweatherapp.R;
import com.example.jianyang.myweatherapp.WeeklyWeatherActivity;

import java.util.ArrayList;

import models.CityDataModel;

/**
 * Created by jianyang on 4/3/15.
 */
public class CityListAdapter extends BaseAdapter
{

	private Context                  context;
	private ArrayList<CityDataModel> cityList;
	public static String WEATHER_API_HEADER = "https://api.forecast.io/forecast/8ae0ab272af42a39549568ffc94a95c7/";

	public CityListAdapter()
	{
	}

	public CityListAdapter(Context context, ArrayList<CityDataModel> cityList)
	{
		this.context = context;
		this.cityList = cityList;
	}

	@Override
	public int getCount()
	{
		return cityList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{

		// Get an inflater reference
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHolderItem viewHolder;
		// Pass imageView reference to viewHolder so that we could reuse it
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.listview_item, null);
			viewHolder = new ViewHolderItem();
			viewHolder.mTextView = (TextView) convertView.findViewById(R.id.city_name);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolderItem) convertView.getTag();
		}

		viewHolder.mTextView.setText(cityList.get(position).getName());

		// Set click listener event
		viewHolder.mTextView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(context, WeeklyWeatherActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				String url = WEATHER_API_HEADER + cityList.get(position).getLat() + "," + cityList.get(position).getLng();
				intent.putExtra("url", url);
				context.startActivity(intent);
			}
		});

		return convertView;
	}

	//The classic viewHolder pattern which enables smooth scrolling for gridView
	static class ViewHolderItem
	{
		TextView mTextView;
	}
}
