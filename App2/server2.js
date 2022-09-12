const express = require("express");
const bodyParser = require("body-parser");
const fetch = require('node-fetch');

const app = express();

app.set("view engine", "ejs");

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));

async function getUser() {
  try {
    var startTime = (new Date()).getTime();
    const response = await fetch('http://localhost:3001/data');
    if (!response.ok) {
      throw new Error(`Error! status: ${response.status}`);
    }

    const result = await response.json();
    var endTime = (new Date()).getTime();
    console.log("Took " + (endTime - startTime) + " ms");
    return result;

  } catch (err) {
    console.log(err);
  }
}

getUser();

app.get("/", function (req, res) {
  getUser().then(result => { 
    if (result.length === 0) {
      res.render("emptyPage");
    } else {
      res.status(200).json(result);
    }
  });
});

app.listen(5001, function () {
  console.log("Server started successfully at port: 5001!");
});
