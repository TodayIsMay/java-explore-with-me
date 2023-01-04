let sign_up_button = document.querySelector(".sign-up-button");
let input_name = document.querySelector(".input-name");
let input_email = document.querySelector(".input-email");

let array = [];
sign_up_button.addEventListener('click', function() {
  array.push(input_name.value);
  replaceInputs();
  setTimeout(redirect, 5000, "EventPage.html");
  console.log(input_name.value);
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
