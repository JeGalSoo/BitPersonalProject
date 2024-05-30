import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { findItemAllAPI } from "./board-.api";

export const findItemAll: any = createAsyncThunk(
    'items/findItemAll',
    async ()=> await  findItemAllAPI()
)