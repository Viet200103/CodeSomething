const switcherIntrinsicSizing = document.getElementById("switcher-intrinsic-sizing");
const containerIntrinsicSizing = document.getElementById("container-intrinsic-sizing");

containerIntrinsicSizing.style.gridTemplateColumns = `repeat(3, auto)`
switcherIntrinsicSizing.addEventListener(
    "change", (evt) => {
        containerIntrinsicSizing.style.gridTemplateColumns = `repeat(3, ${evt.target.value})`
    }
)

const switcherFrUnit = document.getElementById("switcher-fr-unit");
const containerFrUnit = document.getElementById("container-fr-unit");

containerFrUnit.style.gridTemplateColumns = "1fr 1fr 1fr"
switcherFrUnit.addEventListener(
    "change", (evt) => {
        containerFrUnit.style.gridTemplateColumns = `1fr 1fr ${evt.target.value}`
    }
)

const switcherFillFit = document.getElementById("switcher-auto-fill-fit");
const containerFillFit = document.getElementById("container-auto-fill-fit");


containerFillFit.style.gridTemplateColumns = "repeat(auto-fill, minmax(100px, 1fr))"
switcherFillFit.addEventListener(
    "change", (evt) => {
        containerFillFit.style.gridTemplateColumns = `repeat(${evt.target.value}, minmax(100px, 1fr))`;
    }
)

const switcherGridFlow = document.getElementById("switcher-grid-flow");
const switcherWritingMode = document.getElementById("switcher-writing-mode");
const containerAutoFlow = document.getElementById("container-auto-flow");

switcherGridFlow.addEventListener(
    "change", (evt) => {
        containerAutoFlow.style.gridAutoFlow = evt.target.value
    }
)

switcherWritingMode.addEventListener(
    "change", (evt) => {
        containerAutoFlow.style.writingMode = evt.target.value
    }
)

const packing = document.getElementById("toggle-grid-packing");
const containerGridPacking = document.getElementById("container-grid-packing")

packing.addEventListener(
    "click", (evt) => {
        if (evt.target.checked) {
            containerGridPacking.style.gridAutoFlow = "row dense"
        } else {
            containerGridPacking.style.gridAutoFlow = "row"
        }
    }
)

const toggleZIndex = document.getElementById("toggle-grid-z-index");
const boxZIndexOneItem = document.getElementById("box-z-index-item-one");

toggleZIndex.addEventListener(
    "change", (evt) => {
        if (evt.target.checked) {
            boxZIndexOneItem.style.zIndex = "2";
        } else {
            boxZIndexOneItem.style.zIndex = "0";
        }
    }
)

const extraSpaceAlignmentSwitcher = document.getElementById("switcher-extra-space-alignment");
const extraSpaceJustificationSwitcher = document.getElementById("switcher-extra-space-justification");
const extraSpaceContainer = document.getElementById("container-extra-space");


extraSpaceAlignmentSwitcher.addEventListener(
    "change", (evt) => {
        extraSpaceContainer.style.alignContent = evt.target.value
    }
)

extraSpaceJustificationSwitcher.addEventListener(
    "change", (evt) => {
        extraSpaceContainer.style.justifyContent = evt.target.value
    }
)


const esItemAlignmentSwitcher = document.getElementById("switcher-grid-align-items");
const esItemJustificationSwitcher = document.getElementById("switcher-grid-justification-items");
const esItemContainer = document.getElementById("container-extra-space-items");

esItemAlignmentSwitcher.addEventListener(
    "change", (evt) => {
        esItemContainer.style.alignItems = evt.target.value;
    }
)

esItemJustificationSwitcher.addEventListener(
    "change", (evt) => {
        esItemContainer.style.justifyItems = evt.target.value;
    }
)


