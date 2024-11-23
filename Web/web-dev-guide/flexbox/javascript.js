const switcher = document.getElementById("switcher");
const items = document.querySelectorAll("div:has(.box), div:not(.sub2)");

switcher.addEventListener("change", (evt) => {
    items.forEach((item) => {
        item.style.flex = evt.target.value;
    });
});

const switcher2 = document.getElementById("switcher_justify_content");
const container = document.getElementById("container_justify_content")

switcher2.addEventListener("change", (evt) => {
    container.style.justifyContent = evt.target.value;
});

const switcher3 = document.getElementById("switcher-align-content");
const alignContentContainer = document.getElementById("container-align-content")

alignContentContainer.style.flexWrap = "wrap"
alignContentContainer.style.maxWidth = "40rem"
alignContentContainer.style.minHeight = "15rem"

switcher3.addEventListener(
    "change", (evt) => {
        alignContentContainer.style.alignContent = evt.target.value
    }
)
