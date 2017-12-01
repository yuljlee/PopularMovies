/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nanodegree.android.popularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the movie db servers.
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOIVE_POPULAR_URL =
            "http://api.themoviedb.org/3/movie/popular";
    private static final String MOIVE_TOPRATED_URL =
            "http://api.themoviedb.org/3/movie/top_rated";
            //"http://api.themoviedb.org/3/discover/movie";
    private static final String MOIVE_POSTER_URL =
            "http://image.tmdb.org/t/p/";

    //private static URL POP_URL = null;
    private static final String sortByPopularity = "popularity.desc";
    private static final String sortByTopRated = "vote_average.desc";
    /* Movie data base access key */
    private static final String apiKey = "8cb2843ff5acb91dcadb5a5f23c39d01";

    public static URL buildUrl(String locationQuery) {
        // COMPLETED (1) Fix this method to return the URL used to query Open Weather Map's API

        String movieUrl;
        if (locationQuery.equals("popular")) {
            movieUrl = MOIVE_POPULAR_URL;
        } else {
            movieUrl = MOIVE_TOPRATED_URL;
        }

        Uri builtUri = Uri.parse(movieUrl).buildUpon()
                .appendQueryParameter("api_key", apiKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //Log.v(TAG, "Built URI " + url);
        return url;
    }
/*
    public void NetworkUtils() {
        Uri builtUri = Uri.parse(MOIVE_POPULAR_URL).buildUpon()
                .appendQueryParameter("api_key", apiKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Log.v(TAG, "Built MOVIE ID URI " + url);
        POP_URL = url;
    }*/

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}