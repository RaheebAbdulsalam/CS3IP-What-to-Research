
/********************** add event listener on multiple elements ****************************/

const addEventOnElements = function (elements, eventType, callback) {
    for (let i = 0, len = elements.length; i < len; i++) {
        elements[i].addEventListener(eventType, callback);
    }
}



/********************** NAVBAR TOGGLE FOR MOBILE *******************************/

const navbar = document.querySelector("[data-navbar]");
const navTogglers = document.querySelectorAll("[data-nav-toggler]");
const overlay = document.querySelector("[data-overlay]");

const toggleNavbar = function () {
    navbar.classList.toggle("active");
    overlay.classList.toggle("active");
    document.body.classList.toggle("nav-active");
}

addEventOnElements(navTogglers, "click", toggleNavbar);



/*************************** HEADER ***** active header when window scroll down to 100px ************************/

const header = document.querySelector("[data-header]");

window.addEventListener("scroll", function () {
    if (window.scrollY > 100) {
        header.classList.add("active");
    } else {
        header.classList.remove("active");
    }
});



/*************************** IMAGE SLIDER IN THE HOME PAGE *****************************/

const sliders = document.querySelectorAll("[data-slider]");

const initSlider = function(currentSlider) {

    const sldierContainer = currentSlider.querySelector("[data-slider-container]");
    const sliderPrevBtn = currentSlider.querySelector("[data-slider-prev]");
    const sliderNextBtn = currentSlider.querySelector("[data-slider-next]");

    let currentSlidePos = 0;

    const moveSliderItem = function () {
        sldierContainer.style.transform = `translateX(-${sldierContainer.children[currentSlidePos].offsetLeft}px)`;
    }

    /*** NEXT SLIDE*/

    const slideNext = function () {
        const slideEnd = currentSlidePos >= sldierContainer.childElementCount - 1;

        if (slideEnd) {
            currentSlidePos = 0;
        } else {
            currentSlidePos++;
        }

        moveSliderItem();
    }

    sliderNextBtn.addEventListener("click", slideNext);

    /*** PREVIOUS SLIDE*/

    const slidePrev = function () {

        if (currentSlidePos <= 0) {
            currentSlidePos = sldierContainer.childElementCount - 1;
        } else {
            currentSlidePos--;
        }

        moveSliderItem();
    }

    sliderPrevBtn.addEventListener("click", slidePrev);

    const dontHaveExtraItem = sldierContainer.childElementCount <= 1;
    if (dontHaveExtraItem) {
        sliderNextBtn.style.display = "none";
        sliderPrevBtn.style.display = "none";
    }

}

for (let i = 0, len = sliders.length; i < len; i++) { initSlider(sliders[i]); }



/***************** In the sign-up form to limit the student number to 9 digits *********************/

const studentNumberInput = document.getElementById('studentNumber');
const errorElement = document.getElementById('studentNumberError');

studentNumberInput.addEventListener('input', function (event) {
    let inputValue = event.target.value;
    // Remove non-numeric characters
    inputValue = inputValue.replace(/\D/g, '');
    // Limit to 9 digits
    inputValue = inputValue.slice(0, 9);
    // Update the input value
    event.target.value = inputValue;

    // Validate minimum length
    if (inputValue.length < 9) {
        errorElement.textContent = 'Student number must be at least 9 digits.';
        studentNumberInput.setCustomValidity('Student number must be at least 9 digits.');
    } else {
        errorElement.textContent = '';
        studentNumberInput.setCustomValidity('');
    }
});

// Add an event listener to reset the error when the field is focused
studentNumberInput.addEventListener('focus', function () {
    errorElement.textContent = '';
    studentNumberInput.setCustomValidity('');
});



