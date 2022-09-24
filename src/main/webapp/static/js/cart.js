const removeFromCartButtons = document.querySelectorAll(".remove-product");
const increaseProductQuantityButtons = document.querySelectorAll(".increase-quantity");
const decreaseProductQuantityButtons = document.querySelectorAll(".decrease-quantity");

const removeProductFromCart = async productId => {
    await fetch("/api/cart/remove", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"product_id": productId})
    });
}

const increaseProductAmount = async productId => {
    await fetch("/api/cart/increase", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"product_id": productId})
    });
}

const decreaseProductAmount = async productId => {
    await fetch("/api/cart/decrease", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"product_id": productId})
    });
}

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

increaseProductQuantityButtons.forEach(increaseProductQuantityButton => {
    increaseProductQuantityButton.addEventListener("click", event => {
        let productId = event.target.dataset.productId;
        increaseProductAmount(productId).then(() => {
            location.reload();
        });
    });
});

decreaseProductQuantityButtons.forEach(button => {
    button.addEventListener("click", event => {
        let productId = event.target.dataset.productId;
        decreaseProductAmount(productId).then(() => {
            location.reload();
        });
    });
});
