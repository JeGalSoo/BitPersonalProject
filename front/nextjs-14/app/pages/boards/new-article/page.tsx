'use client'

import AxiosConfig from "@/redux/common/configs/axios-config";
import { API } from "@/redux/common/enums/API";
import { Button } from "@mui/material";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function NewArticle(){
    const [filename,setfilename] = useState('')
    const [sdate,setsdate] = useState('')
    const [edate,setedate] = useState('')

    const handlefilename = (e:any)=>{
        setfilename(e.target.value)
    }
    const handlesdate = (e:any)=>{
        setsdate(e.target.value)
    }
    const handleedate = (e:any)=>{
        setedate(e.target.value)
    }

    const router = useRouter()

    const url = 'http://localhost:8000/'
    const data = {'filename':filename, 'sdate':sdate, 'edate':edate}

    const handleClick = (()=>{
        console.log(url)
        console.log(data)
        alert("완료")
        axios.post(url,data,AxiosConfig())
        .then(res => {
            JSON.stringify(res.data)
            router.push("/boards/list")
        })
    })

    return (<>
    <p>
    <input  name="filename" onChange={handlefilename}/>
    </p>
    <p>
    <input name="sdate" onChange={handlesdate}/>
    </p>
    <p>
    <input name="edate" onChange={handleedate}/>
    </p>
    <Button type="submit" onClick={handleClick}>개시</Button>
    </>)
}