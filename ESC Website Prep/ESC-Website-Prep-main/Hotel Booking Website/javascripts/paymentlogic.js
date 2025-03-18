// Update elements with booking details
document.addEventListener("DOMContentLoaded", function () {
  //getting actual parameters from the url
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);

  let hotelName = urlParams.get('hotel');
  let checkInDate = urlParams.get('checkin');
  let checkOutDate = urlParams.get('checkout');
  let guestNum = urlParams.get('guests');
  let roomType = urlParams.get('room');
  let price = urlParams.get('price');


  // Function to generate day options
  function generateDays() {
    const dayDropdown = document.getElementById('dayDropdown');
    for (let day = 1; day <= 31; day++) {
        const option = document.createElement('option');
        option.value = day.toString().padStart(2, '0');
        option.textContent = day.toString().padStart(2, '0');
        dayDropdown.appendChild(option);
    }
  }

  // Function to generate month options
  function generateMonths() {
    const monthDropdown = document.getElementById('monthDropdown');
    for (let month = 1; month <= 12; month++) {
        const option = document.createElement('option');
        option.value = month.toString().padStart(2, '0');
        option.textContent = month.toString().padStart(2, '0');
        monthDropdown.appendChild(option);
    }
  }

  // Function to generate year options
  function generateYears(startYear, endYear) {
    const yearDropdown = document.getElementById('yearDropdown');
    for (let year = startYear; year <= endYear; year++) {
        const option = document.createElement('option');
        option.value = year;
        option.textContent = year;
        yearDropdown.appendChild(option);
    }
  }
  // Get references to the HTML elements
  const hotelNameElement = document.querySelector('.hotelrm');
  const roomTypeElement = document.querySelector('.rmtype');
  const guestNumberElement = document.getElementById('guest');
  const checkInDateElement = document.getElementById('inDate');
  const checkOutDateElement = document.getElementById('outDate'); // This needs to be updated
  const totalAmountElement = document.getElementById('price');

  // Update the elements with the retrieved data
  hotelNameElement.textContent = hotelName;
  roomTypeElement.textContent = roomType;
  guestNumberElement.textContent = guestNum + ' guests';
  checkInDateElement.textContent = checkInDate;
  checkOutDateElement.textContent = checkOutDate; 
  totalAmountElement.textContent = '$' + price;

  // Call the functions to generate options
  generateDays();
  generateMonths();
  generateYears(1900, new Date().getFullYear());
});

document.querySelector('form').addEventListener('submit', (event) => {
  event.preventDefault();

  // Access the form input elements using their id attribute
  const fullnameInput = document.getElementById('fullname');
  const emailInput = document.getElementById('email');
  const specialreqInput = document.getElementById('specialreq');
  const billaddressInput = document.getElementById('billaddress');
  const telphoneInput = document.getElementById('telphone');

  

  // Get the user input from the form fields
  const fullname = fullnameInput.value;
  const email = emailInput.value;
  const specialreq = specialreqInput.value;
  const billaddress = billaddressInput.value;
  const telephone = telphoneInput.value;



  // Create the request payload (data to be sent to the server)
  const data = {
    fullname,
    email,
    specialreq,
    billaddress,
    telephone
    
  };

  // Send the form data to the server
  fetch('/submit', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then((data) => {
      // Handle the response from the server if needed
      console.log('Server response:', data);
      // Optionally, you can redirect to another page after successful submission
      window.location.href = '/';
    })
    .catch((error) => {
      console.error('Error:', error);
      // Handle any errors that occurred during the fetch request
    });
  
  // Add event listeners to the dropdowns
  const dayDropdown = document.getElementById('dayDropdown');
  const monthDropdown = document.getElementById('monthDropdown');
  const yearDropdown = document.getElementById('yearDropdown');
  const selectedDOBElement = document.getElementById('selectedDOB');

  dayDropdown.addEventListener('change', updateSelectedDOB);
  monthDropdown.addEventListener('change', updateSelectedDOB);
  yearDropdown.addEventListener('change', updateSelectedDOB);

  // Function to update the selected DOB
  function updateSelectedDOB() {
    const selectedDay = dayDropdown.value;
    const selectedMonth = monthDropdown.value;
    const selectedYear = yearDropdown.value;

    // Combine the selected day, month, and year to form the DOB string
    const selectedDOB = `${selectedDay}/${selectedMonth}/${selectedYear}`;

    // Update the selectedDOBElement with the selected DOB
    selectedDOBElement.textContent = `Selected DOB: ${selectedDOB}`;
  }

  // Call the updateSelectedDOB function initially to set the default DOB
  updateSelectedDOB();

});

