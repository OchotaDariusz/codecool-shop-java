const mainContainer = document.querySelector("#container")

const homeUrl = '/';
const orderUrl = '/order';
const removeFiltersLink = document.querySelector("#removeFilters");
let category = null;
let supplier = 0;
let baseUrl = `/?category=${category}`;
let Manu = false;


function displayWebsite(){
    console.log("JS script initialized")
    initPage();
}


function initPage() {
    addEventListenersToCategoryLinks();
    addEventListenerRemoveFilters();
    //addEventListenersToSupplierLinks();
}


function addEventListenersToCategoryLinks(){
    const clickableLinks = document.querySelectorAll(".dropdown-item");
    clickableLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            categoryNum = link.getAttribute("data-category");
            baseUrl = `/?category=${categoryNum}`;
            category = link.innerHTML;
            location.href = baseUrl;
            changeCategoryName(category);
        })
    })
}

function changeCategoryName(newName){
    const categoryName = document.querySelector("#category-name");
    categoryName.innerHTML = newName;
}

function addEventListenerRemoveFilters(){
    removeFiltersLink.addEventListener('click', (e) => {
        e.preventDefault();
        location.href = "/";
    })
}

displayWebsite()
