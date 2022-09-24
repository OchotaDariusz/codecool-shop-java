const addToCartButtons = document.querySelectorAll(".add-to-cart");
const numberOfProducts = document.querySelector("#number-of-products");
const removeFiltersLink = document.querySelector("#removeFilters");
let category = null;
let supplier = 0;
let baseUrl = `/?category=${category}`;

function displayWebsite() {
    console.log("JS script initialized")
    initPage();
}

function initPage() {
    addEventListenersToCategoryLinks();
    addEventListenersToSupplierLinks();
    addEventListenerRemoveFilters();
}

function addEventListenersToCategoryLinks() {
    const clickableLinks = document.querySelectorAll(".category");
    clickableLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            let categoryNum = link.getAttribute("data-category");
            baseUrl = `/?category=${categoryNum}`;
            category = link.innerHTML;
            location.href = baseUrl;
            changeCategoryName(category);
        })
    })
}

function addEventListenersToSupplierLinks() {
    const clickableLinks = document.querySelectorAll(".supplier");
    clickableLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            let categoryNum = link.getAttribute("data-category");
            baseUrl = `/?supplier=${categoryNum}`;
            category = link.innerHTML;
            location.href = baseUrl;
            changeCategoryName(category);
        })
    })
}

function changeCategoryName(newName) {
    const categoryName = document.querySelector("#category-name");
    categoryName.innerHTML = newName;
}

function addEventListenerRemoveFilters() {
    removeFiltersLink.addEventListener('click', (e) => {
        e.preventDefault();
        location.href = "/";
    })
}

addToCartButtons.forEach(addToCartButton => {
    addToCartButton.addEventListener("click", event => {
        let productId = event.target.dataset.productId;
        addProductToCart(productId).then(() => {
            numberOfProducts.innerText = Number(numberOfProducts.innerText) + 1;
            document.querySelector(".product-added-alert").innerHTML =
                "<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
                "    <strong>Success!</strong> Product has been added to your cart.\n" +
                "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                "        <span aria-hidden=\"true\">&times;</span>\n" +
                "    </button>\n" +
                "</div>";
        });
    });
});

async function addProductToCart(productId) {
    await fetch("/api/cart/add", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"product_id": productId})
    });
}

displayWebsite()
