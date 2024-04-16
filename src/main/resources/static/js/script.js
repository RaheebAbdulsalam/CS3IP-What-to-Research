let projectCounter = 5;

/**************** Function to add event listeners to multiple elements  *******************/
function addEventOnElements(elements, eventType, callback) {
    elements.forEach(function (element) {
        element.addEventListener(eventType, callback);
    });
}

/******************* Navigation toggling for mobile **********************/

const navbar = document.querySelector("[navigation]");
const navToggle = document.querySelectorAll("[nav-toggle]");
const overlay = document.querySelector("[overlay]");
const toggleNavigation = function () {
    navbar.classList.toggle("active");
    overlay.classList.toggle("active");
    document.body.classList.toggle("nav-active");
};
addEventOnElements(navToggle, "click", toggleNavigation);

/************* Active header to change background colour when scrolling down the page *************/
const header = document.querySelector("[custom-header]");

function changeHeader() {
    header.classList.toggle("active", window.scrollY > 100);
}

window.addEventListener("scroll", changeHeader);

/**************************** Image Slider ************************************/
function setUpSlider(currentSlider) {
    const sliderContainer = currentSlider.querySelector("[slider-container]");
    const sliderPrevBtn = currentSlider.querySelector("[slider-prev]");
    const sliderNextBtn = currentSlider.querySelector("[slider-next]");
    let currentSlidePos = 0;

    // Move slider item
    function moveSliderItem() {
        sliderContainer.style.transform = `translateX(-${sliderContainer.children[currentSlidePos].offsetLeft}px)`;
    }

    // Handle next slide
    function slideNext() {
        currentSlidePos = (currentSlidePos + 1) % sliderContainer.childElementCount;
        moveSliderItem();
    }

    sliderNextBtn.addEventListener("click", slideNext);

    // Handle previous slide
    function slidePrev() {
        currentSlidePos = (currentSlidePos - 1 + sliderContainer.childElementCount) % sliderContainer.childElementCount;
        moveSliderItem();
    }

    sliderPrevBtn.addEventListener("click", slidePrev);

    // Hide navigation buttons if there's only one slide
    if (sliderContainer.childElementCount <= 1) {
        sliderNextBtn.style.display = "none";
        sliderPrevBtn.style.display = "none";
    }
}

// Initialise the slider
document.querySelectorAll("[slider]").forEach(setUpSlider);

/********** collapsible content for toggling between hiding and showing content in features section ***************/
const accordions = document.querySelectorAll("[accordion]");
let lastActiveAccordion = accordions[0];

accordions.forEach(function (currentAccordion) {
    const accordionBtn = currentAccordion.querySelector("[accordion-btn]");

    accordionBtn.addEventListener("click", function () {
        if (lastActiveAccordion && lastActiveAccordion !== currentAccordion) {
            lastActiveAccordion.classList.remove("expanded");
        }

        currentAccordion.classList.toggle("expanded");
        lastActiveAccordion = currentAccordion;
    });
});

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


/************ Function to check if both passwords are the same **************/
function checkPasswords(event) {
    const password = document.getElementsByName('password')[0].value;
    const confirmPassword = document.getElementsByName('confirmPassword')[0].value;
    const errorMessageElement = document.getElementById('error-message');

    if (password !== confirmPassword) {
        event.preventDefault();
        errorMessageElement.textContent = 'Passwords do not match! Please try again';
    } else {
        errorMessageElement.textContent = '';
    }
}

const signupForm = document.getElementById('signupForm');
signupForm.addEventListener('submit', checkPasswords);


/*********************** Add Project to the shortlist *********************/
function addToShortlist() {
    let wishlistForm = document.getElementById('add-to-shortlist-form');
    let formData = new FormData(wishlistForm);

    let http = new XMLHttpRequest();
    http.open('POST', '/shortlist', true);

    http.onload = function () {
        if (http.status === 200) {
            // Display the success message
            document.getElementById('shortlist-success-message').style.display = 'block';
        } else {
            console.error('Failed to add project to shortlist.');
        }
    };

    http.send(formData);
}


/*********************** Validation for the Research Form *********************/
function updateProjectTypeOptions() {
    let methodologySelect = document.getElementById('project-methodology');
    let typeSelect = document.getElementById('project-type');
    // Enable all options
    for (let i = 0; i < typeSelect.options.length; i++) {
        typeSelect.options[i].disabled = false;
    }
    // Disable options based on selected methodology
    if (methodologySelect.value === 'Experimental Research' || methodologySelect.value === 'Theoretical Research') {
        disableTypesOptions(typeSelect, ['Mobile App Development', 'Website Development', 'Game Development']);
    }

}

// Disable languages based on selected project type
function updateLanguageOptions() {
    let typeSelect = document.getElementById('project-type').value;
    enableLanguages(['java', 'python', 'javascript', 'c#', 'c', 'c++']);
    if (typeSelect === 'Mobile App Development') {
        disableLanguages(['python', 'c', 'c++']);
    }
    if (typeSelect === 'Website Development') {
        disableLanguages(['c', 'c++']);
    }
    if (typeSelect === 'Data Science' || typeSelect === 'Machine Learning') {
        disableLanguages(['java', 'javascript', 'c#', 'c', 'c++']);
    }
    if (typeSelect === 'Game Development') {
        disableLanguages(['java', 'python', 'javascript', 'c']);
    }
    if (typeSelect === 'Robotics') {
        disableLanguages(['java', 'javascript', 'c#']);
    }
}


function disableTypesOptions(select, optionsToDisable) {
    for (let i = 0; i < select.options.length; i++) {
        if (optionsToDisable.includes(select.options[i].value)) {
            select.options[i].disabled = true;
        }
    }
}

function disableLanguages(languages) {
    languages.forEach(function (language) {
        let checkbox = document.getElementById(language);
        if (checkbox) {
            checkbox.disabled = true;
        }
    });
}

function enableLanguages(languages) {
    languages.forEach(function (language) {
        let checkbox = document.getElementById(language);
        if (checkbox) {
            checkbox.disabled = false;
        }
    });
}

// Load more projects when user clicks load more button
function loadMoreProjects() {
    const projects = document.querySelectorAll('.project-row');
    const maxIndex = Math.min(projectCounter + 5, projects.length);

    for (let i = projectCounter; i < maxIndex; i++) {
        projects[i].style.display = '';
    }

    projectCounter = maxIndex;

    if (projectCounter === projects.length) {
        document.getElementById('load-more-projects').style.display = 'none';
    }
}







