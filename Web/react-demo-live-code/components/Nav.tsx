'use client'

import Link from "@node_modules/next/dist/client/link";
import Image from "next/image";
import {signOut, signIn, getProviders, useSession, ClientSafeProvider, LiteralUnion} from "next-auth/react";
import {useEffect, useState} from "react";
import {BuiltInProviderType} from "@node_modules/next-auth/providers";

type ProvidersType = Record<LiteralUnion<BuiltInProviderType>, ClientSafeProvider> | null;

export default function Nav() {

    const {data: session} = useSession();

    const [providers, setProviders] = useState<ProvidersType>()
    const [toogleDropdown, setToogleDropdown] = useState<boolean>(false)


    useEffect(
        () => {
            const waitProviders = async () => {
                const response = await getProviders();
                setProviders(response);
            }

            waitProviders().then();

        }, []
    )

    return (
        <nav className={"flex-between w-full mb-16 pt-3"}>
            <Link href={"/"} className={"flex gap-2 flex-center"}>
                <Image
                    src={"/assets/images/logo.svg"}
                    alt={"Promtopia Logo"}
                    width={30}
                    height={30}
                    className={"object-contain"}
                />
                <p className={"logo_text"}>Promptopia</p>
            </Link>

            {/*Desktop Navigation*/}
            <div className={"sm:flex hidden"}>
                {session?.user ? (
                    <div className={"flex gap-3 md:gap-5"}>
                        <Link href={"/create-prompt"} className={"black_btn"}>
                            Create Post
                        </Link>

                        <button type={"button"} className={"outline_btn"} onClick={() => signOut()}>
                            Sign Out
                        </button>

                        <Link href={"/profile"}>
                            <Image
                                src={session?.user?.image as string}
                                alt={"profile"}
                                width={37}
                                height={37}
                                className={"rounded-full"}
                            />
                        </Link>
                    </div>
                ) : (
                    <>
                        {
                            providers && Object.values(providers).map(
                                (providers) => (
                                    <button
                                        type={"button"}
                                        key={providers.name}
                                        onClick={() => signIn(providers.id)}
                                        className={"black_btn"}
                                    >
                                        Sign In
                                    </button>
                                )
                            )
                        }
                    </>
                )}
            </div>

            {/*Mobile Navigation*/}
            <div className={"sm:hidden flex relative"}>
                {session?.user ? (
                    <div className={"flex"}>
                        <Image
                            src={session?.user?.image as string}
                            alt={"User image"}
                            width={30}
                            height={30}
                            className={"rounded-full"}
                            onClick={() => {setToogleDropdown((prev) => !prev)}}
                        />
                        {toogleDropdown && (
                            <div className={"dropdown"}>
                                <Link
                                    href={"/profile"}
                                    className={"dropdown_link"}
                                    onClick={() => setToogleDropdown(false)}
                                >
                                    My Profile
                                </Link>

                                <Link
                                    href={"/create-prompt"}
                                    className={"dropdown_link"}
                                    onClick={() => setToogleDropdown(false)}
                                >
                                    Create Prompt
                                </Link>
                                <button
                                    type={"button"}
                                    onClick={() => {
                                        setToogleDropdown(false)
                                        signOut();
                                    }}
                                    className={"mt-5 w-full black_btn"}
                                >
                                    Sign  Out
                                </button>
                            </div>
                        )}
                    </div>
                ) : (
                    <>
                        {
                            providers && Object.values(providers).map(
                                (providers) => (
                                    <button
                                        type={"button"}
                                        key={providers.name}
                                        onClick={() => signIn(providers.id)}
                                        className={"black_btn"}
                                    >
                                        Sign In
                                    </button>
                                )
                            )
                        }
                    </>
                )}
            </div>
        </nav>
    )
}