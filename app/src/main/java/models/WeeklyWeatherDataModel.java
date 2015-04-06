package models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jianyang on 4/4/15.
 */
public class WeeklyWeatherDataModel implements Parcelable
{
	private String summaryOfTheDay;
	private String temperatureMin;
	private String temperatureMax;

	public WeeklyWeatherDataModel()
	{

	}

	public WeeklyWeatherDataModel(String summaryOfTheDay, String temperatureMin, String temperatureMax)
	{
		this.summaryOfTheDay = summaryOfTheDay;
		this.temperatureMin = temperatureMin;
		this.temperatureMax = temperatureMax;
	}

	public String getSummaryOfTheDay()
	{
		return summaryOfTheDay;
	}

	public void setSummaryOfTheDay(String summaryOfTheDay)
	{
		this.summaryOfTheDay = summaryOfTheDay;
	}

	public String getTemperatureMin()
	{
		return temperatureMin;
	}

	public void setTemperatureMin(String temperatureMin)
	{
		this.temperatureMin = temperatureMin;
	}

	public String getTemperatureMax()
	{
		return temperatureMax;
	}

	public void setTemperatureMax(String temperatureMax)
	{
		this.temperatureMax = temperatureMax;
	}


	// Parcelling part, since our data model is a customized class so if we wanna retain this object when user
	// rotates screen, we must implements Parcelable interface
	public WeeklyWeatherDataModel(Parcel in)
	{
		String[] data = new String[3];
		in.readStringArray(data);
		this.summaryOfTheDay = data[0];
		this.temperatureMin = data[1];
		this.temperatureMax = data[2];
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
				this.summaryOfTheDay, this.temperatureMin, this.temperatureMax
		});
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
	{

		@Override
		public WeeklyWeatherDataModel createFromParcel(Parcel source)
		{
			return new WeeklyWeatherDataModel(source);
		}

		@Override
		public WeeklyWeatherDataModel[] newArray(int size)
		{
			return new WeeklyWeatherDataModel[size];
		}
	};
}