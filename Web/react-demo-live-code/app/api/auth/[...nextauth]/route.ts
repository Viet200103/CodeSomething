import NextAuth, {Profile} from 'next-auth';
import GoogleProvider from 'next-auth/providers/google';

import {connectToDB} from "@utils/database";
import User from "@models/user";

const handler = NextAuth({
    providers: [
        GoogleProvider(
            {
                clientId: process.env.GOOGLE_ID as string,
                clientSecret: process.env.GOOGLE_CLIENT_SECRET as string,
            }
        )
    ],
    callbacks: {
        async session(
            {session}
        ) {

            if (session && session.user && session.user.email) {
                // Tìm người dùng trong cơ sở dữ liệu dựa trên email từ phiên
                const sessionUser = await User.findOne({
                    email: session.user.email,
                });

                // Nếu tìm thấy người dùng, gán ID của người dùng vào phiên
                if (sessionUser) {
                    (session.user as any).id = sessionUser._id.toString();
                }
            }

            return session;
        },
        async signIn(
            {profile}
        ) {
            console.log(profile)
            if (profile == undefined) {
                return false
            }

            try {
                await connectToDB()

                const userExists = await User.findOne({
                    email: profile.email,
                })

                if (!userExists) {
                    await User.create({
                        email: profile.email,
                        username: "abc12345678",
                        image: (profile as any).picture
                    })
                } else {

                }
                return true;
            } catch (error) {
                console.log(error)
                return false
            }
        },
    }
})

export {handler as GET, handler as POST}