# frozen_string_literal: true

# noinspection RubyResolve
require 'sinatra'
require 'net/http'
require 'uri'
require_relative 'https_wrappers'
require_relative 'cinema_api'
require_relative 'client_api'
require_relative 'movie_api'
require_relative 'ticket_api'

# # # #
#
#  Cross Origin Configuration (CORS)
#
# # # #

configure do
  enable :cross_origin
end

before do
  response.headers['Access-Control-Allow-Origin'] = '*'
end

options '*' do
  response.headers['Allow'] = '*'
  response.headers['Access-Control-Allow-Headers'] = 'Authorization, Content-Type, Accept, X-User-Email, X-Auth-Token'
  response.headers['Access-Control-Allow-Methods'] = 'GET, PUT, POST, DELETE, OPTIONS'
  200
end
