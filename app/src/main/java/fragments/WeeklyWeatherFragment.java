package fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jianyang.myweatherapp.R;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import adapters.WeeklyWeatherAdapter;
import models.MyWeatherDataModel;
import models.WeeklyWeatherDataModel;

/**
 * Created by jianyang on 4/3/15.
 */
public class WeeklyWeatherFragment extends Fragment
{

	private ListView mListView;
	private ProgressBar mProgressBar;

	private TextView currentTemperature;
	private TextView currentSummary;
	private TextView weeklySummary;
	private MyWeatherDataModel myWeatherDataModel = new MyWeatherDataModel();;

	private WeeklyWeatherAdapter mWeeklyWeatherAdapter;


	public WeeklyWeatherFragment()
	{
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		Intent intent = getActivity().getIntent();
		if (intent != null)
		{
			String url = intent.getStringExtra("url");
			// Do our Async HTTP call so that UI thread won't be blocked
			new MyAsyncTask().execute(url);
			System.out.println("url: " + url);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_weekly, container, false);
		mListView = (ListView)rootView.findViewById(R.id.listview);
		currentSummary = (TextView)rootView.findViewById(R.id.current_summary);
		currentTemperature = (TextView)rootView.findViewById(R.id.current_temperature);
		weeklySummary = (TextView)rootView.findViewById(R.id.weeklySummary);
		mProgressBar = (ProgressBar)rootView.findViewById(R.id.progresBar);

		// Show mProgressBar when fetching data
		mProgressBar.setVisibility(View.VISIBLE);

		// If this comes from a rotation of screen
		if (savedInstanceState != null)
		{
			MyWeatherDataModel mySavedWeatherDataModel = savedInstanceState.getParcelable("dataKey");
			populateJsonData(mySavedWeatherDataModel);
		}
		return rootView;
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		// Save the data when configuration changes
		outState.putParcelable("dataKey", myWeatherDataModel);
	}

	private class MyAsyncTask extends AsyncTask<String, String, String>
	{

		@Override
		protected String doInBackground(String... params)
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;
			String responseString = null;
			try
			{
				response = httpclient.execute(new HttpGet(params[0]));
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK)
				{
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					response.getEntity().writeTo(out);
					responseString = out.toString();
					out.close();
				}
				else
				{
					//Closes the connection.
					response.getEntity().getContent().close();
					throw new IOException(statusLine.getReasonPhrase());
				}
			}
			catch (IOException e) {
				//TODO Handle problems..
			}
			return responseString;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			mListView.setAdapter(null);
			parseAndPopulateJsonData(result);
		}
	}

	private void populateJsonData(MyWeatherDataModel myWeatherDataModel)
	{
		// Remove mProgressBar after network callback
		mProgressBar.setVisibility(View.INVISIBLE);

		currentSummary.setText(myWeatherDataModel.getCurrentSummary());
		currentTemperature.setText("Current: " + myWeatherDataModel.getCurrentTemperature() + " °F");
		weeklySummary.setText(myWeatherDataModel.getSummaryOfTheWeek());

		mWeeklyWeatherAdapter = new WeeklyWeatherAdapter(getActivity(), myWeatherDataModel);
		mListView.setAdapter(mWeeklyWeatherAdapter);
	}
	private void parseAndPopulateJsonData(String result)
	{
		//Parse json and populate UI elements here
		try
		{
			// Remove mProgressBar after network callback
			mProgressBar.setVisibility(View.INVISIBLE);

			JSONObject myWeatherJson = new JSONObject(result);
			ArrayList<WeeklyWeatherDataModel> weeklyWeatherDataModels = new ArrayList<>();

			if (myWeatherJson.getJSONObject("currently") != null)
			{
				JSONObject currentWeatherJson = myWeatherJson.getJSONObject("currently");
				myWeatherDataModel.setCurrentSummary(currentWeatherJson.getString("summary"));
				myWeatherDataModel.setCurrentTemperature(currentWeatherJson.getString("temperature"));

				currentSummary.setText(myWeatherDataModel.getCurrentSummary());
				currentTemperature.setText("Current: " + myWeatherDataModel.getCurrentTemperature() + " °F");
			}

			if (myWeatherJson.getJSONObject("daily") != null)
			{

				JSONObject dailyJson = myWeatherJson.getJSONObject("daily");

				myWeatherDataModel.setSummaryOfTheWeek(dailyJson.getString("summary"));
				weeklySummary.setText(myWeatherDataModel.getSummaryOfTheWeek());

				JSONArray weeklyJsonArray = dailyJson.getJSONArray("data");

				for (int i = 0; i < weeklyJsonArray.length(); i++)
				{
					WeeklyWeatherDataModel weeklyWeatherData = new WeeklyWeatherDataModel();
					JSONObject weeklyJson = weeklyJsonArray.getJSONObject(i);

					if (weeklyJson != null)
					{
						weeklyWeatherData.setSummaryOfTheDay(weeklyJson.getString("summary"));
						weeklyWeatherData.setTemperatureMax(weeklyJson.getString("temperatureMax"));
						weeklyWeatherData.setTemperatureMin(weeklyJson.getString("temperatureMin"));
					}

					weeklyWeatherDataModels.add(weeklyWeatherData);
				}

				myWeatherDataModel.setWeeklyWeatherDataArrayList(weeklyWeatherDataModels);
			}

			mWeeklyWeatherAdapter = new WeeklyWeatherAdapter(getActivity(), myWeatherDataModel);
			mListView.setAdapter(mWeeklyWeatherAdapter);

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
}

