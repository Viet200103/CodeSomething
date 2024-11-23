const offsetX = document.querySelector('#offsetx');
const offsetY = document.querySelector('#offsety');
const blur = document.querySelector('#blur');
const spread = document.querySelector('#spread');
const color = document.querySelector('#color');
const inset = document.querySelector('#inset');
const resultElement = document.querySelector('#result');
const box = document.querySelector('.my-element');

const render = () => {
    let boxShadow = 'box-shadow: ';
    if (inset.checked) {
        boxShadow += 'inset '
    }

    boxShadow += offsetX.value + 'px ' + offsetY.value + 'px ' + blur.value + 'px ' + spread.value + 'px ' + color.value;

    resultElement.innerHTML = `<code>${boxShadow}</code>`;
    box.setAttribute('style', boxShadow);
};

[offsetX, offsetY, blur, spread, color].forEach(
    input => {
        input.addEventListener('input', render)
    }
)

inset.addEventListener('change', render)

render();