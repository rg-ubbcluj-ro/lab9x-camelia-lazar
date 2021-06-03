# frozen_string_literal: true

# noinspection RubyResolve
require 'sinatra'
require_relative 'https_wrappers'

CINEMA_API_PREFIX = '/api/cinemas'
CINEMA_CALL_PREFIX = 'http://cinema-service:8081/api/cinemas'

# Get all people

get CINEMA_API_PREFIX do
  https_get(CINEMA_CALL_PREFIX)
end

# # Filter by budget
# get "#{CINEMA_API_PREFIX}/budget-filter/:min_budget" do
#   https_get("#{CINEMA_CALL_PREFIX}/budget-filter/#{params['min_budget']}")
# end
#
# # Get pets of owner
# get "#{CINEMA_API_PREFIX}/:person_id/pets" do
#   https_get("#{CINEMA_CALL_PREFIX}/#{params['person_id']}/pets")
# end

# Add a cinema
post CINEMA_API_PREFIX do
  request.body.rewind
  https_post(CINEMA_CALL_PREFIX, request.body.read)
end

# # Buy pet
# post "#{CINEMA_API_PREFIX}/pets/:person_id/:pet_id" do
#   request.body.rewind
#   https_post("#{CINEMA_CALL_PREFIX}/pets/#{params['person_id']}/#{params['pet_id']}", nil)
# end

# Delete a cinema
delete "#{CINEMA_API_PREFIX}/:id" do
  https_delete("#{CINEMA_CALL_PREFIX}/#{params['id']}")
end

# # Refund pet
# delete "#{CINEMA_API_PREFIX}/pets/:person_id/:pet_id" do
#   https_delete("#{CINEMA_CALL_PREFIX}/pets/#{params['person_id']}/#{params['pet_id']}")
# end

# Update a cinema
put CINEMA_API_PREFIX do
  request.body.rewind
  https_put(CINEMA_CALL_PREFIX, request.body.read)
end
