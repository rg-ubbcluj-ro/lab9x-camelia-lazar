class ClientEntity <ApplicationRecord
  self.table_name = 'client'
  attr_readonly :identifier, :firstname, :lastname, :email, :age
end