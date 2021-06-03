# frozen_string_literal: true

# noinspection RubyResolve
require 'sinatra'
require_relative 'https_wrappers'

TICKET_API_PREFIX = '/api/tickets'
TICKET_CALL_PREFIX = 'http://ticket-service:8082/ticket/api/tickets'

# Get all tickets
get TICKET_API_PREFIX do
  https_get(TICKET_CALL_PREFIX)
end

# Add a ticket
post TICKET_API_PREFIX do
  request.body.rewind
  https_post(TICKET_CALL_PREFIX, request.body.read)
end

# Delete a ticket
delete "#{TICKET_API_PREFIX}/:id" do
  https_delete("#{TICKET_CALL_PREFIX}/#{params['id']}")
end
