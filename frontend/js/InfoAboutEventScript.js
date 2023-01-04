let add_event_to_bd_button = document.querySelector(".add-event-to-bd");
let event_name_input = document.querySelector('.event-name');
add_event_to_bd_button.addEventListener('click', function () {
    addCardToDiv(event_name_input.value, 'test', 'test');
 });
