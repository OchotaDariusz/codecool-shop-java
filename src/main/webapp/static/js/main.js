const mainContainer = document.querySelector("#container")

const homeUrl = '/';
const orderUrl = '/order';
const jobsUrl = '/apiJobs';
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
    //addEventListenersToSupplierLinks();
}


function addEventListenersToCategoryLinks(){
    const clickableLinks = document.querySelectorAll(".dropdown-item");
    clickableLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            categoryNum = link.getAttribute("data-category");
            baseUrl = `/?category=${categoryNum}`;
            console.log(baseUrl);
            category = link.innerHTML;
            console.log(category);
            location.href = baseUrl;
            changeCategoryName(category);
        })
    })
}

function changeCategoryName(newName){
    const categoryName = document.querySelector("#category-name");
    categoryName.innerHTML = newName;
}

displayWebsite()
