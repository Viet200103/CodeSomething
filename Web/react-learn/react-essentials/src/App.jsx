import { useState } from 'react';
import Header from "./components/Header/Header";
import CoreConcept from "./components/CoreConcept";
import {CORE_CONCEPTS} from './data.js';
import TabButton from "./components/TabButton";
import {EXAMPLES} from "./data-with-examples";

function App() {
    const [selectedTopic, setSelectedTopic] = useState();

    function handleSelect(selectedButton) {
        setSelectedTopic(selectedButton);
    }

    let tabContent = <p>Please select a topic.</p>;

    if (selectedTopic) {
        tabContent = (
            <div id={"tab-content"}>
                <h3>{EXAMPLES[selectedTopic].title}</h3>
                <h3>{EXAMPLES[selectedTopic].description}</h3>
                <pre>
                    <code>{EXAMPLES[selectedTopic].code}</code>
                </pre>
            </div>
        );
    }

    return (
        <div>
            <Header/>

            <main>
                <section id={"core-concepts"}>
                    <h2>Core concepts</h2>
                    <ul>
                        <CoreConcept
                            title={CORE_CONCEPTS[0].title}
                            description={CORE_CONCEPTS[0].description}
                            image={CORE_CONCEPTS[0].image}
                        />
                        <CoreConcept {...CORE_CONCEPTS[1]} />
                        <CoreConcept {...CORE_CONCEPTS[2]} />
                        <CoreConcept {...CORE_CONCEPTS[3]} />
                    </ul>
                </section>
                <section id={"examples"}>
                    <h2>Examples</h2>
                    <menu>
                        <TabButton
                            isSelected={selectedTopic === 'components'}
                            onSelect={() => handleSelect('components')}
                        />
                        <TabButton
                            isSelected={selectedTopic === 'jsx'}
                            onSelect={() => handleSelect('jsx')}
                        >
                            JSX
                        </TabButton>
                        <TabButton
                            isSelected={selectedTopic === 'props'}
                            onSelect={() => handleSelect('props')}
                        >
                            Props
                        </TabButton>
                        <TabButton
                            isSelected={selectedTopic === 'state'}
                            onSelect={() => handleSelect('state')}
                        >
                            State
                        </TabButton>
                    </menu>
                    {tabContent}
                </section>
            </main>

        </div>
    );
}

export default App;
