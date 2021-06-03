# frozen_string_literal: true

# noinspection RubyResolve
require 'sinatra'
require_relative 'https_wrappers'

CLIENT_API_PREFIX = '/api/clients'
CLIENT_CALL_PREFIX = 'http://client-service:8080/api/clients'

# Get all clients
get CLIENT_API_PREFIX do
  https_get(CLIENT_CALL_PREFIX)
end

# # Get toy of pet
# get "#{CLIENT_API_PREFIX}/:pet_id/toy" do
#   https_get("#{PET_CALL_PREFIX}/#{params['pet_id']}/toy")
# end

# Add a client
post CLIENT_API_PREFIX do
  request.body.rewind
  https_post(CLIENT_CALL_PREFIX, request.body.read)
end

# Delete a client
delete "#{CLIENT_API_PREFIX}/:id" do
  https_delete("#{CLIENT_CALL_PREFIX}/#{params['id']}")
end


# Update a client
put CLIENT_API_PREFIX do
  request.body.rewind
  https_put(CLIENT_CALL_PREFIX, request.body.read)
end
