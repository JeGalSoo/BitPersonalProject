'use client'

import { useRouter } from "next/navigation"
import { DataGrid } from '@mui/x-data-grid';
import { useState, useEffect } from "react"
import {Box, Button, Input} from '@mui/material';
import { useSelector, useDispatch } from 'react-redux'
import { NextPage } from "next";
import Link from "next/link";
import { PG } from "@/redux/common/enums/PG";
import { findAllArticles, findArticleById } from "@/app/component/articles/service/article.service";
import { getAllArticles, getArticleById } from "@/app/component/articles/service/article-slice";
import itemColumns from "@/app/component/board/module/columns";
import MoveButton from "@/app/atoms/button/MoveButton";
import { findItemAll } from "@/app/component/board/service/board-service";
import { getAllitems } from "@/app/component/board/service/board-slice";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";



const ArticleListPage: NextPage = () => {
  const dispatch = useDispatch()
  const item= useSelector(getAllitems)
  const [search,setSearch] = useState()
  const [sdate,setSdate] = useState(new Date())
  const [edate,setEdate] = useState(new Date())

  const handleTiele = (e: any) => {
    setSearch(e.target.value)
    console.log(search)
  }
  const url = 'http://localhost:8080/api/articles/detail'
  const data = {'search':search, 'sdate':sdate, 'edate':edate }
  const handleSubmit = () => {
    console.log('1111111111111',data)
    axios.post(url, data, AxiosConfig())
        .then(res => { 
            const message=res.data.message
            alert(message)
            if(message === 'SUCCESS'){
                                    
             }else if (message === 'FAIL'){
             }else if (message === 'WRONG._PASSWORD'){
             }else{
             }
        })
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
      <input onChange={handleTiele}></input><button onClick={handleSubmit}>전송</button>
      <DatePicker label='시작일을 선택해 주세요' dataFormat="yyyy/MM/dd" selected={sdate} onChange={date => setSdate(date)} />
      <DatePicker label='마지막날을 선택해 주세요' dataFormat="yyyy/MM/dd" selected={edate} onChange={date => setEdate(date)} />   
      {/* <MoveButton text="글쓰기" path={`${PG.BOARD}/save`}/> */}
        <Box sx={{ height: '80%', width: '100%' }}>
     {item && <DataGrid
        rows={item}
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