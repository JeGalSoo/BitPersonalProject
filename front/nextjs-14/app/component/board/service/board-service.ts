import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { findItemAllAPI, findItemDetailAPI } from "./board-.api";

export const findItemAll: any = createAsyncThunk(
    'items/findItemAll',
    async ()=> await  findItemAllAPI()
)
export const findItemDetail: any = createAsyncThunk(
    'items/findItemDetail',
    async (data)=> await  findItemDetailAPI(data)
)