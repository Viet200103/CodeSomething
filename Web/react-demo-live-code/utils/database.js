
import mongoose from 'mongoose';

let isConnected = false;

export async function connectToDB() {
    mongoose.set('strictQuery', true);
    if (isConnected) {
        console.log('MongoDB Connected!');
    }

    try {
        await mongoose.connect(
            process.env.MONGO_URL, {
                dbName: "share_prompt",
            }
        );

        isConnected = true
        console.log('Connected to DB!');
    } catch (error) {
        console.log(error)
    }
}