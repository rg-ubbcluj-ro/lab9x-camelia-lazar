class CinemaEntity <ApplicationRecord
  self.table_name = 'cinema'
  attr_readonly :identifier, :name, :address
end