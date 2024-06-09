'use client'

import Form from "@components/Form";
import { FormEvent, useState } from "react";
import { useSession } from "next-auth/react";
import { useRouter } from "next/navigation";
import {Post} from "@models/post";

export default function CreatePrompt() {

    const router = useRouter();
    const {data: session} = useSession<any>();

    const [isSubmitting, setIsSubmitting] = useState(false);
    const [post, setPost] = useState<Post>({
        prompt: '', tag: ''
    })

    const  createPrompt = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setIsSubmitting(true)

        try {
            const response = await fetch(
                "/api/prompt/new", {
                    method: "POST",
                    body: JSON.stringify({
                        prompt: post.prompt,
                        tag: post.tag,
                        userId: (session?.user as any).id
                    })
                }
            )

            if (response.ok) {
                router.push("/")
            }

        } catch (error) {
            console.log(error)
        } finally {
            setIsSubmitting(false)
        }
    }
    return (
        <Form
            type={"Create"}
            post={post}
            setPost={setPost}
            submitting={isSubmitting}
            handleSubmit={createPrompt}
        >

        </Form>
    );
}