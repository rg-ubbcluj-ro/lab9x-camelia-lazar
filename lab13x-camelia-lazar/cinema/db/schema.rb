# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# This file is the source Rails uses to define your schema when running `bin/rails
# db:schema:load`. When creating a new database, `bin/rails db:schema:load` tends to
# be faster and is potentially less error prone than running all of your
# migrations from scratch. Old migrations may fail to apply correctly if those
# migrations use external dependencies or application code.
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 2021_06_02_213513) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "cinema", primary_key: "identifier", id: :serial, force: :cascade do |t|
    t.string "name", limit: 50
    t.string "address", limit: 50
  end

  create_table "client", primary_key: "identifier", id: :serial, force: :cascade do |t|
    t.string "firstname", limit: 50
    t.string "lastname", limit: 50
    t.string "email", limit: 50
    t.integer "age"
  end

  create_table "movie", primary_key: "identifier", id: :bigint, default: nil, force: :cascade do |t|
    t.integer "duration"
    t.string "genre", limit: 255
    t.string "name", limit: 255
    t.integer "publishingyear"
    t.string "soundtrack", limit: 255
  end

  create_table "ticket", primary_key: "identifier", id: :bigint, default: nil, force: :cascade do |t|
    t.bigint "clientid"
    t.bigint "movieid"
    t.float "price"
  end

end
