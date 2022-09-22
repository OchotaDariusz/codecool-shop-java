const form = document.querySelector("form");

form.addEventListener("submit", event => {
    event.preventDefault();

    const data = {
        "firstName": form.firstName.value,
        "lastName": form.lastName.value,
        "username": form.username.value,
        "email": form.email.value,
        "address": form.address.value,
        "address2": form.address2.value,
        "country": form.country.value,
        "city": form.city.value,
        "zip": form.zip.value,
        "ccname": form.ccname.value,
        "ccnumber": form.ccnumber.value,
        "ccexpiration": form.ccexpiration.value,
        "cccvv": form.cccvv.value
    };

    fetch("/payment", { method: "POST", body: JSON.stringify(data) }).then(() => {
        displaySuccessInfo();

        // redirect after 3s to main page
        setTimeout(() => {
            location.href = "/";
        }, 3000);
    });
});

function displaySuccessInfo() {
    document.querySelector(".payment-complete").innerHTML =
        "<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
        "    <strong>Thank you!</strong> Redirecting to main page...\n" +
        "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
        "        <span aria-hidden=\"true\">&times;</span>\n" +
        "    </button>\n" +
        "</div>";
}