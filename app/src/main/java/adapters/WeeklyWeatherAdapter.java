package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jianyang.myweatherapp.R;

import models.MyWeatherDataModel;

/**
 * Created by jianyang on 4/3/15.
 */
public class WeeklyWeatherAdapter extends BaseAdapter
{
	private Context mContext;
	private MyWeatherDataModel mMyWeatherDataModel;

	public WeeklyWeatherAdapter(Context context, MyWeatherDataModel myWeatherDataModel)
	{
		mContext = context;
		mMyWeatherDataModel = myWeatherDataModel;

	}

	@Override
	public int getCount()
	{
		return mMyWeatherDataModel.getWeeklyWeatherDataArrayList().size();
	}

	@Override
	public Object getItem(int position)
	{
		return mMyWeatherDataModel.getWeeklyWeatherDataArrayList().get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}


	static class ViewHolderItem
	{
		TextView weekDayMinTemperature;
		TextView weekDayMaxTemperature;
		TextView weekDaySummary;
		TextView day;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		ViewHolderItem viewHolderItem = new ViewHolderItem();
		// Get an inflater reference
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.weekly_item, null);
			// Pass imageView reference to viewHolder so that we could reuse it
			viewHolderItem.weekDayMinTemperature = (TextView)convertView.findViewById(R.id.min_temperature);
			viewHolderItem.weekDayMaxTemperature = (TextView)convertView.findViewById(R.id.max_temperature);
			viewHolderItem.weekDaySummary = (TextView)convertView.findViewById(R.id.daily_summary);
			viewHolderItem.day = (TextView)convertView.findViewById(R.id.day);

			convertView.setTag(viewHolderItem);
		}

		else
		{
			viewHolderItem = (ViewHolderItem) convertView.getTag();
		}

		if (position == 0)
		{
			viewHolderItem.day.setText("Today ");
		}
		else
		{
			viewHolderItem.day.setText("Day " + position);
		}
		viewHolderItem.weekDayMinTemperature.setText("Min: " + mMyWeatherDataModel.getWeeklyWeatherDataArrayList().get(position).getTemperatureMin() + " °F");
		viewHolderItem.weekDayMaxTemperature.setText("Max: " + mMyWeatherDataModel.getWeeklyWeatherDataArrayList().get(position).getTemperatureMax() + " °F");
		viewHolderItem.weekDaySummary.setText(mMyWeatherDataModel.getWeeklyWeatherDataArrayList().get(position).getSummaryOfTheDay());

		return convertView;
	}
}
