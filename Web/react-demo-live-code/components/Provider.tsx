'use client'

import {SessionProvider} from "@node_modules/next-auth/react";
import {ReactNode} from "react";
import {Session} from "@node_modules/next-auth";

export default function Provider(
    { children, session }: { children: ReactNode, session: Session | null }
) {
    return(
        <SessionProvider session={session}>
            {children}
        </SessionProvider>
    );
}