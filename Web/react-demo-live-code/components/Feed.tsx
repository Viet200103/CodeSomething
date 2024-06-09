'use client'

import React, {ChangeEvent, useEffect, useState} from "react";
import PromptCard from "@components/PromptCard";

function PromptCardList(
    {data, handleTagClick}: {
        data: any[],
        handleTagClick: () => void
    }
) {
    return (
        <div className={"mt-16 prompt_layout"}>
            {data.map((post) => (
                <PromptCard
                    key={post._id}
                    post={post}
                    handleDelete={() => {}}
                    handleEdit={() => {}}
                    handleTagClick={handleTagClick}
                />
            ))}
        </div>
    )
}

export default function Feed() {

    const [searchText, setSearchText] = useState('');
    const [posts, setPosts] = useState<any[]>([]);

    const handleSearchChange = (e: ChangeEvent<HTMLInputElement>) => {

    }

    useEffect(() => {
        const fetchPosts = async () => {
            const response = await fetch('/api/prompt');
            const data = await response.json()
            setPosts(data)
        }

        fetchPosts().then();
    }, []);

    return (
        <section className={"feed"}>
            <form className={"relative w-full flex-center"}>
                <input
                    type={"text"}
                    placeholder={"Search for a tag or a username"}
                    value={searchText}
                    required={true}
                    className={"search_input peer"}
                    onChange={(e) => handleSearchChange(e)}
                />
            </form>

            <PromptCardList
                data={posts}
                handleTagClick={() => {
                }}
            />
        </section>
    );
}