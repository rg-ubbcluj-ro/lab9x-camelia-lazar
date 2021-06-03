FROM ruby:2.7.3

WORKDIR /code
COPY . /code

RUN bundle install

EXPOSE 3000


CMD ["ruby", "/code/bin/rails", "server", "-b", "0.0.0.0", "-p", "8080", "-e", "development"]