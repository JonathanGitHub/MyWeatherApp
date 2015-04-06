# MyWeatherApp

Demo video link: https://www.youtube.com/watch?v=gBhzMrHUg-U

Your task is to build a simple 2 screen app that will show weekly forecasts for a fixed set of cities using a free API.
Screen 1 (Cities):
This screen displays a list of cities. To keep things simple, use the following 5 cities:
New York London
Los Angeles Paris
Tokyo
Screen 2 (City Forecast):
This screen displays the weather over the next week for the chosen city from screen 1. Display the following data for the chosen city:
Current temperature
Summary for the week, e.g. "No precipitation throughout the week, with temperatures peaking at 70Â° on Saturday."
For each of the next 7 days, the min and max temperature, and summary (e.g. "Mostly cloudy throughout the day.")
API Usage
For this assignment, use the free API from https://developer.forecast.io/
You must register and get an API key for access. Once registered, test out the below call which your app will use:
https://api.forecast.io/forecast/<APIKEY>/<LATITUDE>,<LONGITUDE>
Notes
You should only use the Android platform libraries. Do not use any external, 3rd party libraries, including any wrappers for forecast.io calls.
You may well have some ideas that, due to lack of time or other constraints, you choose to not implement. Please include these in comments in your source code.
Make sensible decisions about what you are displaying in terms of usability. Feel free to include comments in your code to indicate where you've made decisions to improve the user experience.
