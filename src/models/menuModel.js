const mongoose = require("mongoose");

const menuSchema = new mongoose.Schema({
  nom: {
    type: String,
    required: true,
  },
  description: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
  idRestaurant: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "Restaurant",
    required: true,
  },
  imageUrl: {
    public_id: {
      type: String,
    //  required: true,
    },
    url: {
      type: String,
    //  required: true,
    },
  },
});

module.exports = mongoose.model("Menu", menuSchema);