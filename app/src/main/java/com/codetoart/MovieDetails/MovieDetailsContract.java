package com.codetoart.MovieDetails;

import com.codetoart.models.AllImages;
import com.codetoart.models.MovieData;

import java.util.ArrayList;

/**
 * Created by chetan_g on 23/11/17.
 */

interface MovieDetailsContract {
     void showData(MovieData locations);

     void showViewPager(ArrayList<String> images);
}
