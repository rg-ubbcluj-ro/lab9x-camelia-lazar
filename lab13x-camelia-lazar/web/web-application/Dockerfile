FROM node:14.17 AS builder

WORKDIR /code
COPY . /code
#COPY package.json /code/package.json
RUN npm install
RUN npm install -g @angular/cli
ENV PATH="./node_modules/.bin:$PATH"

#COPY package.json /code/package.json
#COPY angular.json /code/angular.json

#

EXPOSE 4200

# RUN ng build --prod

CMD npm start
