Rails.application.routes.draw do
  get 'api/clients', to: 'client_entities#all'
  post 'api/clients', to: 'client_entities#add'
  delete 'api/clients/:identifier', to: 'client_entities#remove'
end
