var request = require("request");
var mysql = require('mysql');

var con = mysql.createConnection({
	host: "pic-food.com",
	user: "myuser",
	password: "YaleSE2018",
	database: "picfood"
});

var businesses = [];

function getFromYelp() {
	for (var offset = 0; offset < 20; offset++) {
		var url = "https://api.yelp.com/v3/businesses/search?location=new_haven&limit=50&offset=" + offset;
		request({
			url: url,
			json: true,
			headers : {
				"Authorization" : "Bearer R6yfiSN8ule23dswC3JawoxJB_3no9DEXUuXvdCpwr_u6iOUzVrknG2rpwcbdF-xQKr9grWhCLs6PrjArt6JGvcZO2h6PyOipjt5LY25UxUPTflE7qhrjxZpohl2WnYx"
			}
		}, function (error, response, body) {
			if (!error && response.statusCode === 200)
				businesses = businesses.concat(body.businesses);
		})
	}
	return new Promise(resolve => {
		setTimeout(() => {
			resolve('resolved');
		}, 5000);
	});
}

async function main() {
	var result = await getFromYelp();
	con.connect(function(err) {
		if (err) throw err;
		for (var b in businesses) {
			var address = "";
			var category = "";
			for (var addr in businesses[b].location.display_address)
				address += businesses[b].location.display_address[addr] + ", "
			address = address.trim().slice(0, -1);
			for (var cate in businesses[b].categories)
				category += businesses[b].categories[cate].title + ", "
			category = category.trim().slice(0, -1);
			var sql= "INSERT INTO restaurant (restaurant_id,address,avatar,avg_rate,rate_count,category,latitude,longitude,name,tele_number) VALUES ('" + businesses[b].id + "', '" + address + "', '" + businesses[b].image_url + "', '" + businesses[b].rating + "', '" + businesses[b].review_count + "', '" + category + "', '" + businesses[b].coordinates.latitude + "', '" + businesses[b].coordinates.longitude + "', '" + businesses[b].name + "', '" + businesses[b].display_phone + "');";
			try {
	  			con.query(sql, function (err, result) {
					if (!err) console.log("1 record inserted");
				});
			}
			catch(error) {
			}
		}
	});
}

main();