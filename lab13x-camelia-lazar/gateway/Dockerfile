FROM ruby:2.7.3

WORKDIR /code
COPY . /code

RUN bundle install

EXPOSE 4567

CMD ["ruby", "code/gateway.rb", "-o", "0.0.0.0"]