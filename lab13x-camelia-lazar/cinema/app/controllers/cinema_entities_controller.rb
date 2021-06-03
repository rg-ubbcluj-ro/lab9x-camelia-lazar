class CinemaEntitiesController <ApplicationController
  def add
    params[:cinema].delete('identifier')
    newCinema = params[:cinema]
    CinemaEntity.insert(newCinema)
  end

  def remove
    id = params[:identifier]
    CinemaEntity.delete(id)
  end

  def all
    cinemas = CinemaEntity.all
    render json: cinemas
  end
end