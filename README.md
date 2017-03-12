# Week 1 Project - Flickster

Flickster is an android app that allows browsing a read-only movie listing using the IMDB Movie Database API.  It also allows for playing of an associated movie trailer from YouTube.

Submitted by: Dave Friedman

Time spent: 24 hours spent in total

## User Stories

The following **required** functionality is completed:

* [y] User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API. 
* [y] Lists are fully optimized for performance using the ViewHolder pattern to cache view lookups within the adapter.
* [y] Views are responsive for both landscape/portrait mode.
* [y] In portrait mode, the poster image, title, and movie overview are shown. In landscape mode, the rotated layout uses the backdrop image instead and shows the title and movie overview to the right of it.

The following **optional** features are implemented:

* [y] Display a nice default placeholder graphic for each image during loading.
* [y] Improved the user interface through styling and coloring.  (rounded image borders, static background image)
* [y] Exposed details of movies (ratings using RatingBar, Popularity, and ReleaseDate) in the same list.
* [y] Allowed video trailers to be played in full-screen using the YouTubePlayerView.  (All videos are played immediately after clicking anywhere on a movie row.)

The following **additional** features are implemented:

* [y] List anything else that you can get done to improve the app functionality!
      ** -Popularity displayed in a horizontal progress bar with RTL orientation.
      ** -Added rounded corners for all images using the Picasso transformations.

* Future version may:
      ** -Fire off on-device Fandango ticket purchase activity.
      ** -Order the movies by popularity (default), date, star rating, alphabetical.
      ** -Allow the user to select from and order and list or video trailers to choose which trailer(s) will be played for a movie.

## Video Walkthrough 

Here's a walkthrough of implemented user stories:  (http://i.imgur.com/Wb2zKn5.gifv)

<img src='http://i.imgur.com/Wb2zKn5.gifv' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.  [MORE FUN.]
-The Genymotion emulator seemed not to like to play YouTube videos for more than 1 second at a time until I replace the parent 
ConstraintsLayout with a RelativeLayout.

## License

    Copyright 2017 by Dave Friedman

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
