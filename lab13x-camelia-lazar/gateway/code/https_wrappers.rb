# frozen_string_literal: true

require 'net/http'
require 'uri'

def https_get(url)
  uri = URI(url)

  Net::HTTP.start(uri.host, uri.port, use_ssl: uri.scheme == 'https') do |http|
    net_request = Net::HTTP::Get.new uri
    net_response = http.request net_request # Net::HTTPResponse object
    status net_response.code
    body net_response.body
  end
end

def https_post(url, post_body = nil)
  uri = URI(url)

  Net::HTTP.start(uri.host, uri.port, use_ssl: uri.scheme == 'https') do |http|
    net_request = Net::HTTP::Post.new uri
    net_request.body = post_body unless post_body.nil?
    net_request['Content-Type'] = 'application/json'
    net_response = http.request net_request # Net::HTTPResponse object
    status net_response.code
    body net_response.body
  end
end

def https_delete(url, delete_body = nil)
  uri = URI(url)

  Net::HTTP.start(uri.host, uri.port, use_ssl: uri.scheme == 'https') do |http|
    net_request = Net::HTTP::Delete.new uri
    net_request.body = delete_body unless delete_body.nil?
    net_request['Content-Type'] = 'application/json'
    net_response = http.request net_request # Net::HTTPResponse object
    net_response['Access-Control-Allow-Origin'] = '*'
    status net_response.code
    body net_response.body
  end
end

def https_put(url, put_body = nil)
  uri = URI(url)

  Net::HTTP.start(uri.host, uri.port, use_ssl: uri.scheme == 'https') do |http|
    net_request = Net::HTTP::Put.new uri
    net_request.body = put_body unless put_body.nil?
    net_request['Content-Type'] = 'application/json'
    net_response = http.request net_request # Net::HTTPResponse object
    status net_response.code
    body net_response.body
  end
end
