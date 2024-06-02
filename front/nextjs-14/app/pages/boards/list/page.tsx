'use client'

import { useRouter } from "next/navigation"
import { DataGrid } from '@mui/x-data-grid';
import { useState, useEffect } from "react"
import {Box, Button, Input} from '@mui/material';
import { useSelector, useDispatch } from 'react-redux'
import { NextPage } from "next";
import Link from "next/link";
import { PG } from "@/redux/common/enums/PG";
import itemColumns from "@/app/component/board/module/columns";
import MoveButton from "@/app/atoms/button/MoveButton";
import { findItemAll, findItemDetail } from "@/app/component/board/service/board-service";
import { getAllDetail, getAllitems } from "@/app/component/board/service/board-slice";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import axios from "axios";
import AxiosConfig from "@/redux/common/configs/axios-config";
import instance from "@/app/component/common/configs/axios-config";
import moment from "moment/moment";



const ArticleListPage: NextPage = () => {
  const dispatch = useDispatch()
  const item= useSelector(getAllitems)
  const item1= useSelector(getAllDetail)
  const [search,setSearch] = useState()
  const [sdate,setSdate] = useState('')
  const [edate,setEdate] = useState('')

  const handleTiele = (e: any) => {
    setSearch(e.target.value)
    console.log(search)
  }
  const setsDate = (date:any) => {
    let newDate = moment(date).format("yyyy-MM-DD");
    setSdate(newDate)
  }
  const seteDate = (date:any) => {
    let newDate = moment(date).format("yyyy-MM-DD");
    setEdate(newDate)
  }
  const url = '/item/detail'
  const data = {'search':search, 'sdate':sdate, 'edate':edate }
  const handleSubmit = () => {
    dispatch(findItemDetail(data))
    console.log('1111111111111',data)
  }
  if(item1 !== undefined){
    console.log('allUser is not undefined')
    console.log(item1)
}else{
    console.log('allUser is undefined')
}
  if(item !== undefined){
      console.log('allUser is not undefined')
      console.log(item)
  }else{
      console.log('allUser is undefined')
  }
  const newArticle =()=>{}

  useEffect(()=>{
    console.log('여기는 page')
      dispatch(findItemAll())
  },[])

    
    return (<>
    <div className="flex flex-col h-screen items-center justify-center w-full">
      <div className="flex overflow-x-scroll snap-x snap-mandatory max-w-6xl no-scrollbar">
      </div>
      <h2>게시판 글쓰기</h2>
      <input className="input-border" placeholder="종목을 입력해 주세요 ex)sk하이닉스" onChange={handleTiele}></input><button onClick={handleSubmit}>전송</button>
      <DatePicker className="datepicker-border" placeholderText="시작 날짜를 입력해 주세요 ex)yyyy/MM/dd" dateFormat="yyyy/MM/dd" selected={sdate} onChange={(date) => setsDate(date)} />
      <DatePicker className="datepicker-border" placeholderText="종료 날짜를 입력해 주세요 ex)yyyy/MM/dd" dateFormat="yyyy/MM/dd" selected={edate} onChange={(date) => seteDate(date)} />   
      {/* <MoveButton text="글쓰기" path={`${PG.BOARD}/save`}/> */}
        <Box sx={{ height: '80%', width: '100%' }}>
     {(item||item1) && <DataGrid
        rows={(item||item1)}
        columns={itemColumns()}
        initialState={{
          pagination: {
            paginationModel: {
              pageSize: 12,
            },
          },
        }}
        pageSizeOptions={[5]}
        checkboxSelection
        disableRowSelectionOnClick
      />}
    </Box>
    </div>
    </>)
}

export default ArticleListPage