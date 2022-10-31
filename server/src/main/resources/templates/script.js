let button = document.querySelector(".button");
button.addEventListener('click', function () {
    showAlert();
})
function showAlert() {
    testWhile(3);
}

function tryToSendRequest() {
    let url = 'http://localhost:8080/admin/users/1';
    let response = fetch(url);
    let user = response.json();
    alert(user.name);
}

function deleteUser(userId) {
    var xhttp = new XMLHttpRequest();
        xhttp.open("DELETE", "http://localhost:8080/admin/users/" + userId, true);
        xhttp.send();
}

function testWhile(a) {
    let x = 0;
    for (let i = 0; i <= a; i++) {
        console.log(i);
        if ((i % 2) == 0) {
            x = x + i;
        }
    }
    console.log('x = ' + x);
}