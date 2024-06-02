import { combineReducers } from "@reduxjs/toolkit";
import { persistReducer } from "redux-persist";
import userReducer from "@/app/component/users/service/user.slice";
import itemReducer from "@/app/component/board/service/board-slice";
import createWebStorage from "redux-persist/lib/storage/createWebStorage";

const createNoopStorage = () => {
  return {
    getItem() {
      return Promise.resolve(null);
    },
    setItem(_key: string, value: number) {
      return Promise.resolve(value);
    },
    removeItem() {
      return Promise.resolve();
    },
  };
};

const storage =
  typeof window !== "undefined"
    ? createWebStorage("local")
    : createNoopStorage();

const userPersistConfig = {
  key: "user",
  storage,
  whitelist: ["userState"],
};
const itemPersistConfig = {
  key: "item",
  storage,
  whitelist: ["itemState"],
};



const persistedUserReducer = persistReducer(userPersistConfig, userReducer);
const persisteditemReducer = persistReducer(itemPersistConfig, itemReducer);

export const rootReducer = combineReducers({
  user: persistedUserReducer,
  item: persisteditemReducer,
});