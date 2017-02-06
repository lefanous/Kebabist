var requestText;

$(document).ready(function() {
  $("#search").click(function() {
  requestText = $("#query").val() + " " + $("#queryLocation").val()
  var request = {
  	query: requestText
  };

  service = new google.maps.places.PlacesService(map);
  service.textSearch(request, callback);

  });
});

function startPhoneNumberSearch(){
	if (dbentries == 0){
		return;
	}
	var request = {
 	 placeId: dbentries[dbentries.length-1].realId
	};

service = new google.maps.places.PlacesService(map);
service.getDetails(request, callbacknumber);
}


var resultsFound = 0;

function callbacknumber(place, status) {
  document.getElementById("totalResults").innerHTML = "Results found: " + resultsFound;
  if (status == google.maps.places.PlacesServiceStatus.OK) {
  	var current = dbentries[dbentries.length-1];
   	var phonenumber = place.formatted_phone_number.replace(" ", "").replace(" ", "").replace(" ", "");
    appendText("INSERT INTO Kebabsteder VALUES (ID, '"+current.name+"', '"+current.lat+"', '"+current.lon+"', '+45"+phonenumber+"');<br>");
    dbentries.splice(dbentries.length-1,dbentries.length);
  }
 startPhoneNumberSearch();
}

var service;
var map;

function initMap(){

	map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15
    });
    
      var input = document.getElementById('queryLocation');
  var options = {
    };
    
    autocomplete = new google.maps.places.Autocomplete(input, options);
}

function callback(results, status){
	if (status == google.maps.places.PlacesServiceStatus.OK) {
    for (var i = 0; i < results.length; i++) {
      resultsFound++;
      var place = results[i];
      var latitude = place.geometry.location.lat();
      var longitude = place.geometry.location.lng();
      var name = place.name;
      var dbentry = {realId:place.place_id, lat:latitude, lon:longitude, name:name};
      dbentries[dbentries.length] = dbentry;
    }
    startPhoneNumberSearch();
  }
}

var dbentries = [];

function appendTotalResults(text){
	$("totalResults").html(resultsFound)
}

function appendText(text){
	$("#result").html($("#result").html()+text);
}

function CopyToClipboard() {
if (document.selection) { 
    var range = document.body.createTextRange();
    range.moveToElementText(document.getElementById('result'));
    range.select().createTextRange();
    document.execCommand("Copy"); 

} else if (window.getSelection) {
    var range = document.createRange();
     range.selectNode(document.getElementById('result'));
     window.getSelection().addRange(range);
     document.execCommand("Copy");
     alert("Results copied") 
}}

function Reset() {
	window.location = '';
}