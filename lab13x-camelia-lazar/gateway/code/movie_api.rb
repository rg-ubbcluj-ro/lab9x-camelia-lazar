# frozen_string_literal: true

# noinspection RubyResolve
require 'sinatra'
require_relative 'https_wrappers'

MOVIE_API_PREFIX = '/api/movies'
MOVIE_CALL_PREFIX = 'http://movie-service:8083/movie/api/movies'
# Get all movies
get MOVIE_API_PREFIX do
  https_get(MOVIE_CALL_PREFIX)
end

# Add a movie
post MOVIE_API_PREFIX do
  request.body.rewind
  https_post(MOVIE_CALL_PREFIX, request.body.read)
end

# Delete a movie
delete "#{MOVIE_API_PREFIX}/:food_id" do
  https_delete("#{MOVIE_CALL_PREFIX}/#{params['food_id']}")
end

# Update a movie
put MOVIE_API_PREFIX do
  request.body.rewind
  https_put(MOVIE_CALL_PREFIX, request.body.read)
end