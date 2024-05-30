import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';
import { createSlice } from "@reduxjs/toolkit";
import { findItemAll } from './board-service';
import boardSlice from '@/redux/features/boards/board.slice';
import { itemColumns } from '../model/item-column';

interface BoardState{
    json:itemColumns,
    array:Array<itemColumns>
}

export const initialState:BoardState = {
    json: {} as itemColumns,
    array: []
}

const boardThunks = [findItemAll]

const status = {
    pending: 'pending',
    fulfilled: 'fulfilled',
    rejected: 'rejected'
}

const handleFulfilled =  (state: any, {payload}: any) => {
    console.log('------------------Board conclusion ---------------')
    // state.array = payload
    console.log(state.array)

}


const handlePending = (state: any) => {
  
}
const handleRejected = (state: any) => {
  
}

export const itemSlice = createSlice({
    name: "items",
    initialState,
    reducers: {},
    extraReducers: builder => {
        const {pending, rejected} = status;

        builder
        .addCase(findItemAll.fulfilled, (state: any, {payload}: any) => {state.array = payload})
  
    }
})
export const getAllBoards = (state: any) => {
    console.log('여기는 슬라이스'+JSON.stringify(state.board.array))
    return state.board.array}
export const getAllitems = (state: any) => {
    console.log('------------------ Before useSelector ---------------')
    console.log(JSON.stringify(state.item.array))
    return state.item.array;
}


export const {} = itemSlice.actions

export default itemSlice.reducer;