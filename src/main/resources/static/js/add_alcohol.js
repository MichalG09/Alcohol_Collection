function autocomplete(inp, arr, isManufacturerName) {
    /*the autocomplete function takes two arguments,
    the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    inp.addEventListener("input", function(e) {
        var a, b, i, val = this.value;
        /*close any already open lists of autocompleted values*/
        closeAllLists();
        if (!val) {
            return false;
        }
        currentFocus = -1;
        /*create a DIV element that will contain the items (values):*/
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");
        /*append the DIV element as a child of the autocomplete container:*/
        this.parentNode.appendChild(a);
        /*for each item in the array...*/
        for (i = 0; i < arr.length; i++) {
            /*check if the item starts with the same letters as the text field value:*/
            if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                /* split name and id of manufacturer */
                var arr1;
                if (isManufacturerName) {
                    var splitedName = arr[i].split(',');
                    arr1 = splitedName[0];
                    var id = splitedName[1];
                } else {
                    var arr1 = arr[i];
                }

                /*create a DIV element for each matching element:*/
                b = document.createElement("DIV");
                /*make the matching letters bold:*/
                b.innerHTML = "<strong>" + arr1.substr(0, val.length) + "</strong>";
                b.innerHTML += arr1.substr(val.length);
                /*insert a input field that will hold the current array item's value:*/
                b.innerHTML += "<input type='hidden' value='" + arr1 + "'>";

                if (isManufacturerName) {
                    /*insert a input field that will hold the current manufacturer id:*/
                    b.innerHTML += "<input type='hidden' value='" + id + "'>";
                }
                /*execute a function when someone clicks on the item value (DIV element):*/
                b.addEventListener("click", function(e) {
                    /*insert the value for the autocomplete text field:*/
                    inp.value = this.getElementsByTagName("input")[0].value;
                    /*close the list of autocompleted values,
                    (or any other open lists of autocompleted values:*/

                    if (isManufacturerName) {
                        updateFormManufacturer(this.getElementsByTagName("input")[1].value);
                    }
                    closeAllLists();
                });
                a.appendChild(b);
            }
        }
    });
    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener("keydown", function(e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x) x = x.getElementsByTagName("div");
        if (e.keyCode == 40) {
            /*If the arrow DOWN key is pressed,
            increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 38) { //up
            /*If the arrow UP key is pressed,
            decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
                /*and simulate a click on the "active" item:*/
                if (x) x[currentFocus].click();
            }
        }
    });

    function addActive(x) {
        /*a function to classify an item as "active":*/
        if (!x) return false;
        /*start by removing the "active" class on all items:*/
        removeActive(x);
        if (currentFocus >= x.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (x.length - 1);
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add("autocomplete-active");
    }

    function updateFormManufacturer(id) {
        for (var i = 0; i < manufacturers.length; i++) {
            if (manufacturers[i].id == id) {
                updateForm(manufacturers[i]);
            }
        }
    }

    function updateForm(manufacturer) {
        var div = document.getElementById("insertManufacturerCountry");
        div.value = manufacturer.country;
        div = document.getElementById("insertManufacturerTown");
        div.value = manufacturer.town;
        div = document.getElementById("insertManufacturerComment");
        div.value = manufacturer.comment;
        div = document.getElementById("insertManufacturerId");
        div.value = manufacturer.id;
        var div5 = document.getElementById("infoNewManufacturer");
        div5.innerText = "producent już istnieje, edycja danych spowoduje ich nadpisanie";
    }

    function removeActive(x) {
        /*a function to remove the "active" class from all autocomplete items:*/
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }

    function closeAllLists(elmnt) {
        /*close all autocomplete lists in the document,
        except the one passed as an argument:*/
        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt != x[i] && elmnt != inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }
    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function(e) {
        closeAllLists(e.target);
    });
}

function nameCompanyChange() {
    var temp = document.getElementById("insertManufacturerName");
    updateFormManufacturer(document.getElementById("insertManufacturerName").value);
}

function updateFormManufacturer(nameCompany) {
    var isChange = 1;
    for (var i = 0; i < manufacturers.length; i++) {
        if (manufacturers[i].name == nameCompany) {
            updateForm(manufacturers[i]);
            isChange = 0;
        }
    }
    if (isChange) {
        updateForm('null');
    }
}

function updateForm(manufacturer) {
    var div1 = document.getElementById("insertManufacturerCountry");
    var div2 = document.getElementById("insertManufacturerTown");
    var div3 = document.getElementById("insertManufacturerComment");
    var div4 = document.getElementById("insertManufacturerId");
    var div5 = document.getElementById("infoNewManufacturer");
    if (manufacturer == 'null') {
        div1.value = "";
        div2.value = "";
        div3.value = "";
        div4.value = "";
        div5.innerText = "nowy producent, po zapisaniu zostanie dodany nowy producent";
    } else {
        div1.value = manufacturer.country;
        div2.value = manufacturer.town;
        div3.value = manufacturer.comment;
        div4.value = manufacturer.id;
        div5.innerText = "producent już istnieje, edycja danych spowoduje ich nadpisanie";
    }
}

function clearForm() {
    var div = document.getElementById("insertManufacturerCountry");
    div.value = "";
    div = document.getElementById("insertManufacturerTown");
    div.value = "";
    div = document.getElementById("insertManufacturerComment");
    div.value = "";
    div = document.getElementById("insertManufacturerId");
    div.value = "";
}

var manufacturerWithNameAndId = new Array(manufacturers.length);
for (var i = 0; i < manufacturers.length; i++) {
    manufacturerWithNameAndId[i] = manufacturers[i].name + ',' + manufacturers[i].id;
}

var allTypesAlcohol = new Array(typesAlcohol.length);
for (var i = 0; i < typesAlcohol.length; i++) {
    allTypesAlcohol[i] = typesAlcohol[i].name;
}

autocomplete(document.getElementById("insertManufacturerName"), manufacturerWithNameAndId, 1);
autocomplete(document.getElementById("insertAlcoholType"), allTypesAlcohol, 0);