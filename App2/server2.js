const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");

const app = express();

app.set("view engine", "ejs");

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));

mongoose.connect("mongodb+srv://sumitramkr:Sumi123@sumi1.joel8sy.mongodb.net/?retryWrites=true&w=majority", {
  useNewUrlParser: true,
});

const customerSchema = new mongoose.Schema({
  Customer_ID: String,
  Customer_First_Name: String,
  Customer_Last_Name: String,
  Customer_Mobile: Number,
  Customer_Email: String,
});

const Customer = mongoose.model("Customer", customerSchema);

app.get("/", function (req, res) {
  Customer.find({}, function (err, results) {
    if (results.length === 0) {
      res.render("emptyPage");
    } else {
      res.render("show", { newCustomers: results });
    }
  });
});

app.listen(5001, function () {
  console.log("Server started successfully at port:5001!");
});
