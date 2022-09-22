// const cart_container = document.querySelector('#cart-container')
// const total_price = document.querySelector('#total-price')
// const num_of_items = document.querySelector('#num-of-items')
//
// function displayWebsite() {
//     console.log("JS script initialized")
//     fetchDataFromAPI();
// }
//
//
// function fetchDataFromAPI() {
//     fetch("/apiCurrentCart").then(function (response) {
//         if (response.ok) {
//             return response.json();
//         } else {
//             return Promise.reject(response);
//         }
//     }).then(function (data) {
//         displayInfoInTable(data);
//     }).catch(function (err) {
//         console.warn('Something went wrong.', err);
//     });
// }
//
// function displayInfoInTable(data) {
//     allInfo = data.allitems;
//
//     //creating list
//     console.log(data)
//     const list = document.createElement("ul");
//     list.classList.add("list-group");
//     list.classList.add("mb-3")
//     cart_container.appendChild(list)
//     for (const info of allInfo) {
//
//         //create list items
//         const row = document.createElement("li");
//         row.classList.add("list-group-item")
//         row.classList.add("d-flex")
//         row.classList.add("justify-content-between")
//         row.classList.add("lh-condensed")
//         list.appendChild(row)
//         const div = document.createElement("div");
//
//         const productName = document.createElement("h6");
//         productName.classList.add("m-0")
//         div.appendChild(productName)
//
//         const description = document.createElement("small")
//         description.classList.add("text-muted")
//         div.appendChild(description)
//         list.appendChild(div)
//
//         const price = document.createElement("span")
//         price.classList.add("text-muted")
//         list.appendChild(price)
//
//         //populating the table with information
//         let product_name = allInfo.name
//         let product_description = allInfo.description
//         let product_price = allInfo.price
//
//         productName.textContent = product_name;
//         description.textContent = product_description;
//         price.textContent = product_price;
//     }
// }
//
// displayWebsite();