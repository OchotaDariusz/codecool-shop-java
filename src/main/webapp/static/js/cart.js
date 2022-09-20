const addToCartButtons = document.querySelectorAll(".add-to-cart");
const numberOfProducts = document.querySelector("#number-of-products");

addToCartButtons.forEach(addToCartButton => {
    addToCartButton.addEventListener("click", event => {
        console.log(event.target.dataset);
        let productId = event.target.dataset.productId;
        addProductToCart(productId).then(() => {
            numberOfProducts.innerText = Number(numberOfProducts.innerText) + 1;
        })
    })
})

async function addProductToCart(productId) {
    await fetch("/api/cart/add", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"product_id": productId})
    });
}