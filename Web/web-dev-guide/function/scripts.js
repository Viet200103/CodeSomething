const boxes = document.querySelectorAll('[data-transform]');

const slider = document.querySelector('input[type="range"]');

function changeBoxAttribute(box) {
    box.setAttribute(
        'style',
        `transform: rotate${box.getAttribute('data-transform').toUpperCase()}(${
            slider.value
        }deg)`
    );
}
slider.addEventListener(
    "input",
    () => {
        boxes.forEach(changeBoxAttribute)
    }
)