Rails.application.routes.draw do
  get 'api/cinemas', to: 'cinema_entities#all'
  post 'api/cinemas', to: 'cinema_entities#add'
  delete 'api/cinemas/:identifier', to: 'cinema_entities#remove'
end
