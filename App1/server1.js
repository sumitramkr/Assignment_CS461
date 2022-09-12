const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");

const app = express();

app.set("view engine", "ejs");

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));

mongoose.connect(
    "mongodb+srv://sumitramkr:Sumi123@sumi1.joel8sy.mongodb.net/?retryWrites=true&w=majority",
    {
      useNewUrlParser: true,
    }
);

const customerSchema = new mongoose.Schema({
    Customer_ID: String,
    Customer_First_Name: String,
    Customer_Last_Name: String,
    Customer_Mobile: Number,
    Customer_Email: String
  });

const Customer = mongoose.model("Customer", customerSchema);

const dummyCustomer = new Customer({
    Customer_ID: "1",
    Customer_First_Name: "Dummy",
    Customer_Last_Name: "Dummy",
    Customer_Mobile: 0000000000,
    Customer_Email: "dummy@dummy.com"
});

app.get("/", function (req, res) {
    Customer.find({}, function (err, results) {
      if (results.length === 0) {
        Customer.insertMany(dummyCustomer, function (err) {
          if (err) {
            console.log(err);
          } else {
            console.log("Successfully added dummyCustomer in DataBase");
          }
        });
        res.render("create");
      } else {
        res.render("create");
      }
    });
});

app.get("/data", function (req, res) {
  Customer.find({}, function (err, results) {
    if (err) {
      console.log(err);
    } else {
    res.json(results);
    }
  });
     
});

app.post("/", function (req, res) {
    //redirect to different routes after post request from here rather than making new post requests app.post
    const Customers_ID = req.body.Customer_ID;
    const Customers_First_Name = req.body.Customer_First_Name;
    const Customers_Last_Name = req.body.Customer_Last_Name;
    const Customers_Mobile = req.body.Customer_Mobile;
    const Customers_Email = req.body.Customer_Email;
  
    const addedCustomer = new Customer({
        Customer_ID: Customers_ID                                                                                       ,
        Customer_First_Name: Customers_First_Name,
        Customer_Last_Name: Customers_Last_Name,
        Customer_Mobile: Customers_Mobile,
        Customer_Email: Customers_Email
    });

    addedCustomer.save();
    res.redirect("/");
});

app.listen(3001, function () {
    console.log("Server started successfully at port:3001!");
  });