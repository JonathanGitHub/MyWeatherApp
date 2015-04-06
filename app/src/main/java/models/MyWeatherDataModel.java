package models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jianyang on 4/3/15.
 */
public class MyWeatherDataModel implements Parcelable
{
	private String currentSummary;
	private String currentTemperature;
	private String summaryOfTheWeek;

	// Using arrayList to scale our days, we can do 7 days, 10 days, and 21 days, or however long
	private ArrayList<WeeklyWeatherDataModel> mWeeklyWeatherDataArrayList;

	public MyWeatherDataModel()
	{

	}
	public MyWeatherDataModel(String currentSummary, String currentTemperature, String summaryOfTheWeek, ArrayList<WeeklyWeatherDataModel> weeklyWeatherDataArrayList)
	{
		this.currentSummary = currentSummary;
		this.currentTemperature = currentTemperature;
		this.summaryOfTheWeek = summaryOfTheWeek;
		mWeeklyWeatherDataArrayList = weeklyWeatherDataArrayList;
	}

	public String getSummaryOfTheWeek()
	{
		return summaryOfTheWeek;
	}

	public void setSummaryOfTheWeek(String summaryOfTheWeek)
	{
		this.summaryOfTheWeek = summaryOfTheWeek;
	}

	public String getCurrentSummary()
	{
		return currentSummary;
	}

	public void setCurrentSummary(String currentSummary)
	{
		this.currentSummary = currentSummary;
	}

	public String getCurrentTemperature()
	{
		return currentTemperature;
	}

	public void setCurrentTemperature(String currentTemperature)
	{
		this.currentTemperature = currentTemperature;
	}

	public ArrayList<WeeklyWeatherDataModel> getWeeklyWeatherDataArrayList()
	{
		return mWeeklyWeatherDataArrayList;
	}

	public void setWeeklyWeatherDataArrayList(ArrayList<WeeklyWeatherDataModel> weeklyWeatherDataArrayList)
	{
		mWeeklyWeatherDataArrayList = weeklyWeatherDataArrayList;
	}

	@Override
	public String toString()
	{
		return "MyWeatherDataModel{" +
			   "currentSummary='" + currentSummary + '\'' +
			   ", currentTemperature='" + currentTemperature + '\'' +
			   ", summaryOfTheWeek='" + summaryOfTheWeek + '\'' +
			   ", mWeeklyWeatherDataArrayList=" + mWeeklyWeatherDataArrayList +
			   '}';
	}

	// Parcelling part, since our data model is a customized class so if we wanna retain this object when user
	// rotates screen, we must implements Parcelable interface
	public MyWeatherDataModel(Parcel in)
	{
		String[] data = new String[3];
		in.readStringArray(data);
		this.currentSummary = data[0];
		this.currentTemperature = data[1];
		this.summaryOfTheWeek = data[2];

		ArrayList<WeeklyWeatherDataModel> arrayList = new ArrayList<>();
		in.readArrayList(WeeklyWeatherDataModel.class.getClassLoader());
		this.mWeeklyWeatherDataArrayList = arrayList;
	}


	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeStringArray(new String[]{
				this.currentSummary, this.currentTemperature, this.summaryOfTheWeek
		});
		dest.writeList(this.mWeeklyWeatherDataArrayList);
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
	{

		@Override
		public MyWeatherDataModel createFromParcel(Parcel source)
		{
			return new MyWeatherDataModel(source);
		}

		@Override
		public MyWeatherDataModel[] newArray(int size)
		{
			return new MyWeatherDataModel[size];
		}
	};
}

