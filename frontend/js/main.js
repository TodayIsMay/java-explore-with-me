let sign_up_button = document.querySelector(".sign-up-button");
let input_name = document.querySelector(".input-name");
let input_email = document.querySelector(".input-email");

let array = [];
sign_up_button.addEventListener('click', function () {
  array.push(input_name.value);
  replaceInputs();
  setTimeout(redirect, 3000, "EventPage.html");
  console.log(input_name.value);
  addUserToDB(input_name.value, input_email.value);
})

function redirect(address) {
  window.location.href = address;
}

function replaceInputs() {
  let div = document.createElement('div');
  div.className = 'replacer';
  div.innerHTML = 'Огонь. Ожидайте.';
  document.body.append(div);
}

async function addUserToDB(name, email) {
  //let url = 'http://localhost:8080/admin/users';
  let url = '${server.url}/admin/users';
  let user = {
    name: name,
    email: email
  };
  let response = await fetch(url, {
    mode: 'no-cors',
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(user)
  });
  let result = await response.json();
  console.log(result.message);
}
