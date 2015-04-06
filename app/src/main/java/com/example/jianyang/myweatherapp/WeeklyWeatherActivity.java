package com.example.jianyang.myweatherapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import fragments.WeeklyWeatherFragment;

/**
 * Created by jianyang on 4/3/15.
 */
public class WeeklyWeatherActivity extends FragmentActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weekly_weather);
		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction()
									   .add(R.id.container, new WeeklyWeatherFragment())
									   .commit();
		}
	}
}
