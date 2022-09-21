const cart_container = document.querySelector('#cart-container')
const total_price = document.querySelector('#total-price')
const num_of_items = document.querySelector('#num-of-items')

function displayWebsite(){
    console.log("JS script initialized")
    fetchDataFromAPI();
}



function fetchDataFromAPI() {
    fetch("/apiCurrentCart").then(function (response) {
        if (response.ok) {
            return response.json();
        } else {
            return Promise.reject(response);
        }
    }).then(function (data) {
        displayInfoInTable(data);
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
    });
}

function displayInfoInTable(data) {
    console.log(data)
}

displayWebsite()
