class ClientEntitiesController <ApplicationController
  def add
    params[:client].delete('identifier')
    newClient = params[:client]
    ClientEntity.insert(newClient)
  end

  def remove
    id = params[:identifier]
    ClientEntity.delete(id)
  end

  def all
    clients = ClientEntity.all
    render json: clients
  end
end