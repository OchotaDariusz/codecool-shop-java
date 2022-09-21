const addToCartButtons = document.querySelectorAll(".add-to-cart");
const numberOfProducts = document.querySelector("#number-of-products");
const removeFromCartButtons = document.querySelectorAll(".remove-product");
const increaseProductQuantityButtons = document.querySelectorAll(".increase-quantity");
const decreaseProductQuantityButtons = document.querySelectorAll(".decrease-quantity");

if (addToCartButtons && numberOfProducts) {
    addToCartButtons.forEach(addToCartButton => {
        addToCartButton.addEventListener("click", event => {
            let productId = event.target.dataset.productId;
            addProductToCart(productId).then(() => {
                numberOfProducts.innerText = Number(numberOfProducts.innerText) + 1;
            })
        })
    });
}

async function addProductToCart(productId) {
    await fetch("/api/cart/add", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"product_id": productId})
    });
}

if (removeFromCartButtons) {
    removeFromCartButtons.forEach(removeFromCartButton => {
        removeFromCartButton.addEventListener("click", event => {
            let productId;
            if (Array.from(event.target.dataset).length !== 0) {
                productId = event.target.dataset.productId;
                console.log(event.target.dataset.length);
            } else {
                productId = event.target.parentElement.dataset.productId;
            }
            removeProductFromCart(productId).then(() => {
                location.reload();
            });
        });
    });
}

async function removeProductFromCart(productId) {
    await fetch("/api/cart/remove", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"product_id": productId})
    });
}

if (increaseProductQuantityButtons && decreaseProductQuantityButtons) {
    increaseProductQuantityButtons.forEach(increaseProductQuantityButton => {
        increaseProductQuantityButton.addEventListener("click", event => {
            let productId = event.target.dataset.productId;
            increaseProductAmount(productId).then(() => {
                location.reload();
            });
        });
    });

    decreaseProductQuantityButtons.forEach(decreaseProductQuantityButton => {
        decreaseProductQuantityButton.addEventListener("click", event => {
            let productId = event.target.dataset.productId;
            decreaseProductAmount(productId).then(() => {
                location.reload();
            });
        });
    });
}

async function increaseProductAmount(productId) {
    await fetch("/api/cart/increase", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"product_id": productId})
    });
}

async function decreaseProductAmount(productId) {
    await fetch("/api/cart/decrease", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"product_id": productId})
    });
}