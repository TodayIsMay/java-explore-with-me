let add_event_button = document.querySelector('.add-event');
let event_cards_div = document.querySelector('.event-cards');
let cardCounter = 0;


add_event_button.addEventListener('click', function() {
  let infoAboutEventInput = document.createElement('div');
  infoAboutEventInput.className = 'info-about-event-input';
  infoAboutEventInput.innerHTML =
    '<input class="event-name" placeholder="Название">' +
    '<input class="event-description" placeholder="Описание">' +
    '<input class="event-date" placeholder="Дата и время">' +
    '<button class="add-event-to-bd">Добавить</button>' +
    '<script type="text/javascript">let add_event_to_bd_button = document.querySelector(".add-event-to-bd");' +
    "add_event_to_bd_button.addEventListener('click', function() {" +
    "console.log('test');" +
    "});" +
    "let event_name_input = document.querySelector('.event-name');" +
    "function addCardToDiv() { " +
    "console.log('Add card to div');" +
    "  };" +
    "</script>";
  let name = document.querySelector('.info-about-event')
  event_cards_div.replaceWith(infoAboutEventInput);
  test();
})
function test(name, description, date) {
  let test = document.querySelector('.info-about-event-input');
  let button = test.querySelector('.add-event-to-bd');
  button.addEventListener('click', function () {
    console.log('Add card to div');
    let newCardDiv = document.createElement('div');
    cardCounter = cardCounter + 1;
    newCardDiv.className = 'eventCard' + cardCounter;
    newCardDiv.innerHTML =
      '<h2>' + name + '</h2>' +
      '<div>' + description + ' ' + date + '</div>';
    event_cards_div.append(newCardDiv);
  });
}
