function closeAlert() {
    var x = document.getElementsByClassName('alert');
    x[0].style.display = "none";
}

function showAndHide(id) {
    var x = document.getElementById(id);
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function adminSearch() {
    var list = document.getElementsByClassName("adminSearch");
    var moreButton = document.getElementById("searchFormMoreButton");

    for (var i = 0; i < list.length; i++) {
        if (list[i].style.display === "none") {
            list[i].style.display = "block";
            moreButton.innerText = 'MNIEJ';
        } else {
            list[i].style.display = "none";
            moreButton.innerText = 'WIĘCEJ';
        }
    }
}

function submit(page) {
    var form = document.getElementById('searchAlcoholForm');
    var numberPage = document.getElementById('sortTypeSelector').value;
    var sortBy = document.getElementById('numberPositionsSelector').value;
    form.action = form.action + page + '/' + sortBy + '/' + numberPage;
    form.submit();
}

function addToCompare(id, currentPage) {
    var form = document.getElementById('searchAlcoholForm');
    var numberAlcoholInPage = document.getElementById('sortTypeSelector').value;
    var sortBy = document.getElementById('numberPositionsSelector').value;
    form.action = form.action + currentPage++ + '/' + sortBy + '/' + numberAlcoholInPage + '/' + id;
    form.action = form.action.replace('search', 'addToCompare');
    form.submit();
}

function clearFormSearch() {
    var alcoholname = document.getElementById('manufacturerName');
    alcoholname.value = '';
    var alcoholType = document.getElementById('alcoholType');
    alcoholType.value = '';
    var alcoholName = document.getElementById('alcoholName');
    alcoholName.value = '';
    var amountOfAlcoholMin = document.getElementById('amountOfAlcoholMin');
    amountOfAlcoholMin.value = '0';
    var amountOfAlcoholMax = document.getElementById('amountOfAlcoholMax');
    amountOfAlcoholMax.value = '100';
    var capacityAlcoholMin = document.getElementById('capacityAlcoholMin');
    capacityAlcoholMin.value = '0';
    var capacityAlcoholMax = document.getElementById('capacityAlcoholMax');
    capacityAlcoholMax.value = '1000';
    var placeInStorage = document.getElementById('placeInStorage');
    placeInStorage.value = '';
}

function clearFormSearchManufacturer() {
    var alcoholname = document.getElementById('manufacturerName');
    alcoholname.value = '';
    var placeInStorage = document.getElementById('manufacturerTown');
    placeInStorage.value = '';
    var alcoholType = document.getElementById('manufacturerCountry');
    alcoholType.value = '';
    var alcoholName = document.getElementById('manufacturerComment');
    alcoholName.value = '';
}