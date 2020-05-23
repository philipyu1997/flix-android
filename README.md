# Flix

## Table of Contents
1. [Overview](#Overview)
2. [Product Specs](#Product-Specs)
3. [App Walkthrough](#App-Walkthrough)
4. [APIs Used](#APIs-Used)
5. [Open-Source libraries used](#Open-Source-libraries-used)
6. [Credits](#Credits)

## Overview
### Description

Flix is a read-only movie browsing app - similar to Fandango and Rotten Tomatoes - that lets a user view and scroll through a list of movies.

## Product Specs
### User Stories

- [x] User shall be able to view a list of movies (title, poster image, and overview) currently playing in theaters from [The Movie Database API](https://developers.themoviedb.org/4/getting-started/authorization).
- [x] Views shall be responsive for both landscape/portrait mode.
   - [x] In portrait mode, the poster image, title, and movie overview shall be shown.
   - [x] In landscape mode, the rotated alternate layout shall use the backdrop image instead and show the title and movie overview to the right of it.
- [x] A nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image shall be displayed during loading.
- [x] For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image shall be displayed. Otherwise, a poster image, the movie title, and overview is listed.
- [x] Details of movie (ratings using RatingBar, popularity, and synopsis) in shall be in a separate activity.
- [x] Video posts shall be allowed to be played in full-screen using the YouTubePlayerView.
- [x] Trailers for popular movies shall be played automatically when the movie is selected.
  - [x] When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video shall be played immediately.
  - [x] Less popular videos shall rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- [x] A play icon overlay to popular movies shall indicate that the movie can be played.
- [x] The popular ButterKnife annotation library shall be applied, to reduce view boilerplate.
- [x] A rounded corners for the images shall be added, using the Glide transformations.

## App Walkthrough

Here's a GIF of how the app works:

<img src="ADD_GIF_LINK" width=250><br>

## APIs Used

- [The Movie Database API](https://developers.themoviedb.org/4/getting-started/authorization) - Provides movie, TV show or actor images and/or data in your application.

## Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing.
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids.

## Credits

>This is a companion project to CodePath's Professional Android Course, check out the full course at [www.codepath.org](https://codepath.org/).
